package com.magiksmostevile.blocks;

import java.util.Random;

import com.magiksmostevile.Main;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AmethystOre extends Block
    {
    	private Item toDrop;
    	
    	public AmethystOre(Material material, String blockName, Item toDrop)
    	{
    		super(material);
    		this.setBlockName(blockName);
    		this.setBlockTextureName(Main.MODID + ":" + blockName);
    		this.setCreativeTab(CreativeTabs.tabBlock);
    		this.setHardness(4.0F);
    		this.setHarvestLevel("pickaxe", 1);
    		this.toDrop = toDrop;
    	}
    	
    	@Override
    	public Item getItemDropped(int i, Random random, int j)
    	{
    		if(toDrop !=null)
    		{
    			return toDrop;
    		}
    		else return Item.getItemFromBlock(this);
    	}
    }