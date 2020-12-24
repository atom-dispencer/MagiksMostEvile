package genelectrovise.magiksmostevile.common.world.gen.ore;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.core.registry.EvileDeferredRegistry;
import genelectrovise.magiksmostevile.common.data.ModdedTags;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Bus.MOD)
public class OreFeatures {

  public static ConfiguredFeature<?, ?> AMETHYST_ORE_OVERWORLD;

  public static final ConfiguredFeature<?, ?>[] OVERWORLD_ORES = {AMETHYST_ORE_OVERWORLD};
  public static final ConfiguredFeature<?, ?>[] NETHER_ORES = {};
  public static final ConfiguredFeature<?, ?>[] END_ORES = {};

  @SubscribeEvent
  public static void createConfiguredOreFeatures(FMLCommonSetupEvent event) {
    MagiksMostEvile.LOGGER.debug("Creating MME configured ore features");



    // Amethyst Ore Overworld
    AMETHYST_ORE_OVERWORLD = Registry.register( //
        WorldGenRegistries.CONFIGURED_FEATURE, //
        "amethyst_ore_overworld_generation", //
        Feature.ORE.withConfiguration( //
            new OreFeatureConfig( //
                new TagMatchRuleTest(ModdedTags.AMETHYST_ORE_SPAWNABLE.get()), //
                EvileDeferredRegistry.AMETHYST_ORE_OVERWORLD.get().getDefaultState(), //
                7)) //
            .withPlacement(Placement.DEPTH_AVERAGE.configure( //
                new DepthAverageConfig(10, 32))) //
            .range(16)); //
  }
}
