package genelectrovise.magiksmostevile.tileentity.mortar;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

/**
 * {@link Blocks#FLOWER_POT}<br>
 * {@link FlowerPotBlock}
 * 
 * @author GenElectrovise
 *
 */
public class MortarBlock extends Block {
  
  protected static final VoxelShape SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);

  public MortarBlock(Properties properties) {
    super(properties);
  }

  @Override
  public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    return new MortarTileEntity();
  }

  @Override
  public boolean hasTileEntity(BlockState state) {
    return true;
  }
  
  @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
      return SHAPE;
    }

  @Override
  public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

    // On client
    if (world.isRemote) {
      return ActionResultType.SUCCESS;
    }

    // If the player is using a pestle
    if (player.getHeldItem(handIn).getItem() instanceof Pestle) {
      TileEntity tileEntity = world.getTileEntity(pos);
      if (tileEntity instanceof MortarTileEntity) {
        ((MortarTileEntity) tileEntity).recipe();
      }
      return ActionResultType.SUCCESS;
    }

    // On server
    // If not air or null
    if (player.getHeldItem(handIn).getItem() != null) {

      // Get the tile entity
      TileEntity tileEntity = ((ServerWorld) world).getTileEntity(pos);

      // IF a Mortar
      if (tileEntity instanceof MortarTileEntity) {

        // Get the stack
        ((MortarTileEntity) tileEntity).recieveItemStack(player.getHeldItem(handIn));
      }
    }

    return ActionResultType.SUCCESS;
  }

  @Override
  public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, FluidState fluid) {

    // Only on server
    if (!world.isRemote) {
      TileEntity tileEntity = world.getTileEntity(pos);
      if (tileEntity instanceof MortarTileEntity) {
        ((MortarTileEntity) tileEntity).popContents();
      }
    }

    return super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
  }

}
