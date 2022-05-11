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
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
package genelectrovise.magiksmostevile.registry.orbital;

import com.google.common.collect.Maps;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import lombok.Data;
import net.minecraftforge.registries.DeferredRegister;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Configuration;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * <ol>
 * <li>blocks (BLOCKS)
 * <li>items (ITEMS)
 * <li>blockitems
 * <li>tools
 * <li>staffs
 * <li>tomes
 * <li>armor
 * <li>foods
 * <li>tileentities (TILEENTITIES)
 * <li>containers (CONTAINERS)
 * <li>entities (ENTITIES)
 * <li>rituals (RITUALS)
 * <li>particles (PARTICLES)
 * <li>structures (STRUCTURES)
 * <li>(WIP: features)
 * <li>recipes (RECIPES)
 * </ol>
 *
 * @author GenElectrovise
 */
@Data
public class OrbitalRegistryGenerator {

    public static final ConfigurationBuilder REFLECTIONS_CONFIGURATION = new ConfigurationBuilder().forPackages("genelectrovise");
    private static final Logger LOGGER = LogManager.getLogger(OrbitalRegistryGenerator.class);
    private boolean initialised;
    private Reflections reflections;
    private LinkedHashMap<OrbitalRegistry, Object> registries;

    public OrbitalRegistryGenerator(@Nullable Configuration configuration) {
        this.setInitialised(false);
        this.reflections = new Reflections(configuration == null ? REFLECTIONS_CONFIGURATION : configuration);
        registries = Maps.newLinkedHashMap();
    }

    public static void registerDeferredRegister(DeferredRegister<?> register) {
        OrbitalRegistryGenerator.LOGGER.info("Registering MagiksMostEvile DeferredRegister<?> " + register.toString() + " to the MOD_EVENT_BUS");
        register.register(MagiksMostEvile.MOD_EVENT_BUS);
    }

    public void collectOrbitalRegistries() {

        if (isInitialised()) {
            throw new IllegalStateException("OrbitalRegistries already initialised");
        }
        setInitialised(true);

        try {
            // Get Set of Class
            Set<Class<?>> types = reflections.getTypesAnnotatedWith(OrbitalRegistry.class);

            Map<Class<?>, OrbitalRegistry> map = getClassOrbitalRegistryMap(types);

            // Stream
            // Sort by priority
            // Process each
            map.entrySet().stream()
               .sorted(Comparator.comparingInt((e) -> e.getValue().priority()))
               .forEach((e) -> {
                   try {
                       Object inst = getOrbitalRegistryInstance(e);
                       DeferredRegister<?> register = getDeferredRegisterInstance(e, inst);
                       registerDeferredRegister(register);
                   } catch (Exception ex) {
                       LOGGER.error(ex);
                       throw new OrbitalRegistryException(ex);
                   }
               })
            ;
        } catch (Exception e) {
            e.printStackTrace();
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

    @Nonnull
    private Map<Class<?>, OrbitalRegistry> getClassOrbitalRegistryMap(Set<Class<?>> types) {
        Map<Class<?>, OrbitalRegistry> map = Maps.newHashMap();
        types.forEach((c) -> map.put(c, c.getAnnotation(OrbitalRegistry.class)));
        return map;
    }

    private DeferredRegister<?> getDeferredRegisterInstance(Map.Entry<Class<?>, OrbitalRegistry> e, Object inst) throws NoSuchFieldException, IllegalAccessException {
        // Register contained register
        String registryFieldName = e.getValue().registryField();
        Field registryField = inst.getClass().getField(registryFieldName);

        if (!registryField.canAccess(inst)) {
            throw new OrbitalRegistryException("The registryField "
                    + registryFieldName
                    + " cannot be accessed for the OrbitalRegistry "
                    + e.getValue().name());
        }

        if (!DeferredRegister.class.isAssignableFrom(registryField.getType())) {
            throw new OrbitalRegistryException("The registryField "
                    + registryFieldName
                    + " from OrbitalRegistry "
                    + e.getValue().name()
                    + " is not assignable from DeferredRegister. Given type is "
                    + registryField.getType());
        }

        return (DeferredRegister<?>) registryField.get(inst);
    }

    @Nonnull
    private Object getOrbitalRegistryInstance(Map.Entry<Class<?>, OrbitalRegistry> e) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // Instantiate
        LOGGER.info("Generating OrbitalRegistry: "
                + e.getValue().priority()
                + ") ["
                + e.getValue().name()
                + "]");
        Object inst = e.getKey().getConstructor().newInstance();
        registries.put(e.getValue(), inst);
        return inst;
    }
}
