package genelectrovise.magiksmostevile.particle.glyph;

import javax.annotation.Nullable;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;

/**
 * Created by TGG on 25/03/2020.
 *
 * On the client side: When the client wants to spawn a Particle, it gives the FlameParticleData to
 * this factory method The factory selects an appropriate
 *
 */
public class GlyphParticleFactory implements IParticleFactory<GlyphParticleData> { // IParticleFactory

  @Nullable
  @Override
  public Particle makeParticle(GlyphParticleData glyphParticleData, ClientWorld world, double xPos,
      double yPos, double zPos, double xVelocity, double yVelocity, double zVelocity) {

    try {
      GlyphParticle newParticle = new GlyphParticle(world, xPos, yPos, zPos, xVelocity, yVelocity,
          zVelocity, glyphParticleData.getTint(), glyphParticleData.getDiameter(), sprites);
      newParticle.selectSpriteRandomly(sprites); // choose a random sprite from the available list
                                                 // (in this case there is only
                                                 // one)
      return newParticle;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private final IAnimatedSprite sprites; // contains a list of textures; choose one using either
  // newParticle.selectSpriteRandomly(sprites); or
  // newParticle.selectSpriteWithAge(sprites);

  // this method is needed for proper registration of your Factory:
  // The ParticleManager.register method creates a Sprite and passes it to your
  // factory for subsequent use when rendering, then
  // populates it with the textures from your textures/particle/xxx.json

  public GlyphParticleFactory(IAnimatedSprite sprite) {
    this.sprites = sprite;
  }

  // This is private to prevent you accidentally registering the Factory using the
  // default constructor.
  // ParticleManager has two register methods, and if you use the wrong one the
  // game will enter an infinite loop
  @SuppressWarnings("unused")
  private GlyphParticleFactory() {
    throw new UnsupportedOperationException(
        "Use the FlameParticleFactory(IAnimatedSprite sprite) constructor");
  }

}
