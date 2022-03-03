package genelectrovise.magiksmostevile.gson;

import com.google.gson.*;
import genelectrovise.magiksmostevile.item.pixiecourier.Flags;
import genelectrovise.magiksmostevile.item.pixiecourier.PixiePacket;

import java.lang.reflect.Type;

public class FlagsSerializer implements JsonSerializer<Flags> {

    public FlagsSerializer() {
    }

    @Override
    public JsonElement serialize(Flags flags, Type type, JsonSerializationContext context) {
        Gson gson = new Gson();
        JsonElement json = gson.toJsonTree(flags.getFlags());

        JsonObject object = new JsonObject();
        object.add(PixiePacket.FLAGS, json);

        return object;
    }

}
