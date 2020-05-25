/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.altar;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import net.minecraft.network.PacketBuffer;

/**
 * @author GenElectrovise 24 May 2020
 */
public class AltarEnergyUpdateMessageToServer {

	private boolean messageIsValid;

	public AltarEnergyUpdateMessageToServer() {
		messageIsValid = true;
	}

	public static AltarEnergyUpdateMessageToServer decode(AltarEnergyUpdateMessageToServer message, PacketBuffer buffer) {
		MagiksMostEvile.LOGGER.dev("Decoding message to server");
		return new AltarEnergyUpdateMessageToServer();
	}

	public void encode(AltarEnergyUpdateMessageToServer message, PacketBuffer buffer) {
		MagiksMostEvile.LOGGER.dev("Encoding message to server");
	}

	public boolean isValid() {
		return messageIsValid;
	}
}
