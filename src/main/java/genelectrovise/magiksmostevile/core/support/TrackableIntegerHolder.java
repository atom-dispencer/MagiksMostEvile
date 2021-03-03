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
package genelectrovise.magiksmostevile.core.support;

import net.minecraft.util.IntReferenceHolder;

/**
 * @author GenElectrovise 22 May 2020
 */
public class TrackableIntegerHolder extends IntReferenceHolder {

  public int value;
  public String nbtKey;

  public TrackableIntegerHolder(int value) {
    this(value, "trackableIntegerHolderKey");
  }

  public TrackableIntegerHolder(int value, String nbtKey) {
    this.value = value;
    this.nbtKey = nbtKey;
  }

  @Override
  public int get() {
    return value;
  }

  @Override
  public void set(int value) {
    this.value = value;
  }

}
