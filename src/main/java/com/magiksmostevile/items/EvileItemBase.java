package com.magiksmostevile.items;

import com.magiksmostevile.Main;
import com.magiksmostevile.init.EvileItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import util.IHasModel;

public class EvileItemBase extends Item implements IHasModel {
	public static final CreativeTabs tabEvile = (new CreativeTabs("tabEvile") {
		
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(EvileItems.AMETHYST);
		}
	});
	
	/*
	public boolean hasEffect(ItemStack stack, boolean doesHaveEffect) {
		if(doesHaveEffect) {
			return true;
		}
		else {
			return false;
		}
	}*/
	
	/**
	 * 
	 * @param name Registry & Unlocalised name of item
	 */
	public EvileItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(EvileItemBase.tabEvile);
		
		EvileItems.EVILEITEMS.add(this);
	}
	
	/**
	 * 
	 * @param name Registry & Unlocalised name of item
	 * @param maxStackSize Max stack size of the item. Shouldn't really be over 64 as Minecraft doesn't really like it.
	 */
	public EvileItemBase(String name, int maxStackSize) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(EvileItemBase.tabEvile);

		setMaxStackSize(maxStackSize);
		
		EvileItems.EVILEITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory"); 
	}

}
