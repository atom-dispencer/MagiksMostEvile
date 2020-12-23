package genelectrovise.magiksmostevile.common.main;

import genelectrovise.magiksmostevile.common.entity.vampire_bat.VampireBatEntity;
import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.ParallelDispatchEvent;

public final class SetupManager {

  public static final Runnable[] tasks = {

      // Setup entity attributes
      () -> {
        MagiksMostEvile.LOGGER.debug("Registering MME entity attributes");
        GlobalEntityTypeAttributes.put(EvileDeferredRegistry.VAMPIRE_BAT.get(),
            VampireBatEntity.getEntityAttributes().create());
      }

      //
  };

  @SubscribeEvent
  public static void setup(ParallelDispatchEvent event) {
    
    MagiksMostEvile.LOGGER.debug("SetupManager#setup() called for MME");

    for (Runnable runnable : tasks) {
      event.enqueueWork(runnable);
    }

  }
}
