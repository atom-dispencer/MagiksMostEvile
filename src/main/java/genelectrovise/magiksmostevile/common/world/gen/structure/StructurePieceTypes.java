package genelectrovise.magiksmostevile.common.world.gen.structure;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine.OvergroundShrineStructurePiece;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;

public class StructurePieceTypes {
  public static final IStructurePieceType OVERGROUND_SHRINE =
      StructureAspectRegistrationManager.registerStructurePiece(OvergroundShrineStructurePiece::new,
          new ResourceLocation(MagiksMostEvile.MODID, "OGS").toString());
}
