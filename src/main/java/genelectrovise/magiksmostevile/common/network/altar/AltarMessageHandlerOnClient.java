/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.altar;

import java.util.function.Supplier;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * @author GenElectrovise 24 May 2020
 */
public class AltarMessageHandlerOnClient {

	/**
	 * Called when a message is received of the appropriate type. CALLED BY THE
	 * NETWORK THREAD, NOT THE CLIENT THREAD
	 */
	public static void onMessageReceived(final AltarEnergyUpdateMessageToClient message, Supplier<NetworkEvent.Context> ctxSupplier) {

		MagiksMostEvile.LOGGER.dev("Message recieved on client!");

		ctxSupplier.get().setPacketHandled(true);

		if (!message.isValid()) {
			MagiksMostEvile.LOGGER.warn("Invalid message received on client.");
			return;
		}

		if (ctxSupplier.get().getDirection().getReceptionSide() != LogicalSide.CLIENT) {
			MagiksMostEvile.LOGGER.warn("Message recieved on incorrect side. (client) ");
			return;
		}

		// Now we know that this is on the client of the player who is viewing the
		// container! (logic actually happening on the network thread still!)

		// Creates a new task for the client for next tick
		ctxSupplier.get().enqueueWork(() -> processMessage(message, ctxSupplier));
	}

	// This message is called from the Client thread.
	private static void processMessage(AltarEnergyUpdateMessageToClient message, Supplier<NetworkEvent.Context> ctxSupplier) {

		// Here I need to get an instance of the world or player so I can access things
		ctxSupplier.get().getPacketDispatcher();
	}

	public static boolean isProtocolAccepted(String protocolVersion) {
		return AltarNetworkingManager.MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
	}
}