/**
 * 
 */
package genelectrovise.magiksmostevile.common.ritual;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.registries.ForgeRegistryEntry;

/**
 * @author GenElectrovise 19 May 2020
 */
public abstract class Ritual extends ForgeRegistryEntry<Ritual> implements INBTSerializable<CompoundNBT> {
	protected String displayName;
	protected String description;

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
	 * Starts casting the ritual
	 */
	public abstract void begin();
}
