package com.magiksmostevile.items;

import com.magiksmostevile.Main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class LesserPowerStone extends Item
    {
    	public LesserPowerStone(String itemname)
    	{
    		this.setUnlocalizedName(itemname);
    		this.setTextureName(Main.MODID + ":" + itemname);
    		this.setCreativeTab(CreativeTabs.tabMisc);
    	}
    }