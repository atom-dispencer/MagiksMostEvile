/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.altar.arrow_toggles;

import java.util.function.Supplier;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.network.altar.AltarNetworkingManager;
import genelectrovise.magiksmostevile.common.tileentity.altar.AltarContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkEvent.Context;

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

		ctxSupplier.get().setPacketHandled(true);
		
		if (!message.isValid()) {
			return;
		}

		ctxSupplier.get().enqueueWork(() -> processMessage(message, ctxSupplier));
	}

	/**
	 * @param message
	 * @param ctxSupplier
	 * @return
	 */
	private static void processMessage(AltarToggleButtonMessageToClient message, Supplier<Context> ctxSupplier) {
		Container container = Minecraft.getInstance().player.openContainer;

		// If the player's open container is not an altar container, return.
		if (!(container instanceof AltarContainer)) {
			return;
		}
		
		AltarContainer altarContainer = (AltarContainer) container;
		altarContainer.getSelector().setLocation(message.getLocation());
	}

	public static boolean isProtocolAccepted(String protocolVersion) {
		return AltarNetworkingManager.TOGGLE_BUTTON_MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
	}
}