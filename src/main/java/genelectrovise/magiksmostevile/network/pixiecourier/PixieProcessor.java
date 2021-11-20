package genelectrovise.magiksmostevile.network.pixiecourier;

import java.util.Map;
import com.google.common.collect.Maps;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkEvent;

public interface PixieProcessor {

  void process(PixiePacket packet, NetworkEvent.Context context);

  public static class Registry {

    public static final Registry INSTANCE = new Registry();
    protected Map<Class<?>, PixieProcessor> processors = Maps.newHashMap();

    private Registry() {}

    public static Registry getInstance() { return INSTANCE; }

    public void register(Class<?> type, PixieProcessor processor) {
      INSTANCE.processors.put(type, processor);
    }

    public PixieProcessor get(Class<?> type) throws CourierException {
      PixieProcessor processor = PixieProcessor.Registry.INSTANCE.get(type);

      if (processor == null) {
        throw new CourierException("No " + PixieProcessor.class.getSimpleName() + " registered for type=" + type.getName());
      }

      return processor;
    }

    @SubscribeEvent
    void registerProcessors(FMLCommonSetupEvent event) {

    }
  }
}
