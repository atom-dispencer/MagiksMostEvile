package genelectrovise.magiksmostevile.item.pixiecourier;

import com.google.gson.JsonObject;

public class PixiePacket {

    public static final String TYPE = "type";
    public static final String FLAGS = "flags";
    public static final String CONTENT = "content";

    protected Class<?> type;
    protected Flags flags;
    protected JsonObject content;

    public PixiePacket(Class<?> type, JsonObject content, Flags flags) {
        this.type = type;
        this.content = content;
        this.flags = flags;
    }

    // Get and set

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    public JsonObject getContent() {
        return content;
    }

    public void setContent(JsonObject content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PixiePacket))
            return false;

        PixiePacket packet = (PixiePacket) obj;

        if (!type.equals(packet.getType()))
            return false;
        if (!flags.equals(packet.getFlags()))
            return false;
        if (!content.equals(packet.getContent()))
            return false;

        return true;
    }
}
