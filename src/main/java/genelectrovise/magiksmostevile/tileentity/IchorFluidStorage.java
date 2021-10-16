/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise
 *
 * This file is part of Magiks Most Evile. Magiks Most Evile is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile.
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
/**
 * 
 */
package genelectrovise.magiksmostevile.tileentity;

import genelectrovise.magiksmostevile.core.support.TrackableIntegerHolder;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author GenElectrovise 20 May 2020
 */
public class IchorFluidStorage extends SingleTankFluidStorage {

  public TrackableIntegerHolder maxIchor;
  public TrackableIntegerHolder currentIchor;
  public String nbtKey;

  /**
   * @param capacity
   * @param maxReceive
   * @param maxExtract
   * @param energy
   */
  public IchorFluidStorage(int capacity, String nbtKey) {
    super(capacity, nbtKey);
  }

  public void update() {
    this.currentIchor.set(this.getFluidAmount());
    this.maxIchor.set(this.getCapacity());
  }

  /**
   * @param compound
   */
  public void fromNbt(CompoundNBT compound) {
    currentIchor.set(compound.getInt("currentIchor"));    
    setCapacity(compound.getInt("capacity"));
  }

  /**
   * @return
   */
  public CompoundNBT toNbt() {
    CompoundNBT tag = new CompoundNBT();
    tag.putInt("currentIchor", currentIchor.get());
    tag.putInt("capacity", getCapacity());

    return tag;
  }

  @Override
  public boolean isFluidValid(FluidStack stack) {
    return super.isFluidValid(stack) && stack.getFluid() == Fluids.LAVA;
  }

  @Override
  public int fill(FluidStack resource, FluidAction action) {
    int filled = super.fill(resource, action);
    update();
    return filled;
  }

  @Override
  public FluidStack drain(FluidStack resource, FluidAction action) {
    FluidStack drained = super.drain(resource, action);
    update();
    return drained;
  }

  public TrackableIntegerHolder getMaxIchor() {
    return maxIchor;
  }

  public void setMaxIchor(TrackableIntegerHolder maxIchor) {
    this.maxIchor = maxIchor;
  }

  public TrackableIntegerHolder getCurrentIchor() {
    return currentIchor;
  }

  public void setCurrentIchor(TrackableIntegerHolder currentIchor) {
    this.currentIchor = currentIchor;
  }

  public String getNbtKey() {
    return nbtKey;
  }

  public void setNbtKey(String nbtKey) {
    this.nbtKey = nbtKey;
  }

}
