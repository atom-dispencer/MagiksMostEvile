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
package genelectrovise.magiksmostevile.world.gen.structure;

import java.util.Locale;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

/**
 * {@link Structure}
 * 
 * @author GenElectrovise
 *
 */
public class StructureFeatures {

  public static final Logger LOGGER = LogManager.getLogger(StructureFeatures.class);

  // public static final Structure<NoFeatureConfig> SHRINE =
  // register("MagiksMostEvile_Overground_Shrine", new ShrineStructure(),
  // GenerationStage.Decoration.SURFACE_STRUCTURES);

  private static Map<Structure<?>, GenerationStage.Decoration> STEP;

  @SuppressWarnings("deprecation")
  private static <F extends Structure<?>> F register(String name, F structure, GenerationStage.Decoration decorationStage) {

    LOGGER.info("Registering structure: " + name);

    if (STEP == null) {
      LOGGER.info("STEP map null, must fetch...");
      STEP = fetchStepMap(structure);
    }

    Structure.STRUCTURES_REGISTRY.put(name.toLowerCase(Locale.ROOT), structure);
    STEP.put(structure, decorationStage);
    return Registry.register(Registry.STRUCTURE_FEATURE, name.toLowerCase(Locale.ROOT), structure);
  }

  private static Map<Structure<?>, Decoration> fetchStepMap(Structure<?> structure) {
    return ObfuscationReflectionHelper.getPrivateValue(Structure.class, structure, "STEP");
  }

}
