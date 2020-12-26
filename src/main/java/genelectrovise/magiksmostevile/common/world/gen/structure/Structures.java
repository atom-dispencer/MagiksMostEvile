package genelectrovise.magiksmostevile.common.world.gen.structure;

import genelectrovise.magiksmostevile.common.world.gen.structure.shrine.OvergroundShrineFeatureConfig;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine.OvergroundShrineStructure;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.structure.Structure;

public class Structures {
  public static final Structure<OvergroundShrineFeatureConfig> OVERGROUND_SHRINE =
      StructureAspectRegistrationManager.registerStructure("MagiksMostEvile_Overground_Shrine",
          new OvergroundShrineStructure(), GenerationStage.Decoration.SURFACE_STRUCTURES);
}
