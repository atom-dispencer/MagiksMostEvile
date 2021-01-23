/**
 * 
 */
package genelectrovise.magiksmostevile.registry;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.entity.boss.kitty_the_kraken.KittyTheKrakenRenderer;
import genelectrovise.magiksmostevile.entity.boss.kitty_the_kraken.squid_missile.SquidMissileRenderer;
import genelectrovise.magiksmostevile.entity.vampire_bat.VampireBatRenderer;
import genelectrovise.magiksmostevile.registry.orbital.registries.EntityOrbitalRegistry;
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
    RenderingRegistry.registerEntityRenderingHandler(EntityOrbitalRegistry.VAMPIRE_BAT.get(), VampireBatRenderer::new);
    RenderingRegistry.registerEntityRenderingHandler(EntityOrbitalRegistry.THE_KRAKEN.get(), KittyTheKrakenRenderer::new);
    RenderingRegistry.registerEntityRenderingHandler(EntityOrbitalRegistry.SQUID_MISSILE.get(), SquidMissileRenderer::new);
  }
}
