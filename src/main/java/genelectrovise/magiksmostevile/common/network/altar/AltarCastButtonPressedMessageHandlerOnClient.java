/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.altar;

import java.util.function.Supplier;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * @author GenElectrovise 24 May 2020
 */
public class AltarCastButtonPressedMessageHandlerOnClient {

	/**
	 * Called when a message is received of the appropriate type. CALLED BY THE
	 * NETWORK THREAD, NOT THE CLIENT THREAD
	 * 
	 * @throws IllegalAccessException
	 */
	public static void onMessageReceived(final AltarCastButtonPressedMessageToClient message, Supplier<NetworkEvent.Context> ctxSupplier) {
		MagiksMostEvile.LOGGER.error("An AltarCastButtonPressedMessageHandlerOnClient has been recieved on the client! This should not be happening! Contact the mod author and your server operator to try to resolve this issue!");
	}

	public static boolean isProtocolAccepted(String protocolVersion) {
		return AltarNetworkingManager.ENERGY_UPDATE_MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
	}
}