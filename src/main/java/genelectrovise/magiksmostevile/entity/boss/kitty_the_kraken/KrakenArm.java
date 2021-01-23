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
