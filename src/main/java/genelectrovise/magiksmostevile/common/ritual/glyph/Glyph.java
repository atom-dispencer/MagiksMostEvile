/**
 * 
 */
package genelectrovise.magiksmostevile.common.ritual.glyph;

import java.nio.ByteBuffer;

import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import genelectrovise.magiksmostevile.common.particle.glyph.GlyphParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Creates a floating image of particles. Taken from {@link IOUtil} in my other
 * project, <a href="https://github.com/Volts-of-Doom">Volts of Doom</a>
 * 
 * @author GenElectrovise 15 Jun 2020
 * 
 * @see EnderPearlEntity
 * @see PortalParticle
 */
public class Glyph {
	private String imagePath;
	private ByteBuffer buffer;

	public Glyph(String path) {
		this.imagePath = path;
		//this.buffer = readImageToByteBuffer();
	}

	/*
	 * private ByteBuffer readImageToByteBuffer() { try { return
	 * IOUtil.ioResourceToByteBuffer(imagePath, 256); } catch (Exception e) {
	 * e.printStackTrace(); } return null; }
	 * 
	 * public void draw(BlockPos startPos, World world) { if (!(world instanceof
	 * ClientWorld)) { return; }
	 * 
	 * ClientWorld clientWorld = (ClientWorld) world;
	 * 
	 * buffer.position(0); while (buffer.position() < buffer.limit()) { byte b1 =
	 * buffer.get(); byte b2 = buffer.get(); byte b3 = buffer.get(); byte b4 =
	 * buffer.get();
	 * 
	 * Particle particle = new GlyphParticle(clientWorld, startPos.getX(),
	 * startPos.getY(), startPos.getZ(), 0, 0, 0, 1, 0, 0, 0);
	 * 
	 * Minecraft.getInstance().particles.addEffect(particle); } }
	 */
}
