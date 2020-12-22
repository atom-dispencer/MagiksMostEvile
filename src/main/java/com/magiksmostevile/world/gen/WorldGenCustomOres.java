package com.magiksmostevile.world.gen;

import java.util.Random;

import com.magiksmostevile.blocks.ores.BlockOres;
import com.magiksmostevile.handler.EnumHandler;
import com.magiksmostevile.init.EvileBlocks;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCustomOres implements IWorldGenerator {
	private WorldGenerator ore_nether_amethyst, ore_overworld_amethyst, ore_end_amethyst;
	private WorldGenerator ore_nether_lead, ore_overworld_lead, ore_end_lead;
	private WorldGenerator solid_amethyst_block;

	/**
	 * Args for each added world generator are: (The blockstate of the block you will spawn), (The max count for a vein of that block), (The block it will spawn in -- eg Stone for overworld)
	 */
	public WorldGenCustomOres() {
		// AMETHYST VARIENTS
		ore_nether_amethyst = new WorldGenMinable(EvileBlocks.ORE_NETHER.getDefaultState().withProperty(BlockOres.VARIENT, EnumHandler.EnumType.AMETHYST), 9, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_overworld_amethyst = new WorldGenMinable(EvileBlocks.ORE_OVERWORLD.getDefaultState().withProperty(BlockOres.VARIENT, EnumHandler.EnumType.AMETHYST), 9, BlockMatcher.forBlock(Blocks.STONE));
		ore_end_amethyst = new WorldGenMinable(EvileBlocks.ORE_END.getDefaultState().withProperty(BlockOres.VARIENT, EnumHandler.EnumType.AMETHYST), 9, BlockMatcher.forBlock(Blocks.END_STONE));
		
		solid_amethyst_block = new WorldGenMinable(EvileBlocks.SOLID_AMETHYST_BLOCK.getDefaultState(), 1, BlockMatcher.forBlock(Blocks.OBSIDIAN));

		// LEAD VARIENTS
		ore_nether_lead = new WorldGenMinable(EvileBlocks.ORE_NETHER.getDefaultState().withProperty(BlockOres.VARIENT, EnumHandler.EnumType.LEAD), 9, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_overworld_lead = new WorldGenMinable(EvileBlocks.ORE_OVERWORLD.getDefaultState().withProperty(BlockOres.VARIENT, EnumHandler.EnumType.LEAD), 9, BlockMatcher.forBlock(Blocks.STONE));
		ore_end_lead = new WorldGenMinable(EvileBlocks.ORE_END.getDefaultState().withProperty(BlockOres.VARIENT, EnumHandler.EnumType.LEAD), 9, BlockMatcher.forBlock(Blocks.END_STONE));

	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
		case -1:
			runGenerator(ore_nether_amethyst, world, random, chunkX, chunkZ, 25, 60, 200);
			runGenerator(ore_nether_lead, world, random, chunkX, chunkZ, 25, 5, 80);
			break;
		case 0:
			runGenerator(ore_overworld_amethyst, world, random, chunkX, chunkZ, 25, 10, 50);
			runGenerator(ore_overworld_lead, world, random, chunkX, chunkZ, 25, 5, 120);
			break;
		case 1:
			runGenerator(ore_end_amethyst, world, random, chunkX, chunkZ, 25, 5, 250);
			runGenerator(ore_end_lead, world, random, chunkX, chunkZ, 25, 5, 250);
			runGenerator(solid_amethyst_block, world, random, chunkX, chunkZ, 60, 5, 250);
			break;
		}
	}

	private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight) {
		if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256)
			throw new IllegalArgumentException("Invalid args for world generator (custom ores)! Args out of bounds.");

		int heightDiff = maxHeight - minHeight - 1;
		for (int i = 0; i < chance; i++) {
			int x = chunkX * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunkZ * 16 + rand.nextInt(16);

			gen.generate(world, rand, new BlockPos(x, y, z));
		}

	}

}
