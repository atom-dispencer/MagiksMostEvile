/**
 * 
 */
package genelectrovise.magiksmostevile.network.particle.transfer_energy;

import java.util.function.Supplier;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.network.particle.ParticleNetworkingManager;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * @author GenElectrovise 24 May 2020
 */
public class TransferEnergyMessageHandlerOnServer {

  public static void onMessageReceived(final TransferEnergyMessageToServer message,
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

  private static void processMessage(TransferEnergyMessageToServer message) {
    return;
  }

  public static boolean isProtocolAccepted(String protocolVersion) {
    return ParticleNetworkingManager.ENDER_PARTICLE_MESSAGE_PROTOCOL_VERSION
        .equals(protocolVersion);
  }
}
