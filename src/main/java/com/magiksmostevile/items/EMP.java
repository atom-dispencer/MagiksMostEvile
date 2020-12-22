package com.magiksmostevile.items;

import com.magiksmostevile.Main;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EMP extends Item
    {
    	public EMP(String itemname)
    	{
    		this.setUnlocalizedName(itemname);
    		this.setTextureName(Main.MODID + ":" + itemname);
    		this.setCreativeTab(CreativeTabs.tabMisc);

    	}

	@Override

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		
	return true;
	}
}