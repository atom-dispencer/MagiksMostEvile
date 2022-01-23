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
package genelectrovise.magiksmostevile.item.spawn_egg;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.block.*;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IDispenseItemBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.DispenserTileEntity;
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
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.AbstractSpawner;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.Objects;

/**
 * @author GenElectrovise 4 Jun 2020
 * @see SpawnEggItem
 * @see DispenserTileEntity
 * @see DispenserBlock
 */
@EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class EvileSpawnEgg extends Item {

    /**
     * Should mean that entities spawn when the egg is fired from a dispenser.
     */
    public static final IDispenseItemBehavior DISPENSER_BEHAVIOR = new DefaultDispenseItemBehavior() {

        /**
         * Dispense the specified stack, play the dispense sound and spawn particles.
         */
        @Override
        public ItemStack execute(IBlockSource source, ItemStack stack) {
            try {
                Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
                EntityType<?> entitytype = ((EvileSpawnEgg) stack.getItem()).getEntityType();
                entitytype.spawn(source.getLevel(), stack, (PlayerEntity) null,
                        source.getPos().relative(direction), SpawnReason.DISPENSER,
                        direction != Direction.UP, false);
                stack.shrink(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return stack;
        }

    };

    public EvileSpawnEgg(Item.Properties properties) {
        super(properties);

        DispenserBlock.registerBehavior(this, DISPENSER_BEHAVIOR);
    }

    /**
     * Called when this item is used when targetting a Block
     */
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getLevel();

        // If client, return
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        }

        // If server
        else {
            ItemStack itemstack = context.getItemInHand();
            BlockPos blockpos = context.getClickedPos();
            Direction direction = context.getClickedFace();
            BlockState blockstate = world.getBlockState(blockpos);
            Block block = blockstate.getBlock();
            if (block == Blocks.SPAWNER) {
                TileEntity tileentity = world.getBlockEntity(blockpos);
                if (tileentity instanceof MobSpawnerTileEntity) {
                    AbstractSpawner abstractspawner = ((MobSpawnerTileEntity) tileentity).getSpawner();
                    EntityType<?> entitytype1 = this.getEntityType();
                    abstractspawner.setEntityId(entitytype1);
                    tileentity.setChanged();
                    world.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
                    itemstack.shrink(1);
                    return ActionResultType.SUCCESS;
                }
            }

            BlockPos blockpos1;
            if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            } else {
                blockpos1 = blockpos.relative(direction);
            }

            EntityType<?> entitytype = this.getEntityType();
            if (entitytype.spawn((ServerWorld) world, itemstack, context.getPlayer(), blockpos1, SpawnReason.SPAWN_EGG, true, (!Objects.equals(blockpos, blockpos1) && direction == Direction.UP)) != null) {
                itemstack.shrink(1);
            }

            return ActionResultType.SUCCESS;
        }
    }

    /**
     * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a
     * Block, see {@link #onItemUse}.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn,
                                                    Hand handIn) {

        // Hand
        ItemStack itemstack = playerIn.getItemInHand(handIn);

        // What are they looking at
        RayTraceResult raytraceresult = getPlayerPOVHitResult(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);

        // If is not a block return pass
        if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
            return ActionResult.pass(itemstack);
        }
        // Otherwise give the client success
        else if (worldIn.isClientSide) {
            return ActionResult.success(itemstack);
        }
        // And give the server spawning logic
        else {
            BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
            BlockPos blockpos = blockraytraceresult.getBlockPos();

            // If not a fluid
            if (!(worldIn.getBlockState(blockpos).getBlock() instanceof FlowingFluidBlock)) {
                return ActionResult.pass(itemstack);
            }
            // Can the targetted block be modified by the player? Can the player edit the targetted face
            // with this itemstack?
            else if (worldIn.mayInteract(playerIn, blockpos)
                    && playerIn.mayUseItemAt(blockpos, blockraytraceresult.getDirection(), itemstack)) {

                // Type to spawn
                EntityType<?> entitytype = this.getEntityType();

                // If null spawned, pass
                if (entitytype.spawn((ServerWorld) worldIn, itemstack, playerIn, blockpos,
                        SpawnReason.SPAWN_EGG, false, false) == null) {
                    return ActionResult.pass(itemstack);
                }
                // Else shrink the stack if the player is not creative
                else {
                    if (!playerIn.abilities.mayfly) {
                        itemstack.shrink(1);
                    }

                    // Record usage and succeed
                    playerIn.awardStat(Stats.ITEM_USED.get(this));
                    return ActionResult.success(itemstack);
                }
            }

            // If the block was not modifiable, fail
            else {
                return ActionResult.fail(itemstack);
            }
        }
    }

    public abstract EntityType<?> getEntityType();

}
