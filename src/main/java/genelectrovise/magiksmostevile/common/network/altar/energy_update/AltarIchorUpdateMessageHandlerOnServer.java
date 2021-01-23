/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.altar.energy_update;

import java.util.function.Supplier;
import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.network.altar.AltarNetworkingManager;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * @author GenElectrovise 24 May 2020
 */
@Deprecated
public class AltarIchorUpdateMessageHandlerOnServer {

  public static void onMessageReceived(final AltarIchorUpdateMessageToServer message,
      Supplier<NetworkEvent.Context> ctxSupplier) {
    MagiksMostEvile.LOGGER.debug("Message recieved by server!");

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

  private static void processMessage(AltarIchorUpdateMessageToServer message) {

  }

  public static boolean isProtocolAccepted(String protocolVersion) {
    return AltarNetworkingManager.ENERGY_UPDATE_MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
  }
}
