package genelectrovise.magiksmostevile.common.tileentity.altar;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.tileentity.ICustomContainer;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.pathfinding.PathType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

/**
 * @see AnvilBlock
 * @author GenElectrovise 19 Jun 2020
 */
public class AltarBlock extends Block {
	// double x1, double y1, double z1, double x2, double y2, double z2
	protected static final VoxelShape BODY_ADDON = Block.makeCuboidShape(1.0D, 0D, 1.0D, 15.0D, 14.0D, 15.0D);
	protected static final VoxelShape BASE_ADDON = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
	protected static final VoxelShape COMBINED_SHAPE = VoxelShapes.or(BODY_ADDON, BASE_ADDON);

	public AltarBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	@Override
	public boolean isTransparent(BlockState state) {
		return true;
	}

	@Override
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return true;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return COMBINED_SHAPE;
	}

	// ======================================================================================================================
	// TILE ENTITY

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new AltarTileEntity();
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity) {
			EffectInstance levitation = new EffectInstance(Effects.LEVITATION, 30);
			LivingEntity entity = (LivingEntity) entityIn;
			entity.addPotionEffect(levitation);
		}
	}

	@Override
	public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {

		if (!worldIn.isRemote()) {
			for (int i = 0; i < 15; i++) {
				LightningBoltEntity lightning = new LightningBoltEntity(worldIn.getWorld(), pos.getX() + RANDOM.nextInt(21) - 5, pos.getY() + RANDOM.nextInt(3) - 1, pos.getZ() + RANDOM.nextInt(21) - 5, true);
				if (lightning.world instanceof ServerWorld) {
					ServerWorld serverWorld = (ServerWorld) lightning.world;

					serverWorld.addLightningBolt(lightning);

					PlayerEntity player = serverWorld.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 5, false);

					if (player != null) {
						serverWorld.createExplosion(player, pos.getX(), pos.getY(), pos.getZ(), 2, true, Explosion.Mode.BREAK);
					}
				}
			}
		}
	}

	// Gui

	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult rayTraceResult) {
		if (!worldIn.isRemote) {
			MagiksMostEvile.LOGGER.debug("Activated -- world not remote (client)");

			final ICustomContainer tileEntity = (ICustomContainer) worldIn.getTileEntity(pos);
			tileEntity.openGUI((ServerPlayerEntity) player);

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.FAIL;
	}

}
