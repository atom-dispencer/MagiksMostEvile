/**
 * 
 */
package genelectrovise.magiksmostevile.common.tileentity;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.core.support.TrackableIntegerHolder;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.energy.EnergyStorage;

/**
 * @author GenElectrovise 20 May 2020
 */
public class IchorFluidStorage extends EnergyStorage {

  public TrackableIntegerHolder maxIchor;
  public TrackableIntegerHolder currentIchor;
  public String nbtKey;

  /**
   * @param capacity
   * @param maxReceive
   * @param maxExtract
   * @param energy
   */
  public IchorFluidStorage(int capacity, int maxReceive, int maxExtract, int energy,
      String nbtKey) {
    super(capacity, maxReceive, maxExtract, energy);
    this.nbtKey = nbtKey;

    currentIchor = new TrackableIntegerHolder(getEnergyStored(),
        MagiksMostEvile.MODID + ":currentIchor");
    maxIchor = new TrackableIntegerHolder(getMaxEnergyStored(),
        MagiksMostEvile.MODID + ":maxIchor");
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
    maxIchor.set(this.capacity);
  }

  /**
   * @param compound
   */
  public void fromNbt(CompoundNBT compound) {
    maxIchor.set(compound.getInt("maxIchor"));
    currentIchor.set(compound.getInt("currentIchor"));
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
    tag.putInt("maxIchor", maxIchor.get());
    tag.putInt("currentIchor", currentIchor.get());
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

    currentIchor.set(energy);

    return energyReceived;
  }

  @Override
  public int extractEnergy(int maxExtract, boolean simulate) {
    if (!canExtract())
      return 0;

    int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
    if (!simulate)
      energy -= energyExtracted;

    currentIchor.set(energy);

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
