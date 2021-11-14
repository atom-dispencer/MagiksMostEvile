package genelectrovise.magiksmostevile.gson;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import genelectrovise.magiksmostevile.network.pixiecourier.PixiePacket;

public class PixiePacketJsonSerializer implements JsonSerializer<PixiePacket> {

  @Override
  public JsonElement serialize(PixiePacket packet, Type packetType, JsonSerializationContext context) {
    Gson gson = GsonConfigurator.newConfiguredInstance();

    JsonObject object = new JsonObject();
    object.addProperty(PixiePacket.TYPE, packet.getType().getName());
    object.addProperty(PixiePacket.FLAGS, gson.toJson(packet.getFlags()));
    object.addProperty(PixiePacket.CONTENT, packet.getContent().toString());

    return object;
  }

}
