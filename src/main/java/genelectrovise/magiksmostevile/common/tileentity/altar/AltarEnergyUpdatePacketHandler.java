/**
 * 
 */
package genelectrovise.magiksmostevile.common.tileentity.altar;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

/**
 * Handles messages containing a BlockPos (location of the updating Altar), the
 * current energy, and the maximum energy of the Altar.
 * 
 * @author GenElectrovise 20 May 2020
 */
public class AltarEnergyUpdatePacketHandler {
	public static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(MagiksMostEvile.MODID, "altar_energy_update"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	// https://mcforge.readthedocs.io/en/1.15.x/networking/simpleimpl/
}
