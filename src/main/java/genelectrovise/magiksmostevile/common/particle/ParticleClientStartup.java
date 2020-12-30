/**
 * 
 */
package genelectrovise.magiksmostevile.common.particle;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.core.registry.orbital.registries.ParticleOrbitalRegistry;
import genelectrovise.magiksmostevile.common.particle.glyph.GlyphParticleFactory;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Bus.MOD)
/**
 * @author GenElectrovise 15 Jun 2020
 */
public class ParticleClientStartup {
  // Register the factory that will spawn our Particle from ParticleData
  @SubscribeEvent
  public static void onParticleFactoryRegistration(ParticleFactoryRegisterEvent event) {

    // beware - there are two registerFactory methods with different signatures.
    // If you use the wrong one it will put Minecraft into an infinite loading loop
    // with no console errors
    MagiksMostEvile.LOGGER.warn(
        "If this is the last thing you can see and Minecraft isn't loading, check in ParticleClientStartup for infinite loading loops.");
    Minecraft.getInstance().particles.registerFactory(ParticleOrbitalRegistry.GLYPH_PARTICLE.get(),
        sprite -> new GlyphParticleFactory(sprite));

    // This lambda may not be obvious: its purpose is:
    // the registerFactory method creates an IAnimatedSprite, then passes it to the
    // constructor of GlyphParticleFactory

    // General rule of thumb:
    // If you are creating a TextureParticle with a corresponding json to specify
    // textures which will be stitched into the
    // particle texture sheet, then use the 1-parameter constructor method
    // If you're supplying the render yourself, or using a texture from the block
    // sheet, use the 0-parameter constructor method
    // (examples are MobAppearanceParticle, DiggingParticle). See
    // ParticleManager::registerFactories for more.
  }
}
