package genelectrovise.magiksmostevile.item.pixiecourier;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Map;
import java.util.Objects;

public class Flags {

    /*
     * The desired processor to process this packet (if no mapping for this, then will default to a
     * processor for the class)
     */
    public static final String F_PROCESSOR = "processor";

    public static final Flags NO_FLAGS = new Flags(new String[]{});

    protected String[] flags;
    protected Map<String, Integer> mappings;

    public Flags(String[] flags) {
        this.mappings = Maps.newHashMap();
        this.flags = flags;

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

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Flags))
            return false;

        Flags flags = (Flags) obj;

        if (!Objects.deepEquals(this.flags, flags.flags))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return String.format("Flags{%s}", ArrayUtils.toString(flags));
    }
}
