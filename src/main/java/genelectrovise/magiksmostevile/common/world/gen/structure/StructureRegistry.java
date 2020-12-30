package genelectrovise.magiksmostevile.common.world.gen.structure;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine.OvergroundShrineFeatureConfig;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine.OvergroundShrineStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

// https://forums.minecraftforge.net/topic/93506-1163-generate-structure-in-custom-biome/

@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Bus.MOD)
public class StructureRegistry {
  public static final DeferredRegister<Structure<?>> STRUCTURES =
      DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, MagiksMostEvile.MODID);

  // Structures
  public static final RegistryObject<Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE =
      STRUCTURES.register("overground_shrine", () -> new OvergroundShrineStructure());


  public static void init(IEventBus modEventBus) {
    STRUCTURES.register(modEventBus);
  }
}
