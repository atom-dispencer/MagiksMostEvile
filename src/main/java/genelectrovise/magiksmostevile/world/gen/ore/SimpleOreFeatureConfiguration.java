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
