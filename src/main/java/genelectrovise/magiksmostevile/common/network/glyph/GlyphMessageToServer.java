package genelectrovise.magiksmostevile.common.network.glyph;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import net.minecraft.network.PacketBuffer;

/**
 * @author GenElectrovise 24 May 2020
 */
public class GlyphMessageToServer {

	protected GlyphMessageToServer() {
			
	}

	public void encode(PacketBuffer buffer) {
		MagiksMostEvile.LOGGER.dev("Encoding message to server");
	}

	public static GlyphMessageToServer decode(PacketBuffer buffer) {
		MagiksMostEvile.LOGGER.dev("Decoding message to server");
		return new GlyphMessageToServer();
	}

	public boolean isValid() {
		return false;
	}
}
