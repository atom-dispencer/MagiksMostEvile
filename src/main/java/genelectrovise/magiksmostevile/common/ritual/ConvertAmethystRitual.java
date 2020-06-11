/**
 * 
 */
package genelectrovise.magiksmostevile.common.ritual;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.tileentity.altar.AltarTileEntity;
import net.minecraft.nbt.CompoundNBT;

/**
 * @author GenElectrovise 19 May 2020
 */
public class ConvertAmethystRitual extends Ritual {
	public static final String registryName = "Magikify Amethyst";
	public static final String displayName = "Magikify Amethyst";
	public static final String description = "Imbue an amethyst with magiky powers!";
	
	private AltarTileEntity altar;

	public ConvertAmethystRitual() {
		super(registryName, displayName, description);
	}

	@Override
	public void begin() {
		MagiksMostEvile.LOGGER.debug("Beginning RitualConvertAmethyst!");
	}
	
	@Override
	public void init(AltarTileEntity altarTileEntity) {
		this.altar = altarTileEntity;
		MagiksMostEvile.LOGGER.dev("init RitualConvertAmethyst!");
	}
	
	@Override
	protected boolean canStart() {
		MagiksMostEvile.LOGGER.dev("canStart RitualConvertAmethyst!");
		return true;
	}

}
