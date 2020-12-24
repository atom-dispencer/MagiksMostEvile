package genelectrovise.magiksmostevile.common.world.gen.ore;

import java.util.ArrayList;
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
public class OreFeatureManager {

  public static final ArrayList<ConfiguredFeature<?, ?>> overworldOres =
      new ArrayList<ConfiguredFeature<?, ?>>();

  public static final ArrayList<ConfiguredFeature<?, ?>> netherOres =
      new ArrayList<ConfiguredFeature<?, ?>>();

  public static final ArrayList<ConfiguredFeature<?, ?>> endOres =
      new ArrayList<ConfiguredFeature<?, ?>>();

  /**
   * Called on {@link Bus.Forge}. Trigger ore registration.
   * 
   * @param event
   */
  @SubscribeEvent(priority = EventPriority.HIGH)
  public static void addOresToBiomes(final BiomeLoadingEvent event) {
    MagiksMostEvile.LOGGER.debug("Adding MME ores: Biome=" + event.getName());
    createOreLists();

    registerOres(event);
  }

  /**
   * Called on {@link Bus.Mod}. Form the lists of ores to register
   * 
   * @param event
   */
  public static void createOreLists() {
    MagiksMostEvile.LOGGER.debug("Creating ore lists for MME feature addition!");
    overworldOres();
    netherOres();
    endOres();
  }

  private static void overworldOres() {
    overworldOres.add(OreFeatures.AMETHYST_ORE_OVERWORLD);
  }

  private static void netherOres() {

  }

  private static void endOres() {

  }

  /**
   * Apply {@value #overworldOres()}, {@value #netherOres} and {@value #endOres} to {@link Biome}s
   * of their various {@link Biome.Category}.
   * 
   * @param event
   */
  private static void registerOres(final BiomeLoadingEvent event) {

    BiomeGenerationSettingsBuilder generation = event.getGeneration();

    switch (event.getCategory()) {
      case NETHER:
        for (ConfiguredFeature<?, ?> simpleConfiguredOreFeature : netherOres) {
          registerTo(generation, simpleConfiguredOreFeature);
        }
        break;

      case THEEND:
        for (ConfiguredFeature<?, ?> simpleConfiguredOreFeature : endOres) {
          registerTo(generation, simpleConfiguredOreFeature);
        }
        break;

      default:
        for (ConfiguredFeature<?, ?> simpleConfiguredOreFeature : overworldOres) {
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
