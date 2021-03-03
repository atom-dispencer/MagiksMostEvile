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

import java.util.function.Supplier;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.network.altar.AltarNetworkingManager;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * @author GenElectrovise 24 May 2020
 */
public class AltarCastButtonPressedMessageHandlerOnClient {

  /**
   * Called when a message is received of the appropriate type. CALLED BY THE NETWORK THREAD, NOT THE
   * CLIENT THREAD
   * 
   * @throws IllegalAccessException
   */
  public static void onMessageReceived(final AltarCastButtonPressedMessageToClient message,
      Supplier<NetworkEvent.Context> ctxSupplier) {
    ctxSupplier.get().setPacketHandled(true);
    MagiksMostEvile.LOGGER.error(
        "An AltarCastButtonPressedMessageHandlerOnClient has been recieved on the client! This should not be happening! Contact the mod author and your server operator to try to resolve this issue!");
  }

  public static boolean isProtocolAccepted(String protocolVersion) {
    return AltarNetworkingManager.CAST_BUTTON_PRESSED_MESSAGE_PROTOCOL_VERSION
        .equals(protocolVersion);
  }
}
