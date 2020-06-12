/**
 * 
 */
package genelectrovise.magiksmostevile.common.main.registry;

import java.util.Iterator;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.main.reference.StructureReference;
import genelectrovise.magiksmostevile.common.world.gen.ore.EvileOreGeneration;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine2.Shrine2;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine2.ShrinePiece;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

/**
 * @author GenElectrovise 12 Jun 2020
 */
@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WorldGenerationRegistry {

	/**
	 * Contains the {@link ObjectHolder}s for {@link MagiksMostEvile}
	 * 
	 * @author GenElectrovise 12 Jun 2020
	 */
	@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class ObjectHolders {
		@ObjectHolder(MagiksMostEvile.MODID + ":shrine")
		public static Structure<NoFeatureConfig> SHRINE_CENTRE;
	}

	/**
	 * A class containing the methods to register and add shrines to the world
	 * 
	 * @author GenElectrovise 12 Jun 2020
	 */
	@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Shrine {

		@SubscribeEvent
		public static void registerShrines(RegistryEvent.Register<Feature<?>> event) {
			MagiksMostEvile.LOGGER.debug("Registering shrines!");
			Shrine2.CENTRE_PIECE = Registry.register(Registry.STRUCTURE_PIECE, StructureReference.SHRINE_LOC, ShrinePiece.ShrineCentrePiece::new);
			event.getRegistry().register(new Shrine2(NoFeatureConfig::deserialize).setRegistryName(StructureReference.SHRINE_LOC));

		}

		@SuppressWarnings("deprecation")
		@SubscribeEvent
		public static void addShrines(FMLCommonSetupEvent event) {
			MagiksMostEvile.LOGGER.debug("Adding shrines to Biomes!");
			DeferredWorkQueue.runLater(() -> {
				Iterator<Biome> biomes = ForgeRegistries.BIOMES.iterator();
				biomes.forEachRemaining((biome) -> {
					biome.addStructure(ObjectHolders.SHRINE_CENTRE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
					biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, ObjectHolders.SHRINE_CENTRE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
				});
			});
		}
	}

	/**
	 * Adds ores to the world.
	 * 
	 * @author GenElectrovise 12 Jun 2020
	 */
	@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Ore {

		@SubscribeEvent
		public static void addOres(FMLLoadCompleteEvent event) {
			MagiksMostEvile.LOGGER.debug("Adding ores!");
			EvileOreGeneration.addOverworldOres();
			EvileOreGeneration.addNetherOres();
			// EvileOreGeneration.addEndOres();
		}
	}
}
