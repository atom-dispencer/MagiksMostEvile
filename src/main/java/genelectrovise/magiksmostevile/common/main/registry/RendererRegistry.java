/**
 * 
 */
package genelectrovise.magiksmostevile.common.main.registry;

import genelectrovise.magiksmostevile.common.entity.boss.the_kraken.TheKrakenRenderer;
import genelectrovise.magiksmostevile.common.entity.vampire_bat.VampireBatRenderer;
import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * @author GenElectrovise 12 Jun 2020
 */
@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class RendererRegistry {

	@SubscribeEvent
	public static void renderers(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EvileDeferredRegistry.VAMPIRE_BAT.get(), VampireBatRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(EvileDeferredRegistry.THE_KRAKEN.get(), TheKrakenRenderer::new);
	}
}
