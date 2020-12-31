package genelectrovise.magiksmostevile.common.world.gen.structure;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.core.registry.orbital.registries.StructureFeatureHolder;
import genelectrovise.magiksmostevile.common.core.registry.orbital.registries.StructureOrbitalRegistry;
import genelectrovise.magiksmostevile.common.world.gen.EnumFeatureLocation;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine.OvergroundShrineStructure;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Bus.FORGE)
public class StructureAdditionManager {

  @SubscribeEvent
  public static void addStructuresToBiomes(BiomeLoadingEvent event) {

    BiomeGenerationSettingsBuilder generation = event.getGeneration();

    StructureOrbitalRegistry.EVILE_STRUCTURES.forEach((structureHolder, structureData) -> {
      structureHolder.getFeatures().forEach((name, feature) -> {

        for (EnumFeatureLocation location : feature.getLocations()) {

          Biome.Category eventCategory = event.getCategory();

          if (location.getBiomeCategory() == eventCategory
              || location.getBiomeCategory() == Category.NONE) {
            registerTo(generation, feature, event);
          }
        }

      });
    });

    MagiksMostEvile.LOGGER.debug("MME Structure addition complete!");
  }

  private static void registerTo(BiomeGenerationSettingsBuilder generation,
      StructureFeatureHolder<?, ?> structureFeatureHolder, BiomeLoadingEvent event) {

    MagiksMostEvile.LOGGER
        .debug(" > Adding " + structureFeatureHolder + " to a " + event.getCategory() + " biome");

    generation.withStructure(structureFeatureHolder.getFeature());
    generation.withFeature(Decoration.SURFACE_STRUCTURES, structureFeatureHolder.getFeature());
  }


}
