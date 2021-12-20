package genelectrovise.magiksmostevile.network.pixiecourier;

import java.util.Map;
import com.google.common.collect.Maps;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkEvent;

public interface PixieProcessor {

  void process(PixiePacket packet, NetworkEvent.Context context);

  public static class Registry {

    public static final String CANNOT_REGISTER_PROCESSOR_AS_TYPE_NULL = "Cannot register processor as type null";
    public static final String CANNOT_REGISTER_PROCESSOR_AS_PROCESSOR_NULL = "Cannot register processor as processor null";
    public static final String NO_PROCESSOR_FOR_TYPE = "No processor for type";
    public static final String CANNOT_GET_PROCESSOR_AS_TYPE_NULL = "Cannot get processor as type null";



    // Static
    protected static final Registry INSTANCE = new Registry();

    public static Registry getInstance() { return INSTANCE; }

    // Instance
    protected Map<Class<?>, PixieProcessor> processors = Maps.newHashMap();

    private Registry() {}

    /**
     * Associates the given type and processor in the {@link #processors} {@link Map}.
     * 
     * @param type
     * @param processor
     * @throws CourierException
     */
    public void register(Class<?> type, PixieProcessor processor) throws CourierException {

      // Null check type
      if (type == null) {
        throw new CourierException(CANNOT_REGISTER_PROCESSOR_AS_TYPE_NULL);
      }

      // Null check processor
      if (processor == null) {
        throw new CourierException(CANNOT_REGISTER_PROCESSOR_AS_PROCESSOR_NULL);
      }

      // Put
      processors.put(type, processor);
    }

    /**
     * Gets a processor for the given type from the {@link #processors} {@link Map}.
     * 
     * @param type
     * @return
     * @throws CourierException
     */
    public PixieProcessor get(Class<?> type) throws CourierException {

      // Null check type
      if (type == null) {
        throw new CourierException(CANNOT_GET_PROCESSOR_AS_TYPE_NULL);
      }
      
      PixieProcessor processor = processors.get(type);
      
      // Null check processor
      if (processor == null) {
        throw new CourierException(NO_PROCESSOR_FOR_TYPE + ": " + type.getName());
      }

      return processor;
    }

    @SubscribeEvent
    void registerProcessors(FMLCommonSetupEvent event) {

    }
  }
}
