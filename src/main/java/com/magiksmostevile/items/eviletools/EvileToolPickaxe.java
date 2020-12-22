package com.magiksmostevile.items.eviletools;

import com.magiksmostevile.Main;
import com.magiksmostevile.init.EvileItems;
import com.magiksmostevile.items.EvileItemBase;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import util.IHasModel;

public class EvileToolPickaxe extends ItemPickaxe implements IHasModel{
	
	public EvileToolPickaxe(String name, ToolMaterial material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(EvileItemBase.tabEvile);
		
		EvileItems.EVILEITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory"); 
	}

}
