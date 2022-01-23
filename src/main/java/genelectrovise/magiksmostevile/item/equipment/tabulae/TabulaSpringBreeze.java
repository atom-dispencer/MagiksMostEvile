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
package genelectrovise.magiksmostevile.item.equipment.tabulae;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.world.World;

import java.util.List;

public class TabulaSpringBreeze extends Tabula {

    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // Only operate on the server
        if (world.isClientSide) {
            return ActionResult.pass(stack);
        }

        // Make sure the player is actually holding the item!
        if (!(stack.getItem() instanceof TabulaMuddiedDust)) {
            return ActionResult.fail(stack);
        }

        // Logic
        BlockPos position = player.blockPosition();
        int lowerLimit = -1;
        int upperLimit = 1;
        for (int x = position.getX() + lowerLimit; x < position.getX() + upperLimit + 1; x++) {
            for (int z = position.getZ() + lowerLimit; z < position.getZ() + upperLimit + 1; z++) {

                BlockPos movingPosition = new BlockPos(x, position.getY() - 1, z);
                BlockState state = world.getBlockState(movingPosition);
                // If dirt and 1/4 chance
                if (state.getBlock() == Blocks.DIRT && (world.getRandom().nextBoolean()) && world.getRandom().nextBoolean()) {
                    world.setBlock(movingPosition, Blocks.GRASS_BLOCK.defaultBlockState(), 2);
                }
            }

        }

        return ActionResult.success(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextComponentUtils.fromMessage(() -> "The wind brushes your cheek and you feel invigorated..."));
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }
}
