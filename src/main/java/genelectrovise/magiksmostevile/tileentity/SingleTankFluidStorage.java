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

import java.util.function.Predicate;
import javax.annotation.Nonnull;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.core.support.TrackableIntegerHolder;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;

/**
 * @author GenElectrovise 20 May 2020
 */
public class SingleTankFluidStorage implements IFluidHandler, IFluidTank {

  private int capacity;
  private Predicate<FluidStack> validator;
  private FluidStack fluidStack;

  public TrackableIntegerHolder maxIchor;
  public TrackableIntegerHolder currentIchor;
  public String nbtKey;

  /**
   * @param capacity
   * @param maxReceive
   * @param maxExtract
   * @param energy
   */
  public SingleTankFluidStorage(int capacity, String nbtKey) {
    this.nbtKey = nbtKey;

    currentIchor = new TrackableIntegerHolder(10, MagiksMostEvile.MODID + ":currentIchor");
    maxIchor = new TrackableIntegerHolder(50, MagiksMostEvile.MODID + ":capacity");

    update();
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
    capacity = compound.getInt("capacity");
  }

  /**
   * @return
   */
  public CompoundNBT toNbt() {
    CompoundNBT tag = new CompoundNBT();
    tag.putInt("currentIchor", currentIchor.get());
    tag.putInt("capacity", capacity);

    return tag;
  }

  @Override
  public boolean isFluidValid(FluidStack stack) {
    return validator.test(stack);
  }

  @Override
  public int fill(FluidStack resource, FluidAction action) {

    if (resource.isEmpty() || !isFluidValid(resource)) {
      return 0;
    }

    if (action.simulate()) {

      if (fluidStack.isEmpty()) {
        return Math.min(capacity, resource.getAmount());
      }

      if (!fluidStack.isFluidEqual(resource)) {
        return 0;
      }

      return Math.min(capacity - fluidStack.getAmount(), resource.getAmount());
    }

    if (fluidStack.isEmpty()) {
      fluidStack = new FluidStack(resource, Math.min(capacity, resource.getAmount()));
      update();
      return fluidStack.getAmount();
    }

    if (!fluidStack.isFluidEqual(resource)) {
      return 0;
    }

    int filled = capacity - fluidStack.getAmount();

    if (resource.getAmount() < filled) {
      fluidStack.grow(resource.getAmount());
      filled = resource.getAmount();
    } else {
      fluidStack.setAmount(capacity);
    }

    if (filled > 0) {
      update();
    }
    return filled;
  }

  @Nonnull
  @Override
  public FluidStack drain(FluidStack resource, FluidAction action) {
    if (resource.isEmpty() || !resource.isFluidEqual(fluidStack)) {
      return FluidStack.EMPTY;
    }
    return drain(resource.getAmount(), action);
  }

  @Nonnull
  @Override
  public FluidStack drain(int maxDrain, FluidAction action) {
    int drained = maxDrain;

    if (fluidStack.getAmount() < drained) {
      drained = fluidStack.getAmount();
    }

    FluidStack stack = new FluidStack(fluidStack, drained);
    if (action.execute() && drained > 0) {
      fluidStack.shrink(drained);
      update();
    }

    return stack;
  }

  @Override
  public FluidStack getFluid() { return fluidStack; }

  @Override
  public int getFluidAmount() { return fluidStack.getAmount(); }

  @Override
  public int getCapacity() { return capacity; }

  public void setCapacity(int capacity) { this.capacity = capacity; }

  @Override
  public int getTanks() { return 1; }

  @Override
  public FluidStack getFluidInTank(int tank) {
    return null;
  }

  @Override
  public int getTankCapacity(int tank) {
    return capacity;
  }

  @Override
  public boolean isFluidValid(int tank, FluidStack stack) {
    return isFluidValid(stack);
  }

}
