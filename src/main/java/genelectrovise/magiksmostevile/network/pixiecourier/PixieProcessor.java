package genelectrovise.magiksmostevile.network.pixiecourier;

import java.util.List;

public interface PixieProcessor {
  
  void process(PixiePacket packet);
  
  List<Class<?>> getCompatibleTypes();
}
