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
package genelectrovise.magiksmostevile.entity.boss.kitty_the_kraken;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.util.INBTSerializable;

public class KrakenArm implements INBTSerializable<CompoundNBT> {

  private final KittyTheKrakenEntity kraken;
  private KrakenArmSide side;
  private boolean usable;

  public KrakenArm(KittyTheKrakenEntity kraken, KrakenArmSide side) {
    this.kraken = kraken;
    this.side = side;
    this.usable = true;
  }

  @Override
  public CompoundNBT serializeNBT() {

    CompoundNBT nbt = new CompoundNBT();
    nbt.putString("krakenArmSide", side.getString());

    return nbt;
  }

  @Override
  public void deserializeNBT(CompoundNBT nbt) {
    this.side = KrakenArmSide.fromString(nbt.getString("krakenArmSide"));
  }

  // Get and set

  public KittyTheKrakenEntity getKraken() {
    return kraken;
  }

  public KrakenArmSide getSide() {
    return side;
  }

  public void setSide(KrakenArmSide side) {
    this.side = side;
  }

  public boolean isUsable() {
    return usable;
  }

  public void setUsable(boolean usable) {
    this.usable = usable;
  }

  // Classes

  public static enum KrakenArmSide implements IStringSerializable {
    LEFT, RIGHT;

    @Override
    public String getString() {
      return this.name();
    }

    public static KrakenArmSide fromString(String string) {
      return KrakenArmSide.valueOf(string);
    }
  }

}
