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
package genelectrovise.magiksmostevile.network.altar.cast_button;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

/**
 * @author GenElectrovise 24 May 2020
 */
public class AltarCastButtonPressedMessageToServer {

  private static boolean messageIsValid;

  private ResourceLocation ritualResourceLocation;

  public AltarCastButtonPressedMessageToServer(ResourceLocation ritualResourceLocation) {
    messageIsValid = true;

    this.ritualResourceLocation = ritualResourceLocation;
  }

  public void encode(PacketBuffer buffer) {
    MagiksMostEvile.LOGGER.debug("Encoding AltarCastButtonPressedMessageToServer.");

    buffer.writeResourceLocation(ritualResourceLocation);
  }

  public static AltarCastButtonPressedMessageToServer decode(PacketBuffer buffer) {
    MagiksMostEvile.LOGGER.debug("Decoding AltarCastButtonPressedMessageToServer");

    ResourceLocation ritualResourceLocation = buffer.readResourceLocation();

    return new AltarCastButtonPressedMessageToServer(ritualResourceLocation);
  }

  public boolean isValid() {
    return messageIsValid;
  }

  /**
   * @return the ritualName
   */
  public ResourceLocation getRitualResourceLocation() {
    return ritualResourceLocation;
  }
}
