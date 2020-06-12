/**
 * 
 */
package genelectrovise.magiksmostevile.common.network.altar.arrow_toggles;

import java.util.jar.Pack200.Packer;

import net.minecraft.network.PacketBuffer;

/**
 * @author GenElectrovise 12 Jun 2020
 */
public class AltarToggleButtonMessageToClient {

	public void encode(PacketBuffer buffer) {

	}

	public static AltarToggleButtonMessageToClient decode(PacketBuffer buffer) {
		return new AltarToggleButtonMessageToClient();
	}
}
