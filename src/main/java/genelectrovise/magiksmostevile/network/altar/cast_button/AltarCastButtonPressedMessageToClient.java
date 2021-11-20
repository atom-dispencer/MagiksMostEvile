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
package genelectrovise.magiksmostevile.network.altar.cast_button;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.network.PacketBuffer;

/**
 * This Network Message is sent from the client to the server, to tell it to spawn projectiles at a
 * particular location. Typical usage: PREQUISITES: have previously setup SimpleChannel, registered
 * the message class and the handler.<br>
 * <br>
 *
 * 1) User creates an AltarEnergyUpdateMessageToServer(targetCoordinates) <br>
 * 2) simpleChannel.sendToServer(airstrikeMessageToServer); <br>
 * 3) Forge network code calls message.encode() to copy the message member variables to a
 * PacketBuffer, ready for sending ... bytes are sent over the network and arrive at the server....
 * <br>
 * 4) Forge network code calls message.decode() to recreate the airstrickeMessageToServer instance
 * by reading from the PacketBuffer into the member variables <br>
 * 5) the handler.onMessage(message) is called to process the message
 * 
 * @author GenElectrovise 24 May 2020
 */
public class AltarCastButtonPressedMessageToClient {

  private static boolean messageIsValid;

  public AltarCastButtonPressedMessageToClient() {
    messageIsValid = false;
    MagiksMostEvile.LOGGER.warn(
        "A AltarCastButtonPressedMessageToClient is being constructed! Why is this happening! Have invalidated message as this should not occur!!");
  }

  public boolean isMessageValid() { return messageIsValid; }

  /**
   * Called by the network code. Used to write the contents of your message member variables into the
   * ByteBuf, ready for transmission over the network.
   *
   * @param buf
   */
  public void encode(PacketBuffer buf) {
    MagiksMostEvile.LOGGER.debug("Encoding message to client");
    if (!messageIsValid)
      return;
  }

  /**
   * Called by the network code once it has received the message bytes over the network. Used to read
   * the ByteBuf contents into your member variables
   *
   * @param buf
   */
  public static AltarCastButtonPressedMessageToClient decode(PacketBuffer buf) {
    MagiksMostEvile.LOGGER.debug("Decoding message to client");
    return new AltarCastButtonPressedMessageToClient();
  }

  public boolean isValid() { return messageIsValid; }
}
