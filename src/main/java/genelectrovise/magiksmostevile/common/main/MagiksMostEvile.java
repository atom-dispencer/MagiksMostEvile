package genelectrovise.magiksmostevile.common.main;

import org.apache.logging.log4j.LogManager;
import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import genelectrovise.magiksmostevile.common.main.setup.RegistryCreationManager;
import genelectrovise.magiksmostevile.common.particle.ParticleClientStartup;
import genelectrovise.magiksmostevile.common.particle.ParticleCommonStartup;
import net.minecraftforge.api.distmarker.Dist;
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

  public static IEventBus EVENT_BUS;

  public MagiksMostEvile() {
    LOGGER.info("Magiks Most Evile is being loaded by FML");

    EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();

    registerCommonEvents();
    DistExecutor.runWhenOn(Dist.CLIENT, () -> MagiksMostEvile::registerClientOnlyEvents);

    EvileDeferredRegistry.init(FMLJavaModLoadingContext.get().getModEventBus());
  }

  @SubscribeEvent
  public static void createRegistries(RegistryEvent.NewRegistry event) {
    LOGGER.debug("Creating MME registries");
    new RegistryCreationManager().create();
  }

  public static void registerCommonEvents() {
    LOGGER.debug("Registering MME common events");
    EVENT_BUS.register(ParticleCommonStartup.class);
    EVENT_BUS.register(SetupManager.class);
  }

  public static void registerClientOnlyEvents() {
    LOGGER.debug("Registering MME client only events");
    EVENT_BUS.register(ParticleClientStartup.class);
  }

}
