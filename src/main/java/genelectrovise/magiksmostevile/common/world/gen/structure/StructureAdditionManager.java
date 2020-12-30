package genelectrovise.magiksmostevile.common.world.gen.structure;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine.OvergroundShrineStructure;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage.Decoration;
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

    StructureFeatures.map.forEach((structure, biomeCategories) -> {
      for (Biome.Category category : biomeCategories) {

        Biome.Category eventCategory = event.getCategory();

        if (category == eventCategory || category == Category.NONE) {
          registerTo(generation, structure, event);
        }
      }
    });

    MagiksMostEvile.LOGGER.debug("MME Structure addition complete!");
  }

  private static void registerTo(BiomeGenerationSettingsBuilder generation,
      StructureFeature<?, ?> structure, BiomeLoadingEvent event) {
    
    return;

    //MagiksMostEvile.LOGGER.debug(" > Adding " + structure + " to a " + event.getCategory() + " biome");
    
    //generation.withStructure(structure);
    //Registry.register(Registry.STRUCTURE_FEATURE, "", new OvergroundShrineStructure());
    //generation.withFeature(Decoration.SURFACE_STRUCTURES, null);
  }


}
