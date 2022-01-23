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
    public ActionResultType useOn(ItemUseContext context) {

        context.getPlayer().sendMessage(TextComponentUtils.fromMessage(() -> "ItemUsed"), Util.NIL_UUID);
        context.getPlayer().sendMessage(TextComponentUtils.fromMessage(() -> context.getLevel().getBlockState(context.getClickedPos()).getBlock().getTags().toString()), Util.NIL_UUID);

        if (ModdedTags.AMETHYST_ORE_SPAWNABLE == null) {
            context.getPlayer().sendMessage(TextComponentUtils.fromMessage(() -> "#A_O_S nulled"), Util.NIL_UUID);
            return super.useOn(context);
        }

        Block block = context.getLevel().getBlockState(context.getClickedPos()).getBlock();
        context.getPlayer().sendMessage(TextComponentUtils.fromMessage(() -> "Block is in A_O_S: " + Boolean.valueOf(ModdedTags.AMETHYST_ORE_SPAWNABLE.contains(block)).toString()), Util.NIL_UUID);

        return super.useOn(context);
    }

}
