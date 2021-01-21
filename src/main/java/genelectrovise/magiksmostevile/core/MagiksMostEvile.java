package genelectrovise.magiksmostevile.core;

import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import genelectrovise.magiksmostevile.core.setup.RegistryCreationManager;
import genelectrovise.magiksmostevile.particle.ParticleClientStartup;
import genelectrovise.magiksmostevile.particle.ParticleCommonStartup;
import genelectrovise.magiksmostevile.registry.orbital.OrbitalRegistryGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MagiksMostEvile.MODID)
@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MagiksMostEvile {
  public static final Logger LOGGER = LogManager.getLogger();
  public static final String MODID = "magiksmostevile";
  public static final String VERSION = "alpha-1.0";
  public static final String NAME = "Magiks Most Evile";
  public static final String ACCEPTED_VERSIONS = "1.16.4";

  public static IEventBus MOD_EVENT_BUS;
  public static IEventBus FORGE_EVENT_BUS;

  public MagiksMostEvile() {
    LOGGER.info("Magiks Most Evile is being loaded by FML");

    MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
    FORGE_EVENT_BUS = MinecraftForge.EVENT_BUS;

    registerCommonEvents();

    // Handle orbital registries
    new OrbitalRegistryGenerator(OrbitalRegistryGenerator.REFLECTIONS_CONFIGURATION)
        .collectOrbitalRegistries();

  }

  @SubscribeEvent
  public static void createRegistries(RegistryEvent.NewRegistry event) {
    LOGGER.debug("Creating MME registries");
    new RegistryCreationManager().create();
  }

  @SubscribeEvent
  public static void registerClientOnlyEvents(final FMLClientSetupEvent event) {
    LOGGER.debug("Registering MME client only events");
    MOD_EVENT_BUS.register(ParticleClientStartup.class);
  }

  @SubscribeEvent
  public void onServerStarting(FMLServerStartingEvent event) {
    LOGGER.info("HELLO from server starting");
  }

  @SubscribeEvent
  public static void processIMC(final InterModProcessEvent event) {
    LOGGER.info(MagiksMostEvile.MODID + " recieved IMC {}", event.getIMCStream().map(m -> m.getMessageSupplier().get()).collect(Collectors.toList()));
  }

  @SubscribeEvent
  public static void enqueueIMC(final InterModEnqueueEvent event) {
    InterModComms.sendTo("examplemod", "helloworld", () -> {
      LOGGER.info("Hello world from the MDK");
      return "Hello world";
    });
  }

  public static void registerCommonEvents() {
    LOGGER.debug("Registering MME common events");
    MOD_EVENT_BUS.register(ParticleCommonStartup.class);
    MOD_EVENT_BUS.register(SetupManager.class);
  }

}
