/**
 * 
 */
package genelectrovise.magiksmostevile.common.item.spawn_egg;

import java.util.Objects;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.spawner.AbstractSpawner;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

/**
 * @see SpawnEggItem
 * @author GenElectrovise 4 Jun 2020
 */
@EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class EvileSpawnEgg extends Item {

	public EvileSpawnEgg(Item.Properties properties) {
		super(properties);
	}

	/**
	 * Called when this item is used when targetting a Block
	 */
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		if (world.isRemote) {
			return ActionResultType.SUCCESS;
		} else {
			ItemStack itemstack = context.getItem();
			BlockPos blockpos = context.getPos();
			Direction direction = context.getFace();
			BlockState blockstate = world.getBlockState(blockpos);
			Block block = blockstate.getBlock();
			if (block == Blocks.SPAWNER) {
				TileEntity tileentity = world.getTileEntity(blockpos);
				if (tileentity instanceof MobSpawnerTileEntity) {
					AbstractSpawner abstractspawner = ((MobSpawnerTileEntity) tileentity).getSpawnerBaseLogic();
					EntityType<?> entitytype1 = this.getEntityType();
					abstractspawner.setEntityType(entitytype1);
					tileentity.markDirty();
					world.notifyBlockUpdate(blockpos, blockstate, blockstate, 3);
					itemstack.shrink(1);
					return ActionResultType.SUCCESS;
				}
			}

			BlockPos blockpos1;
			if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
				blockpos1 = blockpos;
			} else {
				blockpos1 = blockpos.offset(direction);
			}

			EntityType<?> entitytype = this.getEntityType();
			if (entitytype.spawn(world, itemstack, context.getPlayer(), blockpos1, SpawnReason.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP) != null) {
				itemstack.shrink(1);
			}

			return ActionResultType.SUCCESS;
		}
	}

	/**
	 * Called to trigger the item's "innate" right click behavior. To handle when
	 * this item is used on a Block, see {@link #onItemUse}.
	 */
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
		if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
			return ActionResult.resultPass(itemstack);
		} else if (worldIn.isRemote) {
			return ActionResult.resultSuccess(itemstack);
		} else {
			BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
			BlockPos blockpos = blockraytraceresult.getPos();
			if (!(worldIn.getBlockState(blockpos).getBlock() instanceof FlowingFluidBlock)) {
				return ActionResult.resultPass(itemstack);
			} else if (worldIn.isBlockModifiable(playerIn, blockpos) && playerIn.canPlayerEdit(blockpos, blockraytraceresult.getFace(), itemstack)) {
				EntityType<?> entitytype = this.getEntityType();
				if (entitytype.spawn(worldIn, itemstack, playerIn, blockpos, SpawnReason.SPAWN_EGG, false, false) == null) {
					return ActionResult.resultPass(itemstack);
				} else {
					if (!playerIn.abilities.isCreativeMode) {
						itemstack.shrink(1);
					}

					playerIn.addStat(Stats.ITEM_USED.get(this));
					return ActionResult.resultSuccess(itemstack);
				}
			} else {
				return ActionResult.resultFail(itemstack);
			}
		}
	}

	public abstract EntityType<?> getEntityType();

}
