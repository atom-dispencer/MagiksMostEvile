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
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

public class ButtonPressedMessageToServer {
    private static boolean messageIsValid;

    private ResourceLocation buttonName;

    public ButtonPressedMessageToServer(ResourceLocation signumName) {
        messageIsValid = true;
        this.buttonName = signumName;
    }

    public static ButtonPressedMessageToServer decode(PacketBuffer buffer) {
        MagiksMostEvile.LOGGER.debug("Decoding AltarCastButtonPressedMessageToServer");

        ResourceLocation buttonName = buffer.readResourceLocation();

        return new ButtonPressedMessageToServer(buttonName);
    }

    public void encode(PacketBuffer buffer) {
        MagiksMostEvile.LOGGER.debug("Encoding AltarCastButtonPressedMessageToServer.");
        buffer.writeResourceLocation(buttonName);
    }

    public ResourceLocation getSignumName() {
        return buttonName;
    }

    public boolean isValid() {
        return messageIsValid;
    }

}
