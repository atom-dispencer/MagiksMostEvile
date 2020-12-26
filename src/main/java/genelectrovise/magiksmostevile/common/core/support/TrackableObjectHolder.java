/**
 * 
 */
package genelectrovise.magiksmostevile.common.core.support;

/**
 * @author GenElectrovise 11 Jun 2020
 */
public class TrackableObjectHolder<T> {
  private T lastKnownValue;
  private T value;

  /**
   * 
   */
  public TrackableObjectHolder(T value) {
    this.value = value;
  }

  public T get() {
    return this.value;
  }

  public void set(T value) {
    this.value = value;
  }

  public boolean isDirty() {
    T i = this.get();
    boolean flag = i != this.lastKnownValue;
    this.lastKnownValue = i;
    return flag;
  }

}
