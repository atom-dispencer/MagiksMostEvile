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
package genelectrovise.magiksmostevile.world.gen.structure.shrine;

import com.mojang.serialization.Codec;
import genelectrovise.magiksmostevile.world.gen.EnumFeatureLocation;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.RuinedPortalFeature;


/**
 * {@link RuinedPortalFeature}
 * 
 * @author GenElectrovise
 *
 */
public class OvergroundShrineFeatureConfig implements IFeatureConfig {
  public static final Codec<OvergroundShrineFeatureConfig> CODEC = EnumFeatureLocation.CODEC
      .fieldOf("shrine_type").xmap(OvergroundShrineFeatureConfig::new, (feature) -> {
        return feature.location;
      }).codec();
  public final EnumFeatureLocation location;

  public OvergroundShrineFeatureConfig(EnumFeatureLocation location) {
    this.location = location;
  }

}
