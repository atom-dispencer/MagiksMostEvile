package com.magiksmostevile.equipment;

import com.magiksmostevile.Main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AmethystBoots extends Item {
	public AmethystBoots(String itemname) {
		this.setUnlocalizedName(itemname);
		this.setTextureName(Main.MODID + ":" + itemname);
		this.setCreativeTab(CreativeTabs.tabTools);
	}
}