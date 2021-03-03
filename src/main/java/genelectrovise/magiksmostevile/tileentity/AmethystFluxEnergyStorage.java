/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise    
 *
 * This file is part of Magiks Most Evile.
 * Magiks Most Evile is free software: you can redistribute it and/or modify it under the terms 
 * of the GNU General Public License as published by the Free Software Foundation, 
 * either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE.  
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile. 
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
/**
 * 
 */
package genelectrovise.magiksmostevile.tileentity;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.core.support.TrackableIntegerHolder;
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
  public AmethystFluxEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy,
      String nbtKey) {
    super(capacity, maxReceive, maxExtract, energy);
    this.nbtKey = nbtKey;

    currentAmethystFlux = new TrackableIntegerHolder(getEnergyStored(),
        MagiksMostEvile.MODID + ":currentAmethystFlux");
    maxAmethystFlux = new TrackableIntegerHolder(getMaxEnergyStored(),
        MagiksMostEvile.MODID + ":maxAmethystFlux");
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

  public void receiveMax(int value) {
    int current = maxReceive;
    setMaxRecieve(value);
    receiveEnergy(value, false);
    setMaxRecieve(current);
  }

  public void extractMax(int value) {
    int current = maxExtract;
    setMaxExtract(value);
    receiveEnergy(value, false);
    setMaxExtract(current);
  }

  public void setMaxRecieve(int value) {
    maxReceive = value;
  }

  public void setMaxExtract(int value) {
    maxExtract = value;
  }

}
