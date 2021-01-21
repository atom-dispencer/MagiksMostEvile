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
