package genelectrovise.magiksmostevile.common.tileentity.amethyst_crystal;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class AmethystCrystalBlock extends Block {
	public static AxisAlignedBB AMETHYST_CRYSTAL_AABB = new AxisAlignedBB(0.0625D, 0D, 0.0625D, 0.9375D, 0.625D,
			0.9375D);

	// double x1, double y1, double z1, double x2, double y2, double z2
	protected static final VoxelShape BLOCK_SHAPE = Block.makeCuboidShape(1.0D, 0D, 1.0D, 15.0D, 1.0D, 15.0D);

	public AmethystCrystalBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
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
