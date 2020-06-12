/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.altar.arrow_toggles;

import net.minecraft.network.PacketBuffer;

/**
 * @author GenElectrovise 12 Jun 2020
 */
public class AltarToggleButtonMessageToServer {

	private ToggleDirection toggleDirection;

	public AltarToggleButtonMessageToServer(int direction) {
		if (direction == 1) {
			this.toggleDirection = ToggleDirection.LEFT;
		} else if (direction == 2) {
			this.toggleDirection = ToggleDirection.RIGHT;
		} else {
			throw new IllegalArgumentException("Invalid integer for toggle direction!");
		}
	}

	public AltarToggleButtonMessageToServer(ToggleDirection direction) {
		this.toggleDirection = direction;
	}

	public void encode(PacketBuffer buffer) {
		if (toggleDirection == ToggleDirection.LEFT) {
			buffer.writeInt(1);
		} else if (toggleDirection == ToggleDirection.RIGHT) {
			buffer.writeInt(2);
		} else {
			buffer.writeInt(0);
		}
	}

	public static AltarToggleButtonMessageToServer decode(PacketBuffer buffer) {
		int directionInt = buffer.readInt();
		return new AltarToggleButtonMessageToServer(directionInt);
	}

	public static enum ToggleDirection {
		LEFT, RIGHT;
	}
}
