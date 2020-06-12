/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.altar.arrow_toggles;

import java.util.function.Supplier;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.network.altar.AltarNetworkingManager;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * @author GenElectrovise 24 May 2020
 */
public class AltarToggleButtonMessageHandlerOnServer {

	public static void onMessageReceived(final AltarToggleButtonMessageToServer message, Supplier<NetworkEvent.Context> ctxSupplier) {
		MagiksMostEvile.LOGGER.dev("Message recieved by server!");

		ctxSupplier.get().setPacketHandled(true);

		// Returns the player who clicked the button! WOOOOOO!!
		ctxSupplier.get().getSender();
	}

	public static boolean isProtocolAccepted(String protocolVersion) {
		return AltarNetworkingManager.TOGGLE_BUTTON_MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
	}
}
