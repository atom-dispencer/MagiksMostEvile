/**
 * 
 */
package genelectrovise.magiksmostevile.core.registry;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.core.registry.orbital.registries.ContainerOrbitalRegistry;
import genelectrovise.magiksmostevile.tileentity.altar.AltarScreenManager;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

/**
 * @author GenElectrovise 12 Jun 2020
 */
@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContainerRegistry {

  @SubscribeEvent
  public static void registerFactories(FMLLoadCompleteEvent event) {
    ScreenManager.registerFactory(ContainerOrbitalRegistry.ALTAR_CONTAINER.get(),
        new AltarScreenManager());
  }
}
