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
package genelectrovise.magiksmostevile.item.equipment.staff;

import genelectrovise.magiksmostevile.data.tag.ModdedTags;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Util;
import net.minecraft.util.text.TextComponentUtils;

public class DebuggingStaff extends Item {

  public DebuggingStaff(Properties properties) {
    super(properties);
  }

  @Override
  public ActionResultType onItemUse(ItemUseContext context) {

    context.getPlayer().sendMessage(TextComponentUtils.toTextComponent(() -> "ItemUsed"),
        Util.DUMMY_UUID);
    context.getPlayer().sendMessage(TextComponentUtils.toTextComponent(
        () -> context.getWorld().getBlockState(context.getPos()).getBlock().getTags().toString()),
        Util.DUMMY_UUID);

    if (ModdedTags.AMETHYST_ORE_SPAWNABLE == null) {
      context.getPlayer().sendMessage(TextComponentUtils.toTextComponent(() -> "#A_O_S nulled"),
          Util.DUMMY_UUID);
      return super.onItemUse(context);
    }


    Block block = context.getWorld().getBlockState(context.getPos()).getBlock();
    context.getPlayer().sendMessage(
        TextComponentUtils.toTextComponent(() -> "Block is in A_O_S: "
            + new Boolean(ModdedTags.AMETHYST_ORE_SPAWNABLE.contains(block)).toString()),
        Util.DUMMY_UUID);

    return super.onItemUse(context);
  }

}
