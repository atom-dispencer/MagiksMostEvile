/**
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise
 * <p>
 * This file is part of Magiks Most Evile. Magiks Most Evile is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 * <p>
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile.
 * If not, see <https://www.gnu.org/licenses/>.
 */
/**
 *
 */
package genelectrovise.magiksmostevile.tileentity;

import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author GenElectrovise 20 May 2020
 */
public class IchorFluidStorage extends SingleTankFluidStorage {

    public IchorFluidStorage(int capacity, String nbtKey) {
        super(capacity, nbtKey, (fluidStack) -> fluidStack.getFluid() == Fluids.LAVA);
    }
}
