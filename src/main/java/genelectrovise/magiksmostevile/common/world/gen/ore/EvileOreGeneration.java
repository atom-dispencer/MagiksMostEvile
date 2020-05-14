package genelectrovise.magiksmostevile.common.world.gen.ore;

import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class EvileOreGeneration {

	public static void addOverworldOres() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, EvileDeferredRegistry.AMETHYST_ORE_OVERWORLD.get().getDefaultState(), 7)).withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(1, 100, 25))));

		}
	}

	public static void addNetherOres() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			if (biome == Biomes.NETHER) {
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, EvileDeferredRegistry.AMETHYST_ORE_NETHER.get().getDefaultState(), 7)).withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(1, 100, 25))));

			}
		}
	}

	/*
	 * public static void addEndOres() { for (Biome biome : ForgeRegistries.BIOMES)
	 * { if (biome == Biomes.END_BARRENS || biome == Biomes.END_HIGHLANDS || biome
	 * == Biomes.END_MIDLANDS || biome == Biomes.SMALL_END_ISLANDS || biome ==
	 * Biomes.THE_END || biome == Biomes.THE_VOID) {
	 * biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION,
	 * Feature.ORE .func_225566_b_(new
	 * OreFeatureConfig(OreFeatureConfig.FillerBlockType.create("end_stone",
	 * Blocks.END_STONE, new BlockMatcher(Blocks.END_STONE)),
	 * EvileRegistry.AMETHYST_ORE_END.get().getDefaultState(), 7)) .func_227228_a_(
	 * Placement.COUNT_DEPTH_AVERAGE.func_227446_a_(new DepthAverageConfig(1, 100,
	 * 25))));
	 * 
	 * } } }
	 */
}
