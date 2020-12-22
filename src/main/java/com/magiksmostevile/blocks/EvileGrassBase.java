package com.magiksmostevile.blocks;

import com.magiksmostevile.init.EvileBlocks;
import com.magiksmostevile.init.EvileItems;
import com.magiksmostevile.items.EvileItemBase;

import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockMycelium;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class EvileGrassBase extends BlockMycelium {
	public EvileGrassBase(String name, Material material) {
		super();
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(EvileItemBase.tabEvile);

		EvileBlocks.EVILEBLOCKS.add(this);
		EvileItems.EVILEITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
}
