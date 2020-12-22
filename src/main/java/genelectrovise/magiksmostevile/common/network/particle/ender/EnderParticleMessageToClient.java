/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.particle.ender;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;

/**
 * @author GenElectrovise 24 May 2020
 */
public class EnderParticleMessageToClient {

  private BlockPos position;
  private int count;

  public EnderParticleMessageToClient(BlockPos position, int count) {
    this.position = position;
    this.count = count;
  }

  public boolean isMessageValid() {
    return position != null && count != 0;
  }

  /**
   * Called by the network code. Used to write the contents of your message member variables into
   * the ByteBuf, ready for transmission over the network.
   *
   * @param buf
   */
  public void encode(PacketBuffer buf) {
    buf.writeBlockPos(position);
    buf.writeInt(count);
  }

  /**
   * Called by the network code once it has received the message bytes over the network. Used to
   * read the ByteBuf contents into your member variables
   *
   * @param buf
   */
  public static EnderParticleMessageToClient decode(PacketBuffer buf) {
    BlockPos position = buf.readBlockPos();
    int count = buf.readInt();

    return new EnderParticleMessageToClient(position, count);
  }

  public boolean isValid() {
    return position != null && count != 0;
  }

  /**
   * @return the position
   */
  public BlockPos getPosition() {
    return position;
  }

  /**
   * @return the spacing
   */
  public double getCount() {
    return count;
  }
}
