package genelectrovise.magiksmostevile.common.core;

import org.apache.logging.log4j.LogManager;
import genelectrovise.magiksmostevile.common.core.registry.orbital.OrbitalRegistryGenerator;
import genelectrovise.magiksmostevile.common.core.setup.RegistryCreationManager;
import genelectrovise.magiksmostevile.common.particle.ParticleClientStartup;
import genelectrovise.magiksmostevile.common.particle.ParticleCommonStartup;
import genelectrovise.magiksmostevile.common.world.gen.structure.StructureRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MagiksMostEvile.MODID)
@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MagiksMostEvile {
  public static final EvileLog LOGGER = new EvileLog(LogManager.getLogger());
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
    DistExecutor.runWhenOn(Dist.CLIENT, () -> MagiksMostEvile::registerClientOnlyEvents);

    // Handle orbital registries
    new OrbitalRegistryGenerator(OrbitalRegistryGenerator.REFLECTIONS_CONFIGURATION)
        .collectOrbitalRegistries();

    StructureRegistry.init(FMLJavaModLoadingContext.get().getModEventBus());
  }

  @SubscribeEvent
  public static void createRegistries(RegistryEvent.NewRegistry event) {
    LOGGER.debug("Creating MME registries");
    new RegistryCreationManager().create();
  }

  public static void registerCommonEvents() {
    LOGGER.debug("Registering MME common events");
    MOD_EVENT_BUS.register(ParticleCommonStartup.class);
    MOD_EVENT_BUS.register(SetupManager.class);
  }

  public static void registerClientOnlyEvents() {
    LOGGER.debug("Registering MME client only events");
    MOD_EVENT_BUS.register(ParticleClientStartup.class);
  }

}
