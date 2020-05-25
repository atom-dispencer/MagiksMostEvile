/**
 * 
 */
package genelectrovise.magiksmostevile.common.tileentity;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.main.support.TrackableIntegerHolder;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.energy.EnergyStorage;

/**
 * @author GenElectrovise 20 May 2020
 */
public class AmethystFluxEnergyStorage extends EnergyStorage {

	public TrackableIntegerHolder maxAmethystFlux;
	public TrackableIntegerHolder currentAmethystFlux;
	public String nbtKey;

	/**
	 * @param capacity
	 * @param maxReceive
	 * @param maxExtract
	 * @param energy
	 */
	public AmethystFluxEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy, String nbtKey) {
		super(capacity, maxReceive, maxExtract, energy);
		this.nbtKey = nbtKey;

		currentAmethystFlux = new TrackableIntegerHolder(getEnergyStored(), MagiksMostEvile.MODID + ":currentAmethystFlux");
		maxAmethystFlux = new TrackableIntegerHolder(getMaxEnergyStored(), MagiksMostEvile.MODID + ":maxAmethystFlux");
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
		maxAmethystFlux.set(this.capacity);
	}

	/**
	 * @param compound
	 */
	public void fromNbt(CompoundNBT compound) {
		maxAmethystFlux.set(compound.getInt("maxAmethystFlux"));
		currentAmethystFlux.set(compound.getInt("currentAmethystFlux"));
		capacity = compound.getInt("capacity");
		maxReceive = compound.getInt("maxReceive");
		maxExtract = compound.getInt("maxExtract");
		energy = compound.getInt("energy");
	}

	/**
	 * @return
	 */
	public CompoundNBT toNbt() {
		CompoundNBT tag = new CompoundNBT();
		tag.putInt("maxAmethystFlux", maxAmethystFlux.get());
		tag.putInt("currentAmethystFlux", currentAmethystFlux.get());
		tag.putInt("capacity", capacity);
		tag.putInt("maxReceive", maxReceive);
		tag.putInt("maxExtract", maxExtract);
		tag.putInt("energy", energy);

		return tag;
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		if (!canReceive())
			return 0;

		int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));
		if (!simulate)
			energy += energyReceived;

		currentAmethystFlux.set(energy);

		return energyReceived;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		if (!canExtract())
			return 0;

		int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
		if (!simulate)
			energy -= energyExtracted;

		currentAmethystFlux.set(energy);

		return energyExtracted;
	}

}
