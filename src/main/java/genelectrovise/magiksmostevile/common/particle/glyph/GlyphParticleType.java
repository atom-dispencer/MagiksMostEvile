/**
 * 
 */
package genelectrovise.magiksmostevile.common.particle.glyph;

import net.minecraft.particles.ParticleType;

/**
 * @author GenElectrovise 15 Jun 2020
 */
public class GlyphParticleType extends ParticleType<GlyphParticleData> {
	private static boolean ALWAYS_SHOW_REGARDLESS_OF_DISTANCE_FROM_PLAYER = false;

	public GlyphParticleType() {
		super(ALWAYS_SHOW_REGARDLESS_OF_DISTANCE_FROM_PLAYER, GlyphParticleData.DESERIALIZER);
	}
}
