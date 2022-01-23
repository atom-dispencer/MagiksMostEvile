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
package genelectrovise.magiksmostevile.network.inscription_table.button_pressed;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.network.altar.AltarNetworkingManager;
import genelectrovise.magiksmostevile.tileentity.inscription_table.InscriptionTableContainer;
import genelectrovise.magiksmostevile.tileentity.inscription_table.InscriptionTableTileEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ButtonPressedMessageHandlerOnServer {
    public static void onMessageReceived(final ButtonPressedMessageToServer message, Supplier<NetworkEvent.Context> ctxSupplier) {

        ctxSupplier.get().setPacketHandled(true);

        if (!message.isValid()) {
            MagiksMostEvile.LOGGER.warn("Invalid ButtonPressedMessageToServer received on server.");
            return;
        }

        if (ctxSupplier.get().getDirection().getReceptionSide() != LogicalSide.SERVER) {
            MagiksMostEvile.LOGGER.warn("Message recieved on incorrect side. (server) ");
        }

        ctxSupplier.get().enqueueWork(() -> processMessage(message, ctxSupplier));
    }

    private static void processMessage(ButtonPressedMessageToServer message, Supplier<NetworkEvent.Context> ctxSupplier) {
        MagiksMostEvile.LOGGER.debug("processing message!");

        // Get and check container
        Container container = ctxSupplier.get().getSender().containerMenu;

        if (!(container instanceof InscriptionTableContainer)) {
            return;
        }

        InscriptionTableContainer inscriptionTable = (InscriptionTableContainer) container;
        BlockPos pos = inscriptionTable.getInscriptionTable().getBlockPos();

        // Get and check tile entity
        TileEntity tileEntity = ctxSupplier.get().getSender().level.getBlockEntity(pos);

        if (!(tileEntity instanceof InscriptionTableTileEntity)) {
            return;
        }

        InscriptionTableTileEntity inscriptionTableTileEntity = (InscriptionTableTileEntity) tileEntity;
        inscriptionTableTileEntity.recipe(message.getSignumName());
    }

    public static boolean isProtocolAccepted(String protocolVersion) {
        return AltarNetworkingManager.CAST_BUTTON_PRESSED_MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
    }

}
