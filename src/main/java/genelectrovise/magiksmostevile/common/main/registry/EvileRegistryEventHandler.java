package genelectrovise.magiksmostevile.common.main.registry;

import java.util.Iterator;

import javax.annotation.Nonnull;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.main.reference.StructureReference;
import genelectrovise.magiksmostevile.common.tileentity.altar.AltarContainer;
import genelectrovise.magiksmostevile.common.tileentity.altar.AltarTileEntity;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine2.Shrine2;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine2.ShrinePiece;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

public class EvileRegistryEventHandler {

	public EvileRegistryEventHandler(IEventBus modEventBus) {
		modEventBus.addListener(this::addShrineToBiomes);
	}

	//////////////////////////////// FEATURES

	/**
	 * Calls other methods to register and add structures.
	 * 
	 * @param event
	 */
	public void features(Register<Feature<?>> event) {
		registerShrines(event);
	}

	// Shrine

	@ObjectHolder(MagiksMostEvile.MODID + ":shrine")
	public static Structure<NoFeatureConfig> SHRINE_CENTRE;

	@SuppressWarnings("deprecation")
	public void addShrineToBiomes(FMLCommonSetupEvent event) {
		DeferredWorkQueue.runLater(() -> {
			MagiksMostEvile.LOGGER.debug("Adding shrines to Biomes! ==1==");
			Iterator<Biome> biomes = ForgeRegistries.BIOMES.iterator();
			biomes.forEachRemaining((biome) -> {
				MagiksMostEvile.LOGGER.debug(" > Adding shrine to Biome : " + biome);
				biome.addStructure(SHRINE_CENTRE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
				biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, SHRINE_CENTRE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
			});
		});
	}

	/**
	 * Adds the shrine structure to the registry and all biomes
	 * 
	 * @param event
	 */
	public void registerShrines(RegistryEvent.Register<Feature<?>> event) {
		MagiksMostEvile.LOGGER.debug("Registering shrines ==2==");
		Shrine2.CENTRE_PIECE = Registry.register(Registry.STRUCTURE_PIECE, StructureReference.SHRINE_LOC, ShrinePiece.ShrineCentrePiece::new);
		event.getRegistry().register(new Shrine2(NoFeatureConfig::deserialize).setRegistryName(StructureReference.SHRINE_LOC));

	}

	public void containers(@Nonnull final RegistryEvent.Register<ContainerType<?>> event) {
		
	}

}
