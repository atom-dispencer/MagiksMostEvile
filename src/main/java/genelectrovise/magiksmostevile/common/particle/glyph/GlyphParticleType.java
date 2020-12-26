/**
 * 
 */
package genelectrovise.magiksmostevile.common.particle.glyph;

import com.mojang.serialization.Codec;
import net.minecraft.particles.ParticleType;

/**
 * @author GenElectrovise 15 Jun 2020
 */
public class GlyphParticleType extends ParticleType<GlyphParticleData> {
  private static boolean ALWAYS_SHOW_REGARDLESS_OF_DISTANCE_FROM_PLAYER = false;

  // TODO does this error?
  private final Codec<GlyphParticleData> codec = Codec.unit(() -> null);

  public GlyphParticleType() {
    super(ALWAYS_SHOW_REGARDLESS_OF_DISTANCE_FROM_PLAYER, GlyphParticleData.DESERIALIZER);
  }

  public GlyphParticleType getThis() {
    return this;
  }

  /**
   * Get the {@link Codec} (Encoder/Decoder) of this particle.
   */
  @Override
  public Codec<GlyphParticleData> func_230522_e_() {
    return codec;
  }

}