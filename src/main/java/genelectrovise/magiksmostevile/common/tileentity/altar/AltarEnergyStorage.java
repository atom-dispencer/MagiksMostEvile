/**
 * 
 */
package genelectrovise.magiksmostevile.common.tileentity.altar;

import genelectrovise.magiksmostevile.common.tileentity.AmethystFluxEnergyStorage;

/**
 * @author GenElectrovise 23 May 2020
 */
public class AltarEnergyStorage extends AmethystFluxEnergyStorage {

	/**
	 * @param capacity
	 * @param maxReceive
	 * @param maxExtract
	 * @param energy
	 * @param nbtKey
	 */
	public AltarEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy, String nbtKey) {
		super(capacity, maxReceive, maxExtract, energy, nbtKey);
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		// send packet
		return super.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		// send packet
		return super.extractEnergy(maxExtract, simulate);
	}

}
