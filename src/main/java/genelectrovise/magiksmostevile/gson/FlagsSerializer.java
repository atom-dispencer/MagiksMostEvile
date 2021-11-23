package genelectrovise.magiksmostevile.gson;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import genelectrovise.magiksmostevile.network.pixiecourier.Flags;
import genelectrovise.magiksmostevile.network.pixiecourier.PixiePacket;

public class FlagsSerializer implements JsonSerializer<Flags> {

  public FlagsSerializer() {}

  @Override
  public JsonElement serialize(Flags flags, Type type, JsonSerializationContext context) {
    Gson gson = new Gson();
    JsonElement json = gson.toJsonTree(flags.getFlags());
    
    JsonObject object = new JsonObject();
    object.add(PixiePacket.FLAGS, json);
    
    return object;
  }

}
