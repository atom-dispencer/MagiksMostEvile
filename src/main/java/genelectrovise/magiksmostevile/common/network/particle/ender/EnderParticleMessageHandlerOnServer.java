/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.particle.ender;

import java.util.function.Supplier;
import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.network.particle.ParticleNetworkingManager;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * @author GenElectrovise 24 May 2020
 */
public class EnderParticleMessageHandlerOnServer {

  public static void onMessageReceived(final EnderParticleMessageToServer message,
      Supplier<NetworkEvent.Context> ctxSupplier) {
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

  private static void processMessage(EnderParticleMessageToServer message) {
    return;
  }

  public static boolean isProtocolAccepted(String protocolVersion) {
    return ParticleNetworkingManager.ENDER_PARTICLE_MESSAGE_PROTOCOL_VERSION
        .equals(protocolVersion);
  }
}
