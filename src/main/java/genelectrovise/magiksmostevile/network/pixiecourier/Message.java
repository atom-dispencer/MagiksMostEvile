package genelectrovise.magiksmostevile.network.pixiecourier;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import genelectrovise.magiksmostevile.guice.Guicer;
import net.minecraft.network.PacketBuffer;

public class Message<T> {

  public static final String PROTOCOL_VERSION = "0007";

  private JsonObject contentJson;
  private Gson GSON = Guicer.get(Gson.class);
  private JsonParser PARSER = Guicer.get(JsonParser.class);

  public Message(JsonElement contentJson) {
    this.contentJson = contentJson.getAsJsonObject();
  }

  public static boolean isProtocolGoodVersion(String protocolVersion) {
    return PixieCourier.MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
  }

  protected JsonElement parseToJsonObject(String json) {
    return PARSER.parse(json);
  }

  public static Message<?> decode(PacketBuffer buffer) {
    return null;
  }

  public Message<T> encode(PacketBuffer buffer) {
    return null;
  }

  public JsonObject getContentJson() {
    return contentJson;
  }

  public Gson getGSON() {
    return GSON;
  }

  public JsonParser getPARSER() {
    return PARSER;
  }
}
