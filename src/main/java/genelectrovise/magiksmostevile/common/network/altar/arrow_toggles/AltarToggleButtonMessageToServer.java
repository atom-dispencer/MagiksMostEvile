/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.altar.arrow_toggles;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

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

	public static AltarToggleButtonMessageToServer decode(PacketBuffer buffer) {
		int directionInt = buffer.readInt();
		ResourceLocation rl = buffer.readResourceLocation();
		return new AltarToggleButtonMessageToServer(directionInt, rl);
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

	public static enum ToggleDirection {
		LEFT, RIGHT;
	}

	/**
	 * @return Whether this message is valid. I.e. whether it has all of the needed
	 *         attributes
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
}
