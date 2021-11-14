package genelectrovise.magiksmostevile.network.pixiecourier;

import java.util.Map;
import com.google.common.collect.Maps;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class PacketDistributor {
  
  protected static Map<String, PixieProcessor> processors = Maps.newHashMap();
  
  @SubscribeEvent
  void registerProcessors(FMLCommonSetupEvent event) {
    
  }
  
  public static void registerProcessor(String key, PixieProcessor processor) {
    processors.put(key, processor);
  }
  
  //

  public PacketDistributor() {
    processors = Maps.newHashMap();
  }

  public PixieProcessor get(Class<?> type, String string) {
    PixieProcessor processor = processors.get(string);
    
    if(!processor.getCompatibleTypes().contains(type)) {
      
    }
  }

}
