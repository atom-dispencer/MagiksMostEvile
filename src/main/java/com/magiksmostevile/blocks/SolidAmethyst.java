package com.magiksmostevile.blocks;


import com.magiksmostevile.Main;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class SolidAmethyst extends Block
    {
		public SolidAmethyst(Material material, String blockName)
		{
			super(material);
			this.setBlockName(blockName);
			this.setBlockTextureName(Main.MODID + ":" + blockName);
			this.setCreativeTab(CreativeTabs.tabBlock);
			this.setHardness(4.0F);
			this.setHarvestLevel("pickaxe", 1);
		}
    }