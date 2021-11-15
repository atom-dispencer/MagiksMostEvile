package genelectrovise.magiksmostevile.network.pixiecourier;

import java.util.List;
import net.minecraftforge.fml.network.NetworkEvent;

public interface PixieProcessor {

  void process(PixiePacket packet, NetworkEvent.Context context);

  List<Class<?>> getCompatibleTypes();
}
