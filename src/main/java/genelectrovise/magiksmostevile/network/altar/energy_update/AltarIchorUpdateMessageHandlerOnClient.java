/**
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise
 * <p>
 * This file is part of Magiks Most Evile. Magiks Most Evile is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 * <p>
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile.
 * If not, see <https://www.gnu.org/licenses/>.
 */
/**
 *
 */
package genelectrovise.magiksmostevile.network.altar.energy_update;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.network.altar.AltarNetworkingManager;
import genelectrovise.magiksmostevile.tileentity.altar.AltarContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * @author GenElectrovise 24 May 2020
 */
@Deprecated
public class AltarIchorUpdateMessageHandlerOnClient {

    /**
     * Called when a message is received of the appropriate type. CALLED BY THE NETWORK THREAD, NOT THE
     * CLIENT THREAD
     */
    public static void onMessageReceived(final AltarIchorUpdateMessageToClient message,
                                         Supplier<NetworkEvent.Context> ctxSupplier) {

        MagiksMostEvile.LOGGER.debug("AltarIchorUpdateMessageToClient recieved on client!");

        ctxSupplier.get().setPacketHandled(true);

        if (!message.isValid()) {
            MagiksMostEvile.LOGGER.warn("Invalid AltarIchorUpdateMessageToClient message received on client.");
            return;
        }

        if (ctxSupplier.get().getDirection().getReceptionSide() != LogicalSide.CLIENT) {
            MagiksMostEvile.LOGGER.warn("AltarIchorUpdateMessage- recieved on incorrect side. (client) ");
            return;
        }

        // Now we know that this is on the client of the player who is viewing the
        // container! (logic actually happening on the network thread still!)

        // Creates a new task for the client for next tick
        ctxSupplier.get().enqueueWork(() -> processMessage(message, ctxSupplier));
    }

    // This message is called from the Client thread.
    private static void processMessage(AltarIchorUpdateMessageToClient message,
                                       Supplier<NetworkEvent.Context> ctxSupplier) {

        MagiksMostEvile.LOGGER.debug("Processing AltarIchorUpdateMessageToClient on client");

        // Here I need to get an instance of the world or player so I can access things
        ClientPlayerEntity player = Minecraft.getInstance().player;
        Container containerRaw = player.containerMenu;
        if (containerRaw instanceof AltarContainer) {
            MagiksMostEvile.LOGGER.debug("instanceof AltarContainer. Updating values.");
            AltarContainer altarContainer = (AltarContainer) containerRaw;
            altarContainer.setIchorCapacity(message.maxAmethystFlux);
            altarContainer.setCurrentIchor(message.currentAmethystFlux);
        }
    }

    public static boolean isProtocolAccepted(String protocolVersion) {
        return AltarNetworkingManager.ENERGY_UPDATE_MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
    }
}
