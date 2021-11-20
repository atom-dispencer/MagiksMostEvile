package genelectrovise.magiksmostevile.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import genelectrovise.magiksmostevile.network.pixiecourier.Flags;
import genelectrovise.magiksmostevile.network.pixiecourier.PixiePacket;

public class GsonConfigurator {

  private GsonConfigurator() {}

  public static Gson newConfiguredInstance() {

    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(Flags.class, new FlagsSerializer());
    builder.registerTypeAdapter(PixiePacket.class, new PixiePacketJsonDeserializer());
    builder.registerTypeAdapter(PixiePacket.class, new PixiePacketJsonSerializer());

    return builder.create();
  }

}
