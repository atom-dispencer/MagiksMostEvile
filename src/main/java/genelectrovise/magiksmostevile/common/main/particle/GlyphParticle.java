/**
 * 
 */
package genelectrovise.magiksmostevile.common.main.particle;

import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.world.World;

/**
 * @author GenElectrovise 15 Jun 2020
 * @see PortalParticle
 */
public class GlyphParticle extends SpriteTexturedParticle {

	/**
	 * @param worldIn
	 * @param xCoordIn
	 * @param yCoordIn
	 * @param zCoordIn
	 * @param xSpeedIn
	 * @param ySpeedIn
	 * @param zSpeedIn
	 */
	public GlyphParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, float red, float green, float blue, float alpha) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		particleRed = red;
		particleGreen = green;
		particleBlue = blue;
		particleAlpha = alpha;
	}

	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

}
