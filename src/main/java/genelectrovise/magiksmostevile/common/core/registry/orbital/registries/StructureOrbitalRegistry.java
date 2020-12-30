package genelectrovise.magiksmostevile.common.core.registry.orbital.registries;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.core.registry.orbital.IOrbitalRegistry;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine.OvergroundShrineFeatureConfig;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine.OvergroundShrineStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

// https://forums.minecraftforge.net/topic/93506-1163-generate-structure-in-custom-biome/

public class StructureOrbitalRegistry implements IOrbitalRegistry {
  
  public static final DeferredRegister<Structure<?>> STRUCTURES =
      DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, MagiksMostEvile.MODID);
  
  public static final RegistryObject<Structure<OvergroundShrineFeatureConfig>> OVERGROUND_SHRINE =
      STRUCTURES.register("overground_shrine", () -> new OvergroundShrineStructure());


  @Override
  public String name() {
    return "structures";
  }


  @Override
  public int priority() {
    return 14;
  }


  @Override
  public void initialise() {
    STRUCTURES.register(MagiksMostEvile.MOD_EVENT_BUS);
  }
}
