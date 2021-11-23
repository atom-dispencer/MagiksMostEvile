/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise
 *
 * This file is part of Magiks Most Evile. Magiks Most Evile is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile.
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
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

  public boolean isMessageValid() { return departure != null && destination != null; }

  /**
   * Called by the network code. Used to write the contents of your message member variables into the
   * ByteBuf, ready for transmission over the network.
   *
   * @param buf
   */
  public void encode(PacketBuffer buf) {
    buf.writeBlockPos(departure);
    buf.writeBlockPos(destination);
  }

  /**
   * Called by the network code once it has received the message bytes over the network. Used to read
   * the ByteBuf contents into your member variables
   *
   * @param buf
   */
  public static TransferEnergyMessageToClient decode(PacketBuffer buf) {
    BlockPos departure = buf.readBlockPos();
    BlockPos destination = buf.readBlockPos();

    return new TransferEnergyMessageToClient(departure, destination);
  }

  public boolean isValid() { return departure != null && destination != null; }

  /**
   * @return the departure
   */
  public BlockPos getDeparture() { return departure; }

  /**
   * @return the destination
   */
  public BlockPos getDestination() { return destination; }
}
