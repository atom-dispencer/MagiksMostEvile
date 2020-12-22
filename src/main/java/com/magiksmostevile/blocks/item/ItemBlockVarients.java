package com.magiksmostevile.blocks.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import util.IMetaName;

public class ItemBlockVarients extends ItemBlock {

	public ItemBlockVarients(Block block) {
		super(block);
		setHasSubtypes(true);
		setMaxDamage(0);

	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "_" + ((IMetaName) this.block).getSpecialName(stack);
	}
}
