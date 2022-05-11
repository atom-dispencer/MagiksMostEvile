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
import java.util.function.Consumer
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
    private var registries: LinkedHashMap<OrbitalRegistry, Any>

    init {
        initialised = false
        reflections = Reflections(configuration ?: REFLECTIONS_CONFIGURATION)
        registries = Maps.newLinkedHashMap()
    }

    fun generateOrbitals() {
        check(!initialised) { "OrbitalRegistries already initialised" }
        initialised = true
        try {
            // Get Set of Class
            val types = reflections.getTypesAnnotatedWith(OrbitalRegistry::class.java)
            val map = getClassOrbitalRegistryMap(types)

            // Stream
            // Sort by priority
            // Process each
            instantiateAndRegisterOrbitals(map)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        /*try {

            MagiksMostEvile.LOGGER.debug("Collecting OrbitalRegistries");

            Set<Class<? extends IOrbitalRegistry>> orbitals = reflections.getSubTypesOf(IOrbitalRegistry.class);

            Map<Integer, IOrbitalRegistry> registries = new HashMap<Integer, IOrbitalRegistry>();

            for (Class<? extends IOrbitalRegistry> clazz : orbitals) {
                IOrbitalRegistry instance = clazz.newInstance();
                registries.put(instance.priority(), instance);
            }

            ArrayList<Integer> keys = Lists.newArrayList(registries.keySet());
            Collections.sort(keys);

            for (Integer integer : keys) {
                IOrbitalRegistry registry = registries.get(integer);
                MagiksMostEvile.LOGGER.info("Initialising MagiksMostEvile OrbitalRegistry: (" + registry.priority() + ") [" + registry.name() + "]");
                registry.initialise();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    protected fun instantiateAndRegisterOrbitals(map: Map<Class<*>, OrbitalRegistry>) {
        map.entries.stream()
            .sorted(
                Comparator.comparingInt { (_, value) -> value.priority }
            )
            .forEach { e: Map.Entry<Class<*>, OrbitalRegistry> ->
                try {
                    val inst = getOrbitalRegistryInstance(e)
                    val register = getDeferredRegisterInstance(e, inst)
                    registerDeferredRegister(register)
                } catch (ex: Exception) {
                    LOGGER.error(ex)
                    throw OrbitalRegistryException(ex)
                }
            }
    }

    @Nonnull
    protected fun getClassOrbitalRegistryMap(types: Set<Class<*>>): Map<Class<*>, OrbitalRegistry> {
        val map: MutableMap<Class<*>, OrbitalRegistry> = Maps.newHashMap()
        types.forEach(Consumer { c: Class<*> ->
            map[c] = c.getAnnotation(
                OrbitalRegistry::class.java
            )
        })
        return map
    }

    @Throws(NoSuchFieldException::class, IllegalAccessException::class)
    protected fun getDeferredRegisterInstance(e: Map.Entry<Class<*>, OrbitalRegistry>, inst: Any): DeferredRegister<*> {
        // Register contained register
        val registryFieldName: String = e.value.registryField
        val registryField = inst.javaClass.getField(registryFieldName)
        if (!registryField.canAccess(inst)) {
            throw OrbitalRegistryException(
                "The registryField "
                        + registryFieldName
                        + " cannot be accessed for the OrbitalRegistry "
                        + e.value.name
            )
        }
        if (!DeferredRegister::class.java.isAssignableFrom(registryField.type)) {
            throw OrbitalRegistryException(
                "The registryField "
                        + registryFieldName
                        + " from OrbitalRegistry "
                        + e.value.name
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
    protected fun getOrbitalRegistryInstance(e: Map.Entry<Class<*>, OrbitalRegistry>): Any {
        // Instantiate
        LOGGER.info(
            "Generating OrbitalRegistry: "
                    + e.value.priority
                    + ") ["
                    + e.value.name
                    + "]"
        )
        val inst = e.key.getConstructor().newInstance()
        registries[e.value] = inst
        return inst
    }

    companion object {
        @JvmField
        val REFLECTIONS_CONFIGURATION = ConfigurationBuilder().forPackages("genelectrovise")
        protected val LOGGER: Logger = LogManager.getLogger(
            OrbitalRegistryHandler::class.java
        )

        @JvmStatic
        protected fun registerDeferredRegister(register: DeferredRegister<*>) {
            LOGGER.info("Registering MagiksMostEvile DeferredRegister<?> $register to the MOD_EVENT_BUS")
            register.register(MagiksMostEvile.MOD_EVENT_BUS)
        }
    }
}
