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
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * @author GenElectrovise 24 May 2020
 */
@Deprecated
public class AltarIchorUpdateMessageHandlerOnServer {

    public static void onMessageReceived(final AltarIchorUpdateMessageToServer message,
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

        ctxSupplier.get().enqueueWork(() -> processMessage(message));
    }

    private static void processMessage(AltarIchorUpdateMessageToServer message) {

    }

    public static boolean isProtocolAccepted(String protocolVersion) {
        return AltarNetworkingManager.ENERGY_UPDATE_MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
    }
}
