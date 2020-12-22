package com.magiksmostevile.items;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EvileGlowingItemBase extends EvileItemBase {

	public EvileGlowingItemBase(String name) {
		super(name);
	}
	public EvileGlowingItemBase(String name, int maxStackSize) {
		super(name, maxStackSize);
	}
	
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

}
