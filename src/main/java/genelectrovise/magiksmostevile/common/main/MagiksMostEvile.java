package genelectrovise.magiksmostevile.common.main;

import org.apache.logging.log4j.LogManager;

import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import genelectrovise.magiksmostevile.common.main.registry.EvileRegistryEventHandler;
import genelectrovise.magiksmostevile.common.network.altar.AltarNetworkingManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MagiksMostEvile.MODID)
@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MagiksMostEvile {
	public static final EvileLog LOGGER = new EvileLog(LogManager.getLogger());
	public static final String MODID = "magiksmostevile";
	public static final String VERSION = "1.0";
	public static final String NAME = "MagiksMostEvile";
	public static final String ACCEPTED_VERSIONS = "1.15.2";

	public static EvileRegistryEventHandler evileRegistryEventHandler;

	public MagiksMostEvile() {
		LOGGER.info("Welcome to Magiks Most Evile! Hello from the Main class! (If you can see this message, Magiks Most Evile is being loaded by Forge! Woohoo!)");
		MagiksMostEvile.LOGGER.debug("Log Key 182927012020 : FMLJavaModLoadingContext = " + FMLJavaModLoadingContext.get());

		registerEventListeners(MinecraftForge.EVENT_BUS);
		new EvileDeferredRegistry();
		evileRegistryEventHandler = new EvileRegistryEventHandler(FMLJavaModLoadingContext.get().getModEventBus());

	}

	@SubscribeEvent
	public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
		evileRegistryEventHandler.features(event);
	}

	@SubscribeEvent
	public static void registerContainers(RegistryEvent.Register<ContainerType<?>> event) {
		evileRegistryEventHandler.containers(event);
	}

	@SubscribeEvent
	public static void FMLLoadCompleteEvent(FMLLoadCompleteEvent event) {
		EvileDeferredRegistry.addOres();
		EvileDeferredRegistry.createContainerFactories();
	}

	private static void registerEventListeners(IEventBus bus) {
		MagiksMostEvile.LOGGER.debug("==MME1== Registering Event Listeners to Forge EventBus");

	}

	@SubscribeEvent
	public static void networking(FMLCommonSetupEvent event) {
		AltarNetworkingManager.onCommonSetupEvent(event);
	}
	
	@SubscribeEvent
	public static void renderers(FMLClientSetupEvent event) {
		EvileDeferredRegistry.renderers(event);
	}

}
