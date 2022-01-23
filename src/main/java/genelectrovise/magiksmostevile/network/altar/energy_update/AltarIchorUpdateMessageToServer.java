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
package genelectrovise.magiksmostevile.network.altar.energy_update;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.network.PacketBuffer;

/**
 * @author GenElectrovise 24 May 2020
 */
@Deprecated
public class AltarIchorUpdateMessageToServer {

    private static boolean messageIsValid;

    public AltarIchorUpdateMessageToServer() {
        messageIsValid = true;
    }

    public static AltarIchorUpdateMessageToServer decode(PacketBuffer buffer) {
        MagiksMostEvile.LOGGER.debug("Decoding message to server");
        return new AltarIchorUpdateMessageToServer();
    }

    public void encode(PacketBuffer buffer) {
        MagiksMostEvile.LOGGER.debug("Encoding message to server");
    }

    public boolean isValid() {
        return messageIsValid;
    }
}
