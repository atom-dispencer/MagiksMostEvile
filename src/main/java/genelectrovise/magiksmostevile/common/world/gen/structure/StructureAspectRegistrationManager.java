package genelectrovise.magiksmostevile.common.world.gen.structure;

import java.util.Locale;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;

/**
 * Contains various structure registration methods.
 * 
 * {@link StructureFeatures}
 * 
 * @author GenElectrovise
 *
 */
public class StructureAspectRegistrationManager {

  public static IStructurePieceType registerStructurePiece(IStructurePieceType type, String key) {
    return Registry.register(Registry.STRUCTURE_PIECE, key.toLowerCase(Locale.ROOT), type);
  }
}
