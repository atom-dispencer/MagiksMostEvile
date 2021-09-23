/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise
 *
 * This file is part of Magiks Most Evile. Magiks Most Evile is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile.
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
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

  protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);

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
  public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

    // On client
    if (world.isClientSide()) {
      return ActionResultType.SUCCESS;
    }

    // If the player is using a pestle
    if (player.getItemInHand(handIn).getItem() instanceof Pestle) {
      TileEntity tileEntity = world.getBlockEntity(pos);
      if (tileEntity instanceof MortarTileEntity) {
        ((MortarTileEntity) tileEntity).recipe();
      }
      return ActionResultType.SUCCESS;
    }

    // On server
    // If not air or null
    if (player.getItemInHand(handIn).getItem() != null) {

      // Get the tile entity
      TileEntity tileEntity = ((ServerWorld) world).getBlockEntity(pos);

      // IF a Mortar
      if (tileEntity instanceof MortarTileEntity) {

        // Get the stack
        ((MortarTileEntity) tileEntity).recieveItemStack(player.getItemInHand(handIn));
      }
    }

    return ActionResultType.SUCCESS;
  }

  @Override
  public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, FluidState fluid) {

    // Only on server
    if (!world.isClientSide()) {
      TileEntity tileEntity = world.getBlockEntity(pos);
      if (tileEntity instanceof MortarTileEntity) {
        ((MortarTileEntity) tileEntity).popContents();
      }
    }

    return super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
  }

}
