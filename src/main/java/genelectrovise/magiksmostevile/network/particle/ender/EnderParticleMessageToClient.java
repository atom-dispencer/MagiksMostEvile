/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise    
 *
 * This file is part of Magiks Most Evile.
 * Magiks Most Evile is free software: you can redistribute it and/or modify it under the terms 
 * of the GNU General Public License as published by the Free Software Foundation, 
 * either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE.  
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile. 
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
/**
 * 
 */
package genelectrovise.magiksmostevile.network.particle.ender;

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
