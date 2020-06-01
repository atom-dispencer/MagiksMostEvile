/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.altar;

import java.util.function.Supplier;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * @author GenElectrovise 24 May 2020
 */
@Deprecated
public class AltarEnergyUpdateMessageHandlerOnServer {

	public static void onMessageReceived(final AltarEnergyUpdateMessageToServer message, Supplier<NetworkEvent.Context> ctxSupplier) {
		MagiksMostEvile.LOGGER.dev("Message recieved by server!");

		ctxSupplier.get().setPacketHandled(true);

		if (!message.isValid()) {
			MagiksMostEvile.LOGGER.warn("Invalid message received on server.");
			return;
		}
		if (ctxSupplier.get().getDirection().getReceptionSide() != LogicalSide.SERVER) {
			MagiksMostEvile.LOGGER.warn("Message recieved on incorrect side. (server) ");
		}

		ctxSupplier.get().enqueueWork(() -> processMessage(message));
	}

	private static void processMessage(AltarEnergyUpdateMessageToServer message) {
		
	}

	public static boolean isProtocolAccepted(String protocolVersion) {
		return AltarNetworkingManager.MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
	}
}
