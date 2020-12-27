package genelectrovise.magiksmostevile.common.world.gen.structure;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.StructureFeature;
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

    StructuresAndStructureFeatures.map.forEach((structure, biomeCategories) -> {
      for (Biome.Category category : biomeCategories) {
        if (category.equals(event.getCategory())) {
          registerTo(generation, structure);
        }
      }
    });
  }

  private static void registerTo(BiomeGenerationSettingsBuilder generation,
      StructureFeature<?, ?> structure) {

    generation.withStructure(structure);
  }


}
