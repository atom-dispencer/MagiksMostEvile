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
public class AltarToggleButtonMessageHandlerOnClient {

	/**
	 * Called when a message is received of the appropriate type. CALLED BY THE
	 * NETWORK THREAD, NOT THE CLIENT THREAD
	 * 
	 * @throws IllegalAccessException
	 */
	public static void onMessageReceived(final AltarToggleButtonMessageToClient message, Supplier<NetworkEvent.Context> ctxSupplier) {
		MagiksMostEvile.LOGGER.error("ToggleButtonOnClient");
		ctxSupplier.get().getSender();
	}

	public static boolean isProtocolAccepted(String protocolVersion) {
		return AltarNetworkingManager.TOGGLE_BUTTON_MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
	}
}