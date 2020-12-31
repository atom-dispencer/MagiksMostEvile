package genelectrovise.magiksmostevile.common.world.gen.structure.registry;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine.OvergroundShrineFeatureConfig;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine.OvergroundShrineStructure;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.IForgeRegistry;

public class ModStructures {

  public static final Structure<OvergroundShrineFeatureConfig> OVERGROUND_SHRINE =
      new OvergroundShrineStructure();

  public static void registerStructures(Register<Structure<?>> event) {
    register(event.getRegistry(), OVERGROUND_SHRINE, "overground_shrine");

    setupStructure(OVERGROUND_SHRINE);

    StructurePieces.registerAllPieces();
  }

  public static <F extends Structure<?>> void setupStructure(F structure) {
    Structure.NAME_STRUCTURE_BIMAP.put(structure.getRegistryName().toString(), structure);
  }

  public static void register(IForgeRegistry<Structure<?>> registry, Structure<?> entry,
      String registryKey) {

    entry.setRegistryName(new ResourceLocation(MagiksMostEvile.MODID, registryKey));
    registry.register(entry);
  }
}
