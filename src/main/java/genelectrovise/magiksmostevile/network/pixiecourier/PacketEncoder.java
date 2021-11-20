package genelectrovise.magiksmostevile.network.pixiecourier;

import com.google.gson.Gson;
import genelectrovise.magiksmostevile.gson.GsonConfigurator;
import net.minecraft.network.PacketBuffer;

public class PacketEncoder {

  public static final String PACKET_BUFFER_IS_NOT_WRITABLE_FOR_ENCODING_PIXIE_PACKET = "PacketBuffer is not writable for encoding PixiePacket";

  public PacketEncoder() {}
  
  public static String encode(PixiePacket packet, PacketBuffer buffer) throws CourierException {
    Gson gson = GsonConfigurator.newConfiguredInstance();
    String json = gson.toJson(packet);
    
    if(!buffer.isWritable()) {
      throw new CourierException(PACKET_BUFFER_IS_NOT_WRITABLE_FOR_ENCODING_PIXIE_PACKET);
    }
    
    buffer.writeUtf(json);
    return json;
  }

  private static boolean isBufferEmpty(PacketBuffer buffer) {
    // TODO Auto-generated method stub
    return false;
  }

  public static PixiePacket decode(PacketBuffer buffer) {
    Gson gson = GsonConfigurator.newConfiguredInstance();
    String json = buffer.readUtf();
    PixiePacket packet = gson.fromJson(json, PixiePacket.class);
    
    return packet;
  }

  public static boolean isValidVersion(String version) {
    return true;
  }

}
