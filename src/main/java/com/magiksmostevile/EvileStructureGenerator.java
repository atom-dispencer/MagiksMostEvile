package com.magiksmostevile;

import java.util.Random;

import com.magiksmostevile.microZ.SchemUtils;
import com.magiksmostevile.microZ.SchemUtils.Schematic;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

public class EvileStructureGenerator implements IWorldGenerator {

	private static final int SHRINE_SPAWN_CHANCE = 200;
	private static final int TEMPLE_SPAWN_CHANCE = 1000; //TODO FIX TEMPLE CLUSTERING
	boolean toggleTempleSpawn = true;
	boolean testSchem = false;


	//private ItemStack PopulateEvileChest(Random random, IInventory newinventory)
	//{
	//	WeightedRandomChestContent.generateChestContents(random, Item.getIdFromItem(Main.amethyst), 1, 10); //FROM WeightedRandomChestContent.java 
	//}
	
	
	
	
	
	
	private SchemUtils sl = new SchemUtils();

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.dimensionId)
		{
		case -1:
			break;
		case 0:
			generateOverworld(world, random, chunkX * 16, chunkZ * 16);
		case 1:
			break;
		}
	}
	
	private int fetchTopBlock(World world, int x, int z, int minHeight, boolean ignoreFluids, boolean ignoreWood, boolean ignoreFoliage) {
		return fetchTopBlock(world, x, z, minHeight, ignoreFluids, ignoreWood, ignoreFoliage, true);
	}
	
	private int fetchTopBlock(World world, int x, int z, int minHeight, boolean ignoreFluids, boolean ignoreWood, boolean ignoreFoliage, boolean ignoreAir)
	{
		int currentTop = world.getHeightValue(x, z);
		boolean thisOne;
		Block blockBelow;
		for (int i = currentTop; i > minHeight; i--) {
			blockBelow = world.getBlock(x, i-1, z);
			thisOne = true;
			EvileLog.LogText(false, 1, "AAA");
			if (ignoreFluids && (blockBelow.getMaterial() == Material.water || blockBelow.getMaterial() == Material.lava)) {
				EvileLog.LogText(false, 1, i + "BBB");
				thisOne = false;
			}
			if (ignoreWood && blockBelow instanceof BlockLog) {
				thisOne = false;
			}
			if (ignoreFoliage && (blockBelow.getMaterial() == Material.leaves || blockBelow.isFoliage(world, x, i-1, z) || blockBelow.getMaterial() == Material.plants)) {
				thisOne = false;
			}
			if (ignoreAir && blockBelow.getMaterial() == Material.air) {
				thisOne = false;
			}
			if (thisOne) {
				return i;
			}
		}
		
		return minHeight+1;
	}

	private void generateOverworld(World world, Random random, int x, int z)
	{
		int randomX = x + random.nextInt(16);
		int randomZ = z + random.nextInt(16);	
		BiomeGenBase theBiome = world.getBiomeGenForCoords(randomX, randomZ);
		boolean inWater = (theBiome == BiomeGenBase.river || theBiome == BiomeGenBase.ocean || theBiome == BiomeGenBase.deepOcean || theBiome == BiomeGenBase.beach);
		
		if (random.nextInt(10000) % SHRINE_SPAWN_CHANCE == 0 && theBiome != BiomeGenBase.desertHills){
			
			int minHeight = 24;
			
			Block theBlock = Blocks.sponge;

			if (inWater) {
				minHeight = 10;
				theBlock = Blocks.glowstone;
			}
			
			int randomY = fetchTopBlock(world, randomX, randomZ, minHeight, true, true, true);//if you want to not ignoreAir, add false at the end. otherwise it is true by default

			SchemUtils su = new SchemUtils();
			for(int chanceForChuk = 0; chanceForChuk < 1; chanceForChuk++) {
	        	Schematic shrine = su.get("shrine.schematic");
	        	EvileLog.LogText(false, 1, "Back to Evile Structure Generator");
	        	if (shrine != null) {
		        	EvileLog.LogText(false, 1, "Back to Evile Structure Generator");

	        	}

	        	 int i = 0;
	             for(int cy = 0; cy < shrine.height; cy++)
	             for(int cz = 0; cz < shrine.length; cz++)
	             for(int cx = 0; cx < shrine.width; cx++){

	                     Block b = Block.getBlockById(shrine.blocks[i]);
	                     if(b!= Blocks.air)
	                     {
	                         world.setBlockToAir(cx + randomX , cy + randomY - 5, cz + randomZ);
	                         world.setBlock(cx + randomX, cy + randomY - 5, cz + randomZ, b, shrine.data[i] , 2);
	                     }
	                     i++;
	             }
	        }
				
			/*
			//MC DRIECTIONS: SOUTH= +Z EAST= +X
			for (int i = -2; i <= 2; i++) { //X offset
				for (int j = 0; j <= 4; j++) { //Y offset
					for (int k = -2; k <= 2; k++) { //Z Offset
						if (j == 0) { //Bottom layer
							world.setBlock(randomX+i, randomY+j, randomZ+k, Blocks.cobblestone);
						}
						else {
						world.setBlock(randomX+i, randomY+j, randomZ+k, theBlock);
						
						}
					}
				}
			}*/
		}
		if (random.nextInt(10000) % TEMPLE_SPAWN_CHANCE == 0 && theBiome != BiomeGenBase.desertHills && toggleTempleSpawn != false){
			
			int minHeight = 62;
			
			Block theBlock = Blocks.sponge;

			if (inWater) {
				minHeight = 10;
				theBlock = Blocks.glowstone;
			}
			
			int randomY = fetchTopBlock(world, randomX, randomZ, minHeight, true, true, true);//if you want to not ignoreAir, add false at the end. otherwise it is true by default

			SchemUtils su = new SchemUtils();
			for(int chanceForChuk = 0; chanceForChuk < 1; chanceForChuk++) {
	        	Schematic skytemple = su.get("cobbleskytemple.schematic");
	        	EvileLog.LogText(false, 1, "Returning to Evile Structure Generator");
	        	if (skytemple != null) {
	            	EvileLog.LogText(false, 1, "Got Schematic");

	        	}

	        	 int i = 0;
	             for(int cy = 0; cy < skytemple.height; cy++)
	             for(int cz = 0; cz < skytemple.length; cz++)
	             for(int cx = 0; cx < skytemple.width; cx++){

	                     Block b = Block.getBlockById(skytemple.blocks[i]);
	                     if(b!= Blocks.air)
	                     {
	                         world.setBlockToAir(cx + randomX , cy + randomY, cz + randomZ);
	                         world.setBlock(cx + randomX, cy + randomY, cz + randomZ, b, skytemple.data[i] , 2);
	                     }
	                     i++;
	             }
	        }
		}
		/*if (testSchem = true){
			
			int minHeight = 62;
			
			Block theBlock = Blocks.sponge;

			if (inWater) {
				minHeight = 10;
				theBlock = Blocks.glowstone;
			}
			
			int randomY = fetchTopBlock(world, randomX, randomZ, minHeight, true, true, true);//if you want to not ignoreAir, add false at the end. otherwise it is true by default

			SchemUtils su = new SchemUtils();
			for(int chanceForChuk = 0; chanceForChuk < 1; chanceForChuk++) {
	        	Schematic testschem = su.get("testschem.schematic");
	        	EvileLog.LogText(false, 1, "Back to Evile Structure Generator");
	        	if (testschem != null) {
	        		EvileLog.LogText(false, 1, "Got Schematic");

	        	}

	        	 int i = 0;
	             for(int cy = 0; cy < testschem.height; cy++)
	             for(int cz = 0; cz < testschem.length; cz++)
	             for(int cx = 0; cx < testschem.width; cx++){

	                     Block b = Block.getBlockById(testschem.blocks[i]);
	                     if(b!= Blocks.air)
	                     {
	                         world.setBlockToAir(cx + randomX , cy + randomY, cz + randomZ);
	                         world.setBlock(cx + randomX, cy + randomY, cz + randomZ, b, testschem.data[i] , 2);
	                     }
	                     i++;
	             }
	        }
		}*/
		
	}
}






























/*
package com.magiksmostevile;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

public class EvileStructureGenerator implements IWorldGenerator {

	private static final int SPAWN_CHANCE = 5;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.dimensionId)
		{
		case -1:
			break;
		case 0:
			generateOverworld(world, random, chunkX * 16, chunkZ * 16);
		case 1:
			break;
		}
	}
	
	private int fetchTopBlock(World world, int x, int z, int minHeight, boolean ignoreFluids, boolean ignoreWood, boolean ignoreFoliage) {
		return fetchTopBlock(world, x, z, minHeight, ignoreFluids, ignoreWood, ignoreFoliage, true);
	}
	
	private int fetchTopBlock(World world, int x, int z, int minHeight, boolean ignoreFluids, boolean ignoreWood, boolean ignoreFoliage, boolean ignoreAir)
	{
		int currentTop = world.getHeightValue(x, z);
		boolean thisOne;
		Block blockBelow;
		for (int i = currentTop; i > minHeight; i--) {
			blockBelow = world.getBlock(x, i-1, z);
			thisOne = true;
	        	EvileLog.LogText(false, 1, "AAA");
			if (ignoreFluids && (blockBelow.getMaterial() == Material.water || blockBelow.getMaterial() == Material.lava)) {
				EvileLog.LogText(i + "BBB");
				thisOne = false;
			}
			if (ignoreWood && blockBelow instanceof BlockLog) {
				thisOne = false;
			}
			if (ignoreFoliage && (blockBelow.getMaterial() == Material.leaves || blockBelow.isFoliage(world, x, i-1, z) || blockBelow.getMaterial() == Material.plants)) {
				thisOne = false;
			}
			if (ignoreAir && blockBelow.getMaterial() == Material.air) {
				thisOne = false;
			}
			if (thisOne) {
				return i;
			}
		}
		
		return minHeight+1;
	}

	private void generateOverworld(World world, Random random, int x, int z)
	{
		int randomX = x + random.nextInt(16);
		int randomZ = z + random.nextInt(16);	
		BiomeGenBase theBiome = world.getBiomeGenForCoords(randomX, randomZ);
		boolean inWater = (theBiome == BiomeGenBase.river || theBiome == BiomeGenBase.ocean || theBiome == BiomeGenBase.deepOcean || theBiome == BiomeGenBase.beach);
		if (random.nextInt(100) % SPAWN_CHANCE == 0 && theBiome != BiomeGenBase.desertHills){
			
			int minHeight = 62;
			
			Block theBlock = Blocks.sponge;

			if (inWater) {
				minHeight = 10;
				theBlock = Blocks.glowstone;
			}
			
			int randomY = fetchTopBlock(world, randomX, randomZ, minHeight, true, true, true);//if you want to not ignoreAir, add false at the end. otherwise it is tru by default

			
			world.setBlock(randomX, randomY, randomZ, theBlock);
		}
	}
}


 */