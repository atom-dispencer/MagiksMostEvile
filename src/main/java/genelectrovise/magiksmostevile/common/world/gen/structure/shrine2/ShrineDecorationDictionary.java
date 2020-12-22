/**
 * 
 */
package genelectrovise.magiksmostevile.common.world.gen.structure.shrine2;

import java.util.Random;
import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import genelectrovise.magiksmostevile.common.world.gen.structure.BlockReplacementList;
import genelectrovise.magiksmostevile.common.world.gen.structure.BlockWeightPair;
import genelectrovise.magiksmostevile.common.world.gen.structure.StructureDecorationDictionary;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;

/**
 * Holds the pairs of {@link Block}s that can be replaced. (and Items!)
 * 
 * @author GenElectrovise 12 May 2020
 */
public class ShrineDecorationDictionary extends StructureDecorationDictionary {
  private static final long serialVersionUID = 1L;
  private int chanceToAddItemStack = 2;
  private int maxAddedStacks = 27;

  ItemStack[] stacks = new ItemStack[] { //
      new ItemStack(Items.BONE, random.nextInt(10) + 1), //
      new ItemStack(Items.BONE_MEAL, random.nextInt(15) + 1), //
      new ItemStack(EvileDeferredRegistry.AMETHYST.get(), random.nextInt(6) + 1), //
      new ItemStack(EvileDeferredRegistry.POWERED_AMETHYST.get(), random.nextInt(2) + 1), //
      new ItemStack(EvileDeferredRegistry.OVER_POWERED_AMETHYST.get(), random.nextInt(1) + 1), //
      new ItemStack(Items.EMERALD, random.nextInt(2) + 1), //
      new ItemStack(Items.DIAMOND, random.nextInt(1) + 1), //
      new ItemStack(Items.GOLD_NUGGET, random.nextInt(20) + 1), //
      new ItemStack(Items.EMERALD, random.nextInt(3) + 1), //
      new ItemStack(Items.IRON_NUGGET, random.nextInt(14) + 1), //
      new ItemStack(Items.BROWN_MUSHROOM, random.nextInt(6) + 1), //
      new ItemStack(Items.RED_MUSHROOM, random.nextInt(7) + 1), //
      new ItemStack(Items.ROTTEN_FLESH, random.nextInt(25) + 1), //
      new ItemStack(EvileDeferredRegistry.AMETHYST_POTATO.get(), random.nextInt(10) + 1), //
      new ItemStack(Items.WOODEN_HOE, random.nextInt(1) + 1), //
      new ItemStack(EvileDeferredRegistry.TOME_CONVERT_AMETHYST.get(), random.nextInt(1) + 1), //
      new ItemStack(EvileDeferredRegistry.TOME_SUMMON_FLAPPY.get(), random.nextInt(1) + 1),//
  };

  // Infested stone bricks
  private final BlockWeightPair INFESTED_CRACKED_STONE_BRICKS =
      new BlockWeightPair(Blocks.INFESTED_CRACKED_STONE_BRICKS, 7);
  private final BlockWeightPair INFESTED_COBBLESTONE =
      new BlockWeightPair(Blocks.INFESTED_COBBLESTONE, 4);
  private final BlockWeightPair CHISLED_STONE_BRICKS =
      new BlockWeightPair(Blocks.CHISELED_STONE_BRICKS, 3);
  private final BlockWeightPair INFESTED_MOSSY_STONE_BRICKS =
      new BlockWeightPair(Blocks.INFESTED_MOSSY_STONE_BRICKS, 6);
  private final BlockWeightPair AIR = new BlockWeightPair(Blocks.AIR, 1);

  // Cobblestone
  private final BlockWeightPair MOSSY_COBBLESTONE =
      new BlockWeightPair(Blocks.MOSSY_COBBLESTONE, 1);
  private final BlockWeightPair STONE_BRICKS = new BlockWeightPair(Blocks.STONE_BRICKS, 2);
  private final BlockWeightPair STONE = new BlockWeightPair(Blocks.STONE, 2);

  // Stone brick wall
  private final BlockWeightPair STONE_BRICK_WALL =
      new BlockWeightPair(Blocks.MOSSY_STONE_BRICK_WALL, 2);
  private final BlockWeightPair MOSSY_STONE_BRICK_WALL =
      new BlockWeightPair(Blocks.MOSSY_STONE_BRICK_WALL, 1);
  private final BlockWeightPair MOSSY_COBBLESTONE_WALL =
      new BlockWeightPair(Blocks.MOSSY_COBBLESTONE_WALL, 1);

  public ShrineDecorationDictionary(Random random, Biome biome, IWorld world) {
    super(random, world);
    MagiksMostEvile.LOGGER.debug("New ShrineBlockReplacementDictionary in biome " + biome);

    add(Blocks.INFESTED_STONE_BRICKS,
        new BlockReplacementList(random).addPair(INFESTED_MOSSY_STONE_BRICKS)
            .addPair(INFESTED_CRACKED_STONE_BRICKS).addPair(CHISLED_STONE_BRICKS)
            .addPair(INFESTED_COBBLESTONE).addPair(AIR));
    add(Blocks.INFESTED_COBBLESTONE, new BlockReplacementList(random).addPair(MOSSY_COBBLESTONE)
        .addPair(STONE_BRICKS).addPair(STONE));
    add(Blocks.COBBLESTONE, new BlockReplacementList(random).addPair(MOSSY_COBBLESTONE)
        .addPair(STONE_BRICKS).addPair(STONE));
    add(Blocks.STONE_BRICK_WALL, new BlockReplacementList(random).addPair(STONE_BRICK_WALL)
        .addPair(MOSSY_STONE_BRICK_WALL).addPair(MOSSY_COBBLESTONE_WALL));

  }

  @Override
  public BlockState decorate(BlockState state, BlockPos pos) {

    MagiksMostEvile.LOGGER.debug("state=" + state + " pos=" + pos);
    if (state.getBlock() instanceof ChestBlock) {
      MagiksMostEvile.LOGGER.debug("==8== Found chest! state=" + state + " pos=" + pos);
      return handleChest(state, pos);
    }

    return super.decorate(state, pos);
  }

  @Override
  protected BlockState handleChest(BlockState state, BlockPos pos) {
    ChestTileEntity tileEntity = (ChestTileEntity) world.getTileEntity(pos);

    int i;
    int j;

    for (i = random.nextInt(maxAddedStacks); i < maxAddedStacks; i++) {

      j = random.nextInt(chanceToAddItemStack);
      if (j + 1 == chanceToAddItemStack) {

        ItemStack stackToAdd = stacks[random.nextInt(stacks.length)];
        MagiksMostEvile.LOGGER.debug("Adding : " + stackToAdd + " from " + stacks);
        tileEntity.setInventorySlotContents(random.nextInt(tileEntity.getSizeInventory()),
            stackToAdd);
      }
    }

    return super.handleChest(state, pos);
  }
}
