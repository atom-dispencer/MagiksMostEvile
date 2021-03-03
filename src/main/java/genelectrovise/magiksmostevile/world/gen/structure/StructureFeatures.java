/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise    
 *
 * This file is part of Magiks Most Evile.
 * Magiks Most Evile is free software: you can redistribute it and/or modify it under the terms 
 * of the GNU General Public License as published by the Free Software Foundation, 
 * either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE.  
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile. 
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
package genelectrovise.magiksmostevile.world.gen.structure;

import java.util.Locale;
import genelectrovise.magiksmostevile.world.gen.structure.shrine.ShrineStructure;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

public class StructureFeatures {

  public static final Structure<NoFeatureConfig> SHRINE =
      register("MagiksMostEvile_Overground_Shrine", new ShrineStructure(),
          GenerationStage.Decoration.SURFACE_STRUCTURES);

  private static <F extends Structure<?>> F register(String name, F structure,
      GenerationStage.Decoration decorationStage) {
    Structure.NAME_STRUCTURE_BIMAP.put(name.toLowerCase(Locale.ROOT), structure);
    return Registry.register(Registry.STRUCTURE_FEATURE, name.toLowerCase(Locale.ROOT), structure);
  }
}
