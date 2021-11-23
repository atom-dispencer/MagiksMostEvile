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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Configuration;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import com.google.common.collect.Lists;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraftforge.registries.DeferredRegister;

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
 *
 */
public class OrbitalRegistryGenerator {

  private boolean initialised;
  private Reflections reflections;

  public static final ConfigurationBuilder REFLECTIONS_CONFIGURATION = new ConfigurationBuilder().forPackages("genelectrovise");
  private static final Logger LOGGER = LogManager.getLogger(OrbitalRegistryGenerator.class);

  public OrbitalRegistryGenerator(@Nullable Configuration configuration) {
    this.setInitialised(false);
    this.reflections = new Reflections(configuration == null ? REFLECTIONS_CONFIGURATION : configuration);
  }

  public void collectOrbitalRegistries() {

    if (this.isInitialised()) {
      throw new IllegalStateException("OrbitalRegistries already initialised");
    }

    try {

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
    }

    this.setInitialised(true);

  }

  public static void registerDeferredRegister(DeferredRegister<?> register) {
    OrbitalRegistryGenerator.LOGGER.info("Registering MagiksMostEvile DeferredRegister<?> " + register.toString() + " to the MOD_EVENT_BUS");
    register.register(MagiksMostEvile.MOD_EVENT_BUS);
  }

  // Get and set

  public boolean isInitialised() { return initialised; }

  public void setInitialised(boolean initialised) { this.initialised = initialised; }

  public Reflections getReflections() { return reflections; }

  public void setReflections(Reflections reflections) { this.reflections = reflections; }
}
