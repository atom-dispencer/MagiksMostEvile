/**
 * 
 */
package genelectrovise.magiksmostevile.network.particle.transfer_energy;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;

/**
 * @author GenElectrovise 24 May 2020
 */
public class TransferEnergyMessageToClient {

  private BlockPos departure;
  private BlockPos destination;

  public TransferEnergyMessageToClient(BlockPos departure, BlockPos destination) {
    this.departure = departure;
    this.destination = destination;
  }

  public boolean isMessageValid() {
    return departure != null && destination != null;
  }

  /**
   * Called by the network code. Used to write the contents of your message member variables into
   * the ByteBuf, ready for transmission over the network.
   *
   * @param buf
   */
  public void encode(PacketBuffer buf) {
    buf.writeBlockPos(departure);
    buf.writeBlockPos(destination);
  }

  /**
   * Called by the network code once it has received the message bytes over the network. Used to
   * read the ByteBuf contents into your member variables
   *
   * @param buf
   */
  public static TransferEnergyMessageToClient decode(PacketBuffer buf) {
    BlockPos departure = buf.readBlockPos();
    BlockPos destination = buf.readBlockPos();

    return new TransferEnergyMessageToClient(departure, destination);
  }

  public boolean isValid() {
    return departure != null && destination != null;
  }

  /**
   * @return the departure
   */
  public BlockPos getDeparture() {
    return departure;
  }

  /**
   * @return the destination
   */
  public BlockPos getDestination() {
    return destination;
  }
}
