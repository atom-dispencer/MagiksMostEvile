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

import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.world.World;

public class TabulaMuddiedDust extends Tabula {

  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    ItemStack stack = player.getHeldItem(hand);

    // Only operate on the server
    if (world.isRemote) {
      return ActionResult.resultPass(stack);
    }

    // Make sure the player is actually holding the item!
    if (!(stack.getItem() instanceof TabulaMuddiedDust)) {
      return ActionResult.resultFail(stack);
    }

    // Logic
    BlockPos position = player.getPosition();
    int lowerLimit = -1;
    int upperLimit = 1;
    for (int x = position.getX() + lowerLimit; x < position.getX() + upperLimit + 1; x++) {
      for (int z = position.getZ() + lowerLimit; z < position.getZ() + upperLimit + 1; z++) {

        BlockPos movingPosition = new BlockPos(x, position.getY(), z);
        BlockState state = world.getBlockState(movingPosition);
        if (state.getBlock() == Blocks.FARMLAND) {
          world.setBlockState(movingPosition, state.with(FarmlandBlock.MOISTURE, Integer.valueOf(7)), 2);
        }
      }
    }


    return ActionResult.resultSuccess(stack);
  }

  @Override
  public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
    tooltip.add(TextComponentUtils.toTextComponent(() -> "The crops are dry and the ground is parched... You wish for rain..."));
    super.addInformation(stack, worldIn, tooltip, flagIn);
  }
}
