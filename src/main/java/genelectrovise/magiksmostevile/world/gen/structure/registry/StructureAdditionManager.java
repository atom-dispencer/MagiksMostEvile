package genelectrovise.magiksmostevile.world.gen.structure.registry;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Bus.FORGE)
public class StructureAdditionManager {

  @SubscribeEvent
  public static void addStructures(BiomeLoadingEvent event) {

    MagiksMostEvile.LOGGER.info("Adding MME structures to biome: " + event.getName());

    for (StructureFeature<?, ?> structure : ModConfiguredStructures.map.values()) {
      event.getGeneration().withStructure(structure);
    }
  }
}
