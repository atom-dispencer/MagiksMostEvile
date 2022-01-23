/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise
 *
 * This file is part of Magiks Most Evile. Magiks Most Evile is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile.
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
package genelectrovise.magiksmostevile.particle.glyph;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;

import javax.annotation.Nullable;

/**
 * Created by TGG on 25/03/2020.
 * <p>
 * On the client side: When the client wants to spawn a Particle, it gives the FlameParticleData to
 * this factory method The factory selects an appropriate
 */
public class GlyphParticleFactory implements IParticleFactory<GlyphParticleData> { // IParticleFactory

    private final IAnimatedSprite sprites; // contains a list of textures; choose one using either

    public GlyphParticleFactory(IAnimatedSprite sprite) {
        this.sprites = sprite;
    }
    // newParticle.selectSpriteRandomly(sprites); or
    // newParticle.selectSpriteWithAge(sprites);

    // this method is needed for proper registration of your Factory:
    // The ParticleManager.register method creates a Sprite and passes it to your
    // factory for subsequent use when rendering, then
    // populates it with the textures from your textures/particle/xxx.json

    // This is private to prevent you accidentally registering the Factory using the
    // default constructor.
    // ParticleManager has two register methods, and if you use the wrong one the
    // game will enter an infinite loop
    @SuppressWarnings("unused")
    private GlyphParticleFactory() {
        throw new UnsupportedOperationException(
                "Use the FlameParticleFactory(IAnimatedSprite sprite) constructor");
    }

    @Nullable
    @Override
    public Particle createParticle(GlyphParticleData glyphParticleData, ClientWorld world, double xPos, double yPos, double zPos, double xVelocity, double yVelocity, double zVelocity) {

        try {
            GlyphParticle newParticle = new GlyphParticle(world, xPos, yPos, zPos, xVelocity, yVelocity, zVelocity, glyphParticleData.getTint(), glyphParticleData.getDiameter(), sprites);
            newParticle.pickSprite(sprites); // choose a random sprite from the available list
            // (in this case there is only
            // one)
            return newParticle;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
