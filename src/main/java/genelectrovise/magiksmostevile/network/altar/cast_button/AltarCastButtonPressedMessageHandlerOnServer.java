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
import genelectrovise.magiksmostevile.tileentity.altar.AltarContainer;
import genelectrovise.magiksmostevile.tileentity.altar.AltarTileEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * @author GenElectrovise 24 May 2020
 */
public class AltarCastButtonPressedMessageHandlerOnServer {

  public static void onMessageReceived(final AltarCastButtonPressedMessageToServer message,
      Supplier<NetworkEvent.Context> ctxSupplier) {
    MagiksMostEvile.LOGGER.debug("Message recieved by server!");

    ctxSupplier.get().setPacketHandled(true);

    if (!message.isValid()) {
      MagiksMostEvile.LOGGER.warn("Invalid message received on server.");
      return;
    }
    if (ctxSupplier.get().getDirection().getReceptionSide() != LogicalSide.SERVER) {
      MagiksMostEvile.LOGGER.warn("Message recieved on incorrect side. (server) ");
    }

    ctxSupplier.get().enqueueWork(() -> processMessage(message, ctxSupplier));
  }

  private static void processMessage(AltarCastButtonPressedMessageToServer message,
      Supplier<NetworkEvent.Context> ctxSupplier) {
    MagiksMostEvile.LOGGER.debug("processing message!");

    // Get and check container
    Container container = ctxSupplier.get().getSender().openContainer;

    if (!(container instanceof AltarContainer)) {
      return;
    }

    AltarContainer altarContainer = (AltarContainer) container;
    BlockPos pos = altarContainer.getAltar().getPos();

    // Get and check tile entity
    TileEntity tileEntity = ctxSupplier.get().getSender().world.getTileEntity(pos);

    if (!(tileEntity instanceof AltarTileEntity)) {
      return;
    }

    AltarTileEntity altarTileEntity = (AltarTileEntity) tileEntity;
    altarTileEntity.castRitual(
        AltarTileEntity.getRitualFromResourceLocation(message.getRitualResourceLocation()));
  }

  public static boolean isProtocolAccepted(String protocolVersion) {
    return AltarNetworkingManager.CAST_BUTTON_PRESSED_MESSAGE_PROTOCOL_VERSION
        .equals(protocolVersion);
  }
}
