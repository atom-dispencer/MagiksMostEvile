package com.magiksmostevile.items;

import com.magiksmostevile.Main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class GreaterPowerStone extends Item
    {
    	public GreaterPowerStone(String itemname)
    	{
    		this.setUnlocalizedName(itemname);
    		this.setTextureName(Main.MODID + ":" + itemname);
    		this.setCreativeTab(CreativeTabs.tabMisc);
    	}
    }