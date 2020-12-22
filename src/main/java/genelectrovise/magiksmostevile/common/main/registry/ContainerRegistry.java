/**
 * 
 */
package genelectrovise.magiksmostevile.common.main.registry;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.tileentity.altar.AltarScreenManager;
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
		ScreenManager.registerFactory(EvileDeferredRegistry.ALTAR_CONTAINER.get(), new AltarScreenManager());
	}
}
