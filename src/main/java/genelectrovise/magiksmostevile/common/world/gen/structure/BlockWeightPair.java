package genelectrovise.magiksmostevile.common.world.gen.structure;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

/**
 * Denotes a {@link Block} and a weight to give it in the random selection process. Will not allow
 * weights of less than 1, defaulting them to 1 if that is the case.
 * 
 * @author GenElectrovise 12 May 2020
 */
public class BlockWeightPair {
  public Block block;
  public int weight;
  public BlockState state;

  public BlockWeightPair(Block block, int weight) {
    this(block.getDefaultState(), weight);
  }

  public BlockWeightPair(BlockState state, int weight) {
    this.block = state.getBlock();
    this.state = state;

    if (weight < 1) {
      this.weight = 1;
    } else {
      this.weight = weight;
    }
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("BlockWeightPair{");

    if (block == null) {
      builder.append("Block=null");
    } else {
      builder.append("Block=" + block.getRegistryName());
    }

    if (state == null) {
      builder.append(" State=null");
    } else {
      builder.append(" State=" + state);
    }

    builder.append(" Weight=" + weight);

    builder.append("}");
    return builder.toString();
  }
}
