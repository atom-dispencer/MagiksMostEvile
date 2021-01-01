package genelectrovise.magiksmostevile.common.world.gen.structure.registry;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Bus.MOD)
public class StructureRegistry {

  @SubscribeEvent
  public static void registerStructures(RegistryEvent.Register<Structure<?>> event) {
    ModStructures.registerStructures(event);
    ModConfiguredStructures.registerConfiguredStructures();
  }
}
