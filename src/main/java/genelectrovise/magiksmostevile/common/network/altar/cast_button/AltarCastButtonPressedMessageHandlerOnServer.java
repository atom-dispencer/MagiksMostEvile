/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.altar.cast_button;

import java.util.function.Supplier;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.network.altar.AltarNetworkingManager;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * @author GenElectrovise 24 May 2020
 */
public class AltarCastButtonPressedMessageHandlerOnServer {

	public static void onMessageReceived(final AltarCastButtonPressedMessageToServer message, Supplier<NetworkEvent.Context> ctxSupplier) {
		MagiksMostEvile.LOGGER.dev("Message recieved by server!");

		ctxSupplier.get().setPacketHandled(true);

		if (!message.isValid()) {
			MagiksMostEvile.LOGGER.warn("Invalid message received on server.");
			return;
		}
		if (ctxSupplier.get().getDirection().getReceptionSide() != LogicalSide.SERVER) {
			MagiksMostEvile.LOGGER.warn("Message recieved on incorrect side. (server) ");
		}

		ctxSupplier.get().enqueueWork(() -> processMessage(message, ctxSupplier));
	}

	private static void processMessage(AltarCastButtonPressedMessageToServer message, Supplier<NetworkEvent.Context> ctxSupplier) {
		MagiksMostEvile.LOGGER.dev("processing message!");
	}

	public static boolean isProtocolAccepted(String protocolVersion) {
		return AltarNetworkingManager.CAST_BUTTON_PRESSED_MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
	}
}
