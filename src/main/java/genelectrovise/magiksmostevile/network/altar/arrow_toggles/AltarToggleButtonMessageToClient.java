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
package genelectrovise.magiksmostevile.network.altar.arrow_toggles;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

/**
 * @author GenElectrovise 12 Jun 2020
 */
public class AltarToggleButtonMessageToClient {

    private ResourceLocation location;

    /**
     * @param resourceLocation
     */
    public AltarToggleButtonMessageToClient(ResourceLocation resourceLocation) {
        this.location = resourceLocation;
    }

    public static AltarToggleButtonMessageToClient decode(PacketBuffer buffer) {
        ResourceLocation rl = buffer.readResourceLocation();

        return new AltarToggleButtonMessageToClient(rl);
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeResourceLocation(location);
    }

    public boolean isValid() {
        if (this.location == null) {
            return false;
        }

        return true;
    }

    /**
     * @return the location
     */
    public ResourceLocation getLocation() {
        return location;
    }
}
