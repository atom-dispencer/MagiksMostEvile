package genelectrovise.magiksmostevile.common.network.altar;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import net.minecraft.network.PacketBuffer;

/**
 * @author GenElectrovise 24 May 2020
 */
public class AltarEnergyUpdateMessageToServer {

	private static boolean messageIsValid;

	public AltarEnergyUpdateMessageToServer() {
		messageIsValid = true;
	}

	public void encode(PacketBuffer buffer) {
		MagiksMostEvile.LOGGER.dev("Encoding message to server");
	}

	public static AltarEnergyUpdateMessageToServer decode(PacketBuffer buffer) {
		MagiksMostEvile.LOGGER.dev("Decoding message to server");
		return new AltarEnergyUpdateMessageToServer();
	}

	public boolean isValid() {
		return messageIsValid;
	}
}
