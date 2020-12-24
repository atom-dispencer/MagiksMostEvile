package genelectrovise.magiksmostevile.common.world.gen.ore;

import genelectrovise.magiksmostevile.common.core.registry.EvileDeferredRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;

public class OreFeatures {

  //Amethyst Ore Overworld
  public static final SimpleOreFeatureConfiguration AMETHYST_ORE_OVERWORLD_CONFIG =
      new SimpleOreFeatureConfiguration(Blocks.STONE.getDefaultState(),
          EvileDeferredRegistry.AMETHYST_ORE_OVERWORLD.get().getDefaultState(), 7);

  public static final DepthAverageConfig AMETHYST_ORE_OVERWORLD_PLACEMENT_CONFIG =
      new DepthAverageConfig(10, 32);

  public static final ConfiguredFeature<?, ?> AMETHYST_ORE_OVERWORLD =
      Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "amethyst_ore_overworld_generation", Feature.ORE.withConfiguration(AMETHYST_ORE_OVERWORLD_CONFIG)
          .withPlacement(Placement.DEPTH_AVERAGE.configure(AMETHYST_ORE_OVERWORLD_PLACEMENT_CONFIG))
          .range(16));
}
