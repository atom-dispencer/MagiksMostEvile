package com.magiksmostevile.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class WhatBlockBase extends BlockBase {

	public WhatBlockBase(String name, Material material) {
		super(name, material);
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		Random random = new Random();
		Item toDrop = Item.REGISTRY.getRandomObject(random);
		return toDrop;
	}

	public int quantityDropped(IBlockState state, int fortune, Random random) {
		return random.nextInt(2) + 1 + fortune; // <--- This will drop 1 to 2 items, and the +1 is so its not from 0 to 2.
	}

}
