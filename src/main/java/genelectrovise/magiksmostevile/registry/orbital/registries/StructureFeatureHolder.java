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
package genelectrovise.magiksmostevile.registry.orbital.registries;

import java.util.Arrays;
import java.util.Collection;
import genelectrovise.magiksmostevile.world.gen.EnumFeatureLocation;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;

public class StructureFeatureHolder<FC extends IFeatureConfig, T extends Structure<FC>> {

  private StructureFeature<FC, ? extends Structure<FC>> feature;
  private Collection<EnumFeatureLocation> categories;

  public StructureFeatureHolder(StructureFeature<FC, ? extends Structure<FC>> feature,
      EnumFeatureLocation... enumFeatureLocations) {

    this.feature = feature;
    this.categories = Arrays.asList(enumFeatureLocations);
  }

  public StructureFeature<FC, ? extends Structure<FC>> getFeature() {
    return feature;
  }

  public Collection<EnumFeatureLocation> getLocations() {
    return categories;
  }

}
