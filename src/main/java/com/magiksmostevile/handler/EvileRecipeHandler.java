package com.magiksmostevile.handler;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EvileRecipeHandler
{
	public static void init(Item amethyst, Block solidAmethyst, Block alter, Item lesserPowerStone, Block end_stone, Item stick, Item amethystSword, Item amethystPickaxe, Item amethystAxe, Item amethystHoe, Item amethystShovel)
	{
		GameRegistry.addRecipe(
				new ItemStack(solidAmethyst),
				"AAA",
				"AAA",
				"AAA",
				'A', amethyst
				);
		
		GameRegistry.addRecipe(
				new ItemStack(alter),
				"AAA",
				"ELE",
				"EEE",
				'A', amethyst,
				'E', end_stone,
				'L', lesserPowerStone
				);
		
		GameRegistry.addRecipe(
				new ItemStack(amethystSword),
				" A ",
				" A ",
				" S ",
				'A', amethyst,
				'S', stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(amethystPickaxe),
				"AAA",
				" S ",
				" S ",
				'A', amethyst,
				'S', stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(amethystAxe),
				"AA ",
				"AS ",
				" S ",
				'A', amethyst,
				'S', stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(amethystHoe),
				"AA ",
				" S ",
				" S ",
				'A', amethyst,
				'S', stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(amethystShovel),
				" A ",
				" S ",
				" S ",
				'A', amethyst,
				'S', stick
				);
		
		//GameRegistry.addShapelessRecipe(new ItemStack(new SolidAmethyst(Material.rock, "solidAmethyst")), );
		
		
		
//EXAMPLE GameRegistry.addRecipe(new ItemStack(ModBlocks.red_diamond_block), "XXX", "XXX", "XXX", 'X', ModItems.red_diamond);

	}
	

}
