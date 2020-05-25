/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.altar;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Supplier;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent.Context;

/**
 * @author GenElectrovise 24 May 2020
 */
public class AltarMessageHandlerOnServer {

	public void onMessageReceived(AltarEnergyUpdateMessageToServer message, Supplier<Context> ctxSupplier) {
		MagiksMostEvile.LOGGER.dev("Message recieved by server!");

		ctxSupplier.get().setPacketHandled(true);

		if (!message.isValid()) {
			MagiksMostEvile.LOGGER.warn("Invalid message received on server.");
			return;
		}
		if (ctxSupplier.get().getDirection().getReceptionSide() != LogicalSide.SERVER) {
			MagiksMostEvile.LOGGER.warn("Message recieved on incorrect side. (server) ");
		}

		ctxSupplier.get().enqueueWork(() -> processMessage());
	}

	private void processMessage() {

	}

	public static boolean isProtocolAccepted(String protocolVersion) {
		return AltarNetworkingManager.MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
	}
}
