/**
 * 
 */
package genelectrovise.magiksmostevile.common.tileentity.altar;

import net.minecraftforge.energy.EnergyStorage;

/**
 * @author GenElectrovise 20 May 2020
 */
public class AltarEnergyStorage extends EnergyStorage {

	/**
	 * @param capacity
	 * @param maxReceive
	 * @param maxExtract
	 * @param energy
	 */
	public AltarEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
		super(capacity, maxReceive, maxExtract, energy);
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

}
