package genelectrovise.magiksmostevile.common.world.gen.structure;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;

/**
 * Used to decorate structures with random blocks based on a palette and replacement conditions
 * given using
 * 
 * @author GenElectrovise 12 May 2020
 */
public class StructureDecorator {
  private IWorld world;
  private StructureDecorationDictionary dictionary;
  int lowerY, upperY;

  public StructureDecorator(MutableBoundingBox bounds, IWorld worldIn,
      StructureDecorationDictionary dictionary, int lowerY, int upperY) {
    this.world = worldIn;
    this.dictionary = dictionary;
    this.lowerY = lowerY;
    this.upperY = upperY;
  }

  /**
   * Decorates throughout the {@link MutableBoundingBox} specified in the constructor of this class
   * using the {@link StructureDecorationDictionary} also given there.
   * 
   * @param type
   */
  public void decorateThroughoutBoundingBox(MutableBoundingBox bounds, EvileStructureType type) {

    switch (type) {
      case SHRINE:
        bounds.minY = bounds.minY - 5;
        break;
      default:
        break;
    }

    for (int x = bounds.minX; x < bounds.maxX + 1; x++) {
      for (int y = bounds.minY; y < bounds.maxY + 1; y++) {
        for (int z = bounds.minZ; z < bounds.maxZ + 1; z++) {
          BlockPos pos = new BlockPos(x, y, z);
          BlockState oldState = world.getBlockState(pos);

          if (dictionary.containsKey(oldState.getBlock())
              || oldState.getBlock() instanceof ChestBlock) {
            world.setBlockState(pos, dictionary.decorate(oldState, pos), 1);
          } else {
            // Main.LOGGER.debug(oldState.getBlock().getRegistryName() + " is not a valid
            // replaceable according to the dictionary " + dictionary);
          }
        }
      }
    }
  }
}
