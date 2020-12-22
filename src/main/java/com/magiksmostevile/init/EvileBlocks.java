package com.magiksmostevile.init;

import java.util.ArrayList;
import java.util.List;

import com.magiksmostevile.blocks.AltarBase;
import com.magiksmostevile.blocks.AmethystCrystalBase;
import com.magiksmostevile.blocks.BlockBase;
import com.magiksmostevile.blocks.WhatBlockBase;
import com.magiksmostevile.blocks.ores.BlockOres;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class EvileBlocks {
	public static final List<Block> EVILEBLOCKS = new ArrayList<Block>();
	
	public static final Block SOLID_AMETHYST_BLOCK = new BlockBase("solid_amethyst_block", Material.ROCK);
	public static final Block ALTAR = new AltarBase("altar", Material.GLASS);
	public static final Block WHAT_BLOCK = new WhatBlockBase("what_block", Material.CLAY);
	public static final Block AMETHYST_CRYSTAL = new AmethystCrystalBase("amethyst_crystal", Material.GLASS);
	
	//ORES
	public static final Block ORE_END = new BlockOres("ore_end", "end");
	public static final Block ORE_OVERWORLD = new BlockOres("ore_overworld", "overworld");
	public static final Block ORE_NETHER = new BlockOres("ore_nether", "nether");


}
