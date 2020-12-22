package com.magiksmostevile.equipment;

import com.magiksmostevile.Main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AmethystChestplate extends Item {
	public AmethystChestplate(String itemname) {
		this.setUnlocalizedName(itemname);
		this.setTextureName(Main.MODID + ":" + itemname);
		this.setCreativeTab(CreativeTabs.tabTools);
	}
}