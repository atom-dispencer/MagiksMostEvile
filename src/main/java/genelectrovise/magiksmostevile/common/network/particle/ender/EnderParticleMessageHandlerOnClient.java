/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.particle.ender;

import java.util.Random;
import java.util.function.Supplier;
import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.network.particle.ParticleNetworkingManager;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * @author GenElectrovise 24 May 2020
 */
public class EnderParticleMessageHandlerOnClient {

  /**
   * Called when a message is received of the appropriate type. CALLED BY THE NETWORK THREAD, NOT
   * THE CLIENT THREAD
   */
  public static void onMessageReceived(final EnderParticleMessageToClient message,
      Supplier<NetworkEvent.Context> ctxSupplier) {

    ctxSupplier.get().setPacketHandled(true);

    if (!message.isValid()) {
      MagiksMostEvile.LOGGER.warn("Invalid message received on client.");
      return;
    }

    if (ctxSupplier.get().getDirection().getReceptionSide() != LogicalSide.CLIENT) {
      MagiksMostEvile.LOGGER.warn("Message recieved on incorrect side. (client) ");
      return;
    }

    ctxSupplier.get().enqueueWork(() -> processMessage(message, ctxSupplier));
  }

  // This message is called from the Client thread.
  private static void processMessage(EnderParticleMessageToClient message,
      Supplier<NetworkEvent.Context> ctxSupplier) {
    Random random = new Random();

    for (int i = 0; i < message.getCount(); i++) {
      Minecraft.getInstance().player.worldClient.addParticle(ParticleTypes.PORTAL, true,
          message.getPosition().getX(), message.getPosition().getY() + random.nextDouble() * 2.0D,
          message.getPosition().getZ(), random.nextGaussian(), 0.0D, random.nextGaussian());
    }
  }

  public static boolean isProtocolAccepted(String protocolVersion) {
    return ParticleNetworkingManager.ENDER_PARTICLE_MESSAGE_PROTOCOL_VERSION
        .equals(protocolVersion);
  }
}
