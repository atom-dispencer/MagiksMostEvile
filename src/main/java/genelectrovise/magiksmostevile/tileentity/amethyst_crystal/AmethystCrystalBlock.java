/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise    
 *
 * This file is part of Magiks Most Evile.
 * Magiks Most Evile is free software: you can redistribute it and/or modify it under the terms 
 * of the GNU General Public License as published by the Free Software Foundation, 
 * either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE.  
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile. 
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
package genelectrovise.magiksmostevile.tileentity.amethyst_crystal;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class AmethystCrystalBlock extends Block {
  public static AxisAlignedBB AMETHYST_CRYSTAL_AABB =
      new AxisAlignedBB(0.0625D, 0D, 0.0625D, 0.9375D, 0.625D, 0.9375D);

  // double x1, double y1, double z1, double x2, double y2, double z2
  protected static final VoxelShape BLOCK_SHAPE =
      Block.makeCuboidShape(1.0D, 0D, 1.0D, 15.0D, 1.0D, 15.0D);

  public AmethystCrystalBlock(Properties properties) {
    super(properties);
  }

  @Override
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos,
      ISelectionContext context) {
    return BLOCK_SHAPE;
  }

  // ======================================================================================================================
  // TILE ENTITY

  @Override
  public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    return new AmethystCrystalTileEntity();
  }

  @Override
  public boolean hasTileEntity(BlockState state) {
    return true;
  }

}
