/**
 * 
 */
package genelectrovise.magiksmostevile.common.core.registry;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.core.registry.orbital.registries.EntityOrbitalRegistry;
import genelectrovise.magiksmostevile.common.entity.boss.the_kraken.KittyTheKrakenRenderer;
import genelectrovise.magiksmostevile.common.entity.boss.the_kraken.squid_missile.SquidMissileRenderer;
import genelectrovise.magiksmostevile.common.entity.vampire_bat.VampireBatRenderer;
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
