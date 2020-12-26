package genelectrovise.magiksmostevile.common.world.gen.structure;

import java.util.Locale;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine.ShrineStructure;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

public class StructureFeatures {

  public static final Structure<NoFeatureConfig> SHRINE =
      register("MagiksMostEvile_Overground_Shrine", new ShrineStructure(),
          GenerationStage.Decoration.SURFACE_STRUCTURES);

  private static <F extends Structure<?>> F register(String name, F structure,
      GenerationStage.Decoration decorationStage) {
    Structure.NAME_STRUCTURE_BIMAP.put(name.toLowerCase(Locale.ROOT), structure);
    return Registry.register(Registry.STRUCTURE_FEATURE, name.toLowerCase(Locale.ROOT), structure);
  }
}
