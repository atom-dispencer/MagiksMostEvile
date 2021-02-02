package genelectrovise.magiksmostevile.tileentity.mortar;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class MortarBlock extends Block {

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
  public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

    // On client
    if (worldIn.isRemote) {
      return ActionResultType.SUCCESS;
    }

    // If not air or null
    if (player.getHeldItem(handIn).getItem() != Items.AIR || player.getHeldItem(handIn).getItem() != null) {

      // Get the tile entity
      TileEntity tileEntity = ((ServerWorld) worldIn).getTileEntity(pos);

      // IF a Mortar
      if (tileEntity instanceof MortarTileEntity) {

        // Get the stack
        ((MortarTileEntity) tileEntity).recieveItemStack(player.getHeldItem(handIn));
      }
    }

    return ActionResultType.SUCCESS;
  }

}
