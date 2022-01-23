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
package genelectrovise.magiksmostevile.tileentity.altar;

import genelectrovise.magiksmostevile.tileentity.AmethystFluxEnergyStorage;

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
    public AltarEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy,
                              String nbtKey) {
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
