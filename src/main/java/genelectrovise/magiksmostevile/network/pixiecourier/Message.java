package genelectrovise.magiksmostevile.network.pixiecourier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.network.PacketBuffer;

public class Message<T> {

  private JsonObject contentJson;
  private final Gson GSON = new GsonBuilder().create();
  private final JsonParser PARSER = new JsonParser();

  public Message(T contentJava) {
    contentJson = parseToJsonObject(GSON.toJson(contentJson)).getAsJsonObject();
  }

  public static boolean isProtocolGoodVersion(String protocolVersion) {
    return PixieCourier.MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
  }
  
  private JsonElement parseToJsonObject(String json) {
    JsonParser parser = new JsonParser();
    return parser.parse(GSON.toJson(json));
  }

  public static Message<?> decode(PacketBuffer buffer) {
    return null;
  }

  public Message<T> encode(PacketBuffer buffer) {
    return null;
  }
}
