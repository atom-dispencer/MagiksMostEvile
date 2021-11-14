package genelectrovise.magiksmostevile.network.pixiecourier;

import java.util.function.Supplier;
import com.google.gson.Gson;
import genelectrovise.magiksmostevile.gson.GsonConfigurator;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class PacketEncoder {

  private PacketEncoder() {}
  
  public static void recievePacket(final PixiePacket message, Supplier<NetworkEvent.Context> contextSupplier) {
    PixieCourier.recieve(message, contextSupplier.get());
  }
  
  public static void encode(PixiePacket packet, PacketBuffer buffer) {
    Gson gson = GsonConfigurator.newConfiguredInstance();
    buffer.writeUtf(gson.toJson(packet));
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
