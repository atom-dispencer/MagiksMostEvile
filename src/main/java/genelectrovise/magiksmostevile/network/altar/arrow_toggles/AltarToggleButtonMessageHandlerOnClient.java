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
package genelectrovise.magiksmostevile.network.altar.arrow_toggles;

import java.util.function.Supplier;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.network.altar.AltarNetworkingManager;
import genelectrovise.magiksmostevile.tileentity.altar.AltarContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkEvent.Context;

/**
 * @author GenElectrovise 24 May 2020
 */
public class AltarToggleButtonMessageHandlerOnClient {

  /**
   * Called when a message is received of the appropriate type. CALLED BY THE NETWORK THREAD, NOT THE
   * CLIENT THREAD
   * 
   * @throws IllegalAccessException
   */
  public static void onMessageReceived(final AltarToggleButtonMessageToClient message,
      Supplier<NetworkEvent.Context> ctxSupplier) {
    MagiksMostEvile.LOGGER.error("ToggleButtonOnClient");

    ctxSupplier.get().setPacketHandled(true);

    if (!message.isValid()) {
      return;
    }

    ctxSupplier.get().enqueueWork(() -> processMessage(message, ctxSupplier));
  }

  /**
   * @param message
   * @param ctxSupplier
   * @return
   */
  private static void processMessage(AltarToggleButtonMessageToClient message,
      Supplier<Context> ctxSupplier) {
    Container container = Minecraft.getInstance().player.containerMenu;

    // If the player's open container is not an altar container, return.
    if (!(container instanceof AltarContainer)) {
      return;
    }

    AltarContainer altarContainer = (AltarContainer) container;
    altarContainer.getSelector().setLocation(message.getLocation());
  }

  public static boolean isProtocolAccepted(String protocolVersion) {
    return AltarNetworkingManager.TOGGLE_BUTTON_MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
  }
}
