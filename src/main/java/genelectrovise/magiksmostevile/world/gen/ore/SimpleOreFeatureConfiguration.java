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
package genelectrovise.magiksmostevile.world.gen.ore;

import java.util.Random;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.IRuleTestType;
import net.minecraft.world.gen.feature.template.RuleTest;

public class SimpleOreFeatureConfiguration extends OreFeatureConfig {

  public SimpleOreFeatureConfiguration(RuleTest predicate, BlockState placedState, int size) {
    super(predicate, placedState, size);
  }

  public SimpleOreFeatureConfiguration(BlockState matchedState, BlockState placedState, int size) {
    this(matchedState, placedState, size, false);
  }

  public SimpleOreFeatureConfiguration(BlockState matchedState, BlockState placedState, int size,
      boolean randomVariation) {

    this(new RuleTest() {

      @Override
      public boolean test(BlockState testAgainst, Random random) {
        return randomVariation == true
            ? testAgainst.getBlock().equals(matchedState.getBlock()) && random.nextBoolean()
            : testAgainst.getBlock().equals(matchedState.getBlock());
      }

      @Override
      protected IRuleTestType<?> getType() {
        return IRuleTestType.BLOCK_MATCH;
      }
    }, placedState, size);
  }

}
