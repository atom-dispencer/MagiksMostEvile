package genelectrovise.magiksmostevile.common.world.gen.structure.registry;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine.OvergroundShrineStructurePiece;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;

public class StructurePieces {

  public static final IStructurePieceType OVERGROUND_SHRINE_PIECE =
      OvergroundShrineStructurePiece::new;

  public static void registerAllPieces() {
    registerStructurePiece(OVERGROUND_SHRINE_PIECE,
        new ResourceLocation(MagiksMostEvile.MODID, "overground_shrine_piece"));
  }

  public static void registerStructurePiece(IStructurePieceType structurePiece,
      ResourceLocation rl) {
    Registry.register(Registry.STRUCTURE_PIECE, rl, structurePiece);
  }
}
