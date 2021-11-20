package genelectrovise.magiksmostevile.gson;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import genelectrovise.magiksmostevile.network.pixiecourier.Flags;
import genelectrovise.magiksmostevile.network.pixiecourier.PixiePacket;

public class PixiePacketJsonDeserializer implements JsonDeserializer<PixiePacket> {

  @Override
  public PixiePacket deserialize(JsonElement element, Type packetType, JsonDeserializationContext context) throws JsonParseException {
    JsonObject object = element.getAsJsonObject();

    Gson gson = GsonConfigurator.newConfiguredInstance();
    String typeName = object.get(PixiePacket.TYPE).getAsString();
    Flags flags = gson.fromJson(object.get(PixiePacket.FLAGS), Flags.class);
    JsonObject content = gson.fromJson(object.get(PixiePacket.CONTENT), JsonObject.class);

    // Default to JsonObject if fails later
    Class<?> type = JsonObject.class;

    try {
      type = Class.forName(typeName);
    } catch (ClassNotFoundException cnf) {
      cnf.printStackTrace();
    }

    return new PixiePacket(type, content, flags);
  }

}
