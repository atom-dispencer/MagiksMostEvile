/**
 * 
 */
package genelectrovise.magiksmostevile.common.ritual;

import genelectrovise.magiksmostevile.common.tileentity.altar.AltarTileEntity;
import net.minecraftforge.registries.ForgeRegistryEntry;

/**
 * @author GenElectrovise 19 May 2020
 */
public abstract class Ritual extends ForgeRegistryEntry<Ritual> {
	protected String displayName;
	protected String description;
	private AltarTileEntity altar;

	@Override
	public String toString() {
		return "Ritual{" + getRegistryName() + "}";
	}

	/**
	 * 
	 */
	public Ritual(String registryName, String displayName, String description) {
		this.displayName = displayName;
		this.description = description;
	}

	/**
	 * Starts casting the ritual
	 */
	public abstract void begin();

	/**
	 * Equivalent of a constructor, where one isn't easy to implement.
	 * 
	 * @param altarTileEntity
	 */
	public abstract void init(AltarTileEntity altarTileEntity);

	/**
	 * Attempts to start the ritual. Should do some preliminary checks here, then
	 * call <code>super.tryStart()</code> to run
	 * <code>if(this.canBegin){this.begin}</code>, if no custom implementation is
	 * needed.
	 */
	public void tryStart() {
		if (this.canStart()) {
			this.begin();
		}
	}

	/**
	 * @return Whether the ritual is able to start.
	 */
	protected abstract boolean canStart();

	// Get and set

	/**
	 * @return The name
	 */
	public final String getDisplayName() {
		return displayName;
	}

	/**
	 * @return The description of the ritual
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * @return the altar
	 */
	public final AltarTileEntity getAltar() {
		return altar;
	}
}
