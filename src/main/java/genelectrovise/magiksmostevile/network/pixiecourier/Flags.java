package genelectrovise.magiksmostevile.network.pixiecourier;

import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;

public class Flags {

  /*
   * The desired processor to process this packet (if no mapping for this, then will default to a
   * processor for the class)
   */
  public static final String F_PROCESSOR = "processor";

  protected String[] flags;
  protected Map<String, Integer> mappings;

  public Flags(String[] flags) {
    addFlags(flags);
  }

  protected void addFlags(String[] flags) {

    if (!(flags.length % 2 == 0)) {
      throw new ArrayIndexOutOfBoundsException("The given flags array " + ArrayUtils.toString(flags) + " does not fulfil 'length % 2 == 0', therefore does not consist of pairs.");
    }

    for (int i = 0; i < flags.length; i++) {
      ArrayUtils.add(this.flags, flags[i]);
    }

    refreshMappings();
  }

  protected void refreshMappings() {
    mappings.clear();

    for (int i = 0; i < flags.length; i++) {
      String flag = flags[i];

      mappings.put(flag, Integer.valueOf(i));
    }
  }

  protected String get(String key) {
    return flags[mappings.get(key).intValue()];
  }

  // Get and set

  public String[] getFlags() {
    return flags;
  }

  public void setFlags(String[] flags) {
    this.flags = flags;
  }

  public Map<String, Integer> getMappings() {
    return mappings;
  }

  public void setMappings(Map<String, Integer> mappings) {
    this.mappings = mappings;
  }

}
