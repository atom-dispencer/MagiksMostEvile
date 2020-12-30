package genelectrovise.magiksmostevile.common.world.gen.ore;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Bus.FORGE)
public class OreFeatureAdditonManager {

  /**
   * Apply {@value #overworldOres()}, {@value #netherOres} and {@value #endOres} to {@link Biome}s
   * of their various {@link Biome.Category}.
   * 
   * @param event
   */
  @SubscribeEvent(priority = EventPriority.HIGH)
  public static void registerOres(final BiomeLoadingEvent event) {
    MagiksMostEvile.LOGGER.debug("Adding MME ores to biome: " + event.getName());

    BiomeGenerationSettingsBuilder generation = event.getGeneration();

    switch (event.getCategory()) {
      case NETHER:
        for (ConfiguredFeature<?, ?> simpleConfiguredOreFeature : OreFeatures.NETHER_ORES) {
          registerTo(generation, simpleConfiguredOreFeature);
        }
        break;

      case THEEND:
        for (ConfiguredFeature<?, ?> simpleConfiguredOreFeature : OreFeatures.END_ORES) {
          registerTo(generation, simpleConfiguredOreFeature);
        }
        break;

      default:
        for (ConfiguredFeature<?, ?> simpleConfiguredOreFeature : OreFeatures.OVERWORLD_ORES) {
          registerTo(generation, simpleConfiguredOreFeature);
        }
        break;
    }

  }

  /**
   * Convenience method to tidy up {@link #registerOres(BiomeLoadingEvent)} by abstracting away
   * {@link BiomeGenerationSettingsBuilder} calls.
   * 
   * @param builder
   * @param feature
   */
  private static void registerTo(BiomeGenerationSettingsBuilder builder,
      ConfiguredFeature<?, ?> feature) {

    builder.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, feature);
  }
}
