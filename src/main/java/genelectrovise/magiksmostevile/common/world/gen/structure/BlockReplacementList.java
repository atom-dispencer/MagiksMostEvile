package genelectrovise.magiksmostevile.common.world.gen.structure;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

/**
 * A list of {@link Block}s that a {@link StructureDecorator} could replace.
 * 
 * @author GenElectrovise
 *
 */
public class BlockReplacementList extends ArrayList<BlockWeightPair> {
  private static final long serialVersionUID = 1L;
  private Random random;

  public BlockReplacementList(Random random) {
    this.random = random;
  }

  public BlockReplacementList addPair(BlockWeightPair e) {
    super.add(e);
    return this;
  }

  /**
   * @return A random element from this {@link BlockReplacementList}, defaulting to the Hated Enemy
   *         (Diorite) if size is less than 1.
   */
  public BlockState random() {
    if (size() > 0) {
      return get(random.nextInt(size())).block.getDefaultState();
    }
    return Blocks.DIORITE.getDefaultState();
  }

}
