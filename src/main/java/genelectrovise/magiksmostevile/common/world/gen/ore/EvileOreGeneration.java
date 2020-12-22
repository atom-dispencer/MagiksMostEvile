package genelectrovise.magiksmostevile.common.world.gen.ore;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class EvileOreGeneration {

	public static void addOverworldOres() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			MagiksMostEvile.LOGGER.debug("Adding overworld amethyst ore to: " + biome);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, EvileDeferredRegistry.AMETHYST_ORE_OVERWORLD.get().getDefaultState(), 12)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 128))));
		}
	}

	public static void addNetherOres() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			if (biome == Biomes.NETHER) {
				MagiksMostEvile.LOGGER.debug("Adding nether amethyst ore to: " + biome);
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, EvileDeferredRegistry.AMETHYST_ORE_NETHER.get().getDefaultState(), 12)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 128))));
			}
		}
	}
}
