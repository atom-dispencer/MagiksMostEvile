package genelectrovise.magiksmostevile.common.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import genelectrovise.magiksmostevile.common.main.registry.EvileRegistryEventHandler;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

//@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION, value = "")

@Mod(Main.MODID)
@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Main {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "magiksmostevile";
	public static final String VERSION = "1.0";
	public static final String NAME = "MagiksMostEvile";
	public static final String ACCEPTED_VERSIONS = "1.15.2";

	// public static Main instance = new Main();
	
	public static EvileRegistryEventHandler evileRegistryEventHandler;

	public Main() {
		LOGGER.info(
				"Welcome to Magiks Most Evile! Hello from the Main class! (If you can see this message, Magiks Most Evile is being loaded by Forge! Woohoo!)");
		Main.LOGGER.debug("Log Key 182927012020 : FMLJavaModLoadingContext = " + FMLJavaModLoadingContext.get());

		new EvileDeferredRegistry();
		evileRegistryEventHandler = new EvileRegistryEventHandler(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	@SubscribeEvent
	public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
		evileRegistryEventHandler.eventCalled(event);
	}

	@SubscribeEvent
	public static void FMLLoadCompleteEvent(FMLLoadCompleteEvent event) {
		EvileDeferredRegistry.addOres();
	}

}
