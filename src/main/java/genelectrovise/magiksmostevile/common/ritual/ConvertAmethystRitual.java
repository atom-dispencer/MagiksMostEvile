/**
 * 
 */
package genelectrovise.magiksmostevile.common.ritual;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import net.minecraft.nbt.CompoundNBT;

/**
 * @author GenElectrovise 19 May 2020
 */
public class ConvertAmethystRitual extends Ritual {
	public static final String registryName = "Magikify Amethyst";
	public static final String displayName = "Magikify Amethyst";
	public static final String description = "Imbue an amethyst with magiky powers!";

	public ConvertAmethystRitual() {
		super(registryName, displayName, description);
	}

	@Override
	public void begin() {
		MagiksMostEvile.LOGGER.debug("Beginning RitualConvertAmethyst!");
	}
			
	@Override
	public CompoundNBT serializeNBT() {
		return null;
	}
	
	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		
	}

}
