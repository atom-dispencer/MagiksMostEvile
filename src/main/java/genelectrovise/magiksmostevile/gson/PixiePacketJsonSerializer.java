package genelectrovise.magiksmostevile.gson;

import com.google.gson.*;
import genelectrovise.magiksmostevile.item.pixiecourier.PixiePacket;

import java.lang.reflect.Type;

public class PixiePacketJsonSerializer implements JsonSerializer<PixiePacket> {

    @Override
    public JsonElement serialize(PixiePacket packet, Type packetType, JsonSerializationContext context) {
        Gson gson = GsonConfigurator.newConfiguredInstance();

        JsonObject object = new JsonObject();
        object.addProperty(PixiePacket.TYPE, packet.getType().getName());
        object.add(PixiePacket.FLAGS, gson.toJsonTree(packet.getFlags()));
        object.add(PixiePacket.CONTENT, gson.toJsonTree(packet.getContent()));

        return object;
    }

}
