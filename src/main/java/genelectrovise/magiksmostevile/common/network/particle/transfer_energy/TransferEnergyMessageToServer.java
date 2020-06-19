package genelectrovise.magiksmostevile.common.network.particle.transfer_energy;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import net.minecraft.network.PacketBuffer;

/**
 * @author GenElectrovise 24 May 2020
 */
public class TransferEnergyMessageToServer {

	protected TransferEnergyMessageToServer() {
		
	}

	public void encode(PacketBuffer buffer) {
		MagiksMostEvile.LOGGER.dev("Encoding message to server");
	}

	public static TransferEnergyMessageToServer decode(PacketBuffer buffer) {
		MagiksMostEvile.LOGGER.dev("Decoding message to server");
		return new TransferEnergyMessageToServer();
	}

	public boolean isValid() {
		return false;
	}
}
