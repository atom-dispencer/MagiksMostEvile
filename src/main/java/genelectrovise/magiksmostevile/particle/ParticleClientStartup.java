/**
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise
 * <p>
 * This file is part of Magiks Most Evile. Magiks Most Evile is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 * <p>
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile.
 * If not, see <https://www.gnu.org/licenses/>.
 */
/**
 *
 */
package genelectrovise.magiksmostevile.particle;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.particle.glyph.GlyphParticleFactory;
import genelectrovise.magiksmostevile.registry.orbital.ParticleOrbitalRegistry;
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
        Minecraft.getInstance().particleEngine.register(ParticleOrbitalRegistry.GLYPH_PARTICLE.get(),
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
