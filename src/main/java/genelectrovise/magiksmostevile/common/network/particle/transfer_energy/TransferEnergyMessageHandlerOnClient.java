/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.particle.transfer_energy;

import java.util.function.Supplier;
import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.network.particle.ParticleNetworkingManager;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * @author GenElectrovise 24 May 2020
 */
public class TransferEnergyMessageHandlerOnClient {

  /**
   * Called when a message is received of the appropriate type. CALLED BY THE NETWORK THREAD, NOT
   * THE CLIENT THREAD
   */
  public static void onMessageReceived(final TransferEnergyMessageToClient message,
      Supplier<NetworkEvent.Context> ctxSupplier) {

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

    ctxSupplier.get().enqueueWork(() -> processMessage(message, ctxSupplier));
  }

  // This message is called from the Client thread.
  private static void processMessage(TransferEnergyMessageToClient message,
      Supplier<NetworkEvent.Context> ctxSupplier) {
    Vector3d crystal = new Vector3d(message.getDeparture().getX(), message.getDeparture().getY(),
        message.getDeparture().getZ());
    Vector3d altar = new Vector3d(message.getDestination().getX(), message.getDestination().getY(),
        message.getDestination().getZ());

    Vector3d direction = altar.subtract(crystal);
    direction.normalize();

    double mul = 0.4;
    double yMul = mul * 10;

    Minecraft.getInstance().player.worldClient.addParticle(ParticleTypes.TOTEM_OF_UNDYING, true,
        message.getDeparture().getX() + 0.5, message.getDeparture().getY() + 0.5,
        message.getDeparture().getZ() + 0.5, direction.x * mul, direction.y * yMul,
        direction.z * mul);
  }

  public static boolean isProtocolAccepted(String protocolVersion) {
    return ParticleNetworkingManager.ENDER_PARTICLE_MESSAGE_PROTOCOL_VERSION
        .equals(protocolVersion);
  }
}
