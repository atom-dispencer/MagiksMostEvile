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
import net.minecraft.resources.ResourceLocation;

/**
 * @author GenElectrovise 12 Jun 2020
 */
public class AltarToggleButtonMessageToServer {

    private ToggleDirection toggleDirection;
    private ResourceLocation ritualRL;

    public AltarToggleButtonMessageToServer(int direction, ResourceLocation rLoc) {
        if (direction == 1) {
            this.toggleDirection = ToggleDirection.LEFT;
        } else if (direction == 2) {
            this.toggleDirection = ToggleDirection.RIGHT;
        } else {
            throw new IllegalArgumentException("Invalid integer for toggle direction!");
        }

        this.ritualRL = rLoc;
    }

    public AltarToggleButtonMessageToServer(ToggleDirection direction, ResourceLocation rLoc) {
        this.toggleDirection = direction;
        this.ritualRL = rLoc;
    }

    public static AltarToggleButtonMessageToServer decode(PacketBuffer buffer) {
        int directionInt = buffer.readInt();
        ResourceLocation rl = buffer.readResourceLocation();
        return new AltarToggleButtonMessageToServer(directionInt, rl);
    }

    public void encode(PacketBuffer buffer) {
        if (toggleDirection == ToggleDirection.LEFT) {
            buffer.writeInt(1);
        } else if (toggleDirection == ToggleDirection.RIGHT) {
            buffer.writeInt(2);
        } else {
            buffer.writeInt(0);
        }

        buffer.writeResourceLocation(ritualRL);
    }

    /**
     * @return the ritualRL
     */
    public ResourceLocation getRitualRL() {
        return ritualRL;
    }

    /**
     * @return the toggleDirection
     */
    public ToggleDirection getToggleDirection() {
        return toggleDirection;
    }

    /**
     * @return Whether this message is valid. I.e. whether it has all of the needed attributes
     */
    public boolean isValid() {
        if (!(toggleDirection instanceof ToggleDirection)) {
            return false;
        }

        if (ritualRL == null) {
            return false;
        }

        return true;
    }

    public static enum ToggleDirection {
        LEFT, RIGHT;
    }
}
