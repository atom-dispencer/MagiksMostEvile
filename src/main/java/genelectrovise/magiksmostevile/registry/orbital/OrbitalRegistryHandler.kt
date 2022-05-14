/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise
 *
 * This file is part of Magiks Most Evile. Magiks Most Evile is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile.
 * If not, see <https:></https:>//www.gnu.org/licenses/>.
 */
package genelectrovise.magiksmostevile.registry.orbital

import com.google.common.collect.Maps
import genelectrovise.magiksmostevile.core.MagiksMostEvile
import lombok.Data
import net.minecraftforge.registries.DeferredRegister
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.reflections.Configuration
import org.reflections.Reflections
import org.reflections.util.ConfigurationBuilder
import java.lang.reflect.InvocationTargetException
import java.util.stream.Collectors
import javax.annotation.Nonnull

/**
 *
 *  1. blocks (BLOCKS)
 *  1. items (ITEMS)
 *  1. blockitems
 *  1. tools
 *  1. staffs
 *  1. tomes
 *  1. armor
 *  1. foods
 *  1. tileentities (TILEENTITIES)
 *  1. containers (CONTAINERS)
 *  1. entities (ENTITIES)
 *  1. rituals (RITUALS)
 *  1. particles (PARTICLES)
 *  1. structures (STRUCTURES)
 *  1. (WIP: features)
 *  1. recipes (RECIPES)
 *
 *
 * @author GenElectrovise
 */
@Data
open class OrbitalRegistryHandler(configuration: Configuration?) {
    private var initialised = false
    private var reflections: Reflections

    init {
        initialised = false
        reflections = Reflections(configuration ?: REFLECTIONS_CONFIGURATION)
    }

    fun generateOrbitals() {
        check(!initialised) { "OrbitalRegistries already initialised" }
        initialised = true
        try {
            reflections
                // Get Classes with Orbital annotation
                .getTypesAnnotatedWith(OrbitalRegistry::class.java)
                // Make stream of Classes
                .stream()
                // Collect the Stream into a <Class, Orbital> Map
                .collect(
                    Collectors.toMap(
                        { c -> c },
                        { c -> c.getAnnotation(OrbitalRegistry::class.java) }
                    ))

                // Get a Set of the entries in the Class, Orbital map
                .entries
                // Sort the entries to their natural order by priority
                .sortedBy { (_, value) -> value.priority }
                // Instantiate each
                .forEach { (e, r) -> instantiateAndRegisterOrbital(e, r) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    internal fun instantiateAndRegisterOrbital(e: Class<*>, o: OrbitalRegistry) {
        try {
            val inst = getOrbitalRegistryInstance(e, o)
            val register = getDeferredRegisterInstance(o, inst)
            registerDeferredRegister(register)
        } catch (ex: Exception) {
            LOGGER.error(ex)
            throw OrbitalRegistryException(ex)
        }
    }

    @Throws(NoSuchFieldException::class, IllegalAccessException::class)
    internal fun getDeferredRegisterInstance(o: OrbitalRegistry, inst: Any): DeferredRegister<*> {
        // Register contained register
        val registryFieldName: String = o.registryField
        val registryField = inst.javaClass.getField(registryFieldName)
        if (!registryField.canAccess(inst)) {
            throw OrbitalRegistryException(
                "The registryField "
                        + registryFieldName
                        + " cannot be accessed for the OrbitalRegistry "
                        + o.name
            )
        }
        if (!DeferredRegister::class.java.isAssignableFrom(registryField.type)) {
            throw OrbitalRegistryException(
                "The registryField "
                        + registryFieldName
                        + " from OrbitalRegistry "
                        + o.name
                        + " is not assignable from DeferredRegister. Given type is "
                        + registryField.type
            )
        }
        return registryField[inst] as DeferredRegister<*>
    }

    @Nonnull
    @Throws(
        InstantiationException::class,
        IllegalAccessException::class,
        InvocationTargetException::class,
        NoSuchMethodException::class
    )
    internal fun getOrbitalRegistryInstance(e: Class<*>, o: OrbitalRegistry): Any {
        // Instantiate
        LOGGER.info(
            "Generating OrbitalRegistry: "
                    + o.priority
                    + ") ["
                    + o.name
                    + "]"
        )
        return e.getConstructor().newInstance()
    }

    companion object {
        @JvmField
        val REFLECTIONS_CONFIGURATION = ConfigurationBuilder().forPackages("genelectrovise")
        protected val LOGGER: Logger = LogManager.getLogger(
            OrbitalRegistryHandler::class.java
        )

        @JvmStatic
        internal fun registerDeferredRegister(register: DeferredRegister<*>) {
            LOGGER.info("Registering MagiksMostEvile DeferredRegister<?> $register to the MOD_EVENT_BUS")
            register.register(MagiksMostEvile.MOD_EVENT_BUS)
        }
    }
}
