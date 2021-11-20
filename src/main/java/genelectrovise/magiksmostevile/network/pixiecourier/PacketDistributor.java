package genelectrovise.magiksmostevile.network.pixiecourier;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.common.collect.Maps;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class PacketDistributor {

  public static final String PIXIE_PACKET_COULD_NOT_BE_PROCESSED_AS_NO_NULL_PROCESSOR_WAS_FOUND = "PixiePacket could not be processed as no (null) processor was found.";
  public static final String PIXIE_PACKET_COULD_NOT_BE_PROCESSED_AS_THE_PACKET_WAS_NULL = "PixiePacket could not be processed as the packet was null.";

  public static final Logger LOGGER = LogManager.getLogger(PacketDistributor.class);

  protected static Map<String, PixieProcessor> str_processors = Maps.newHashMap();
  protected static Map<Class<?>, PixieProcessor> cla_processors = Maps.newHashMap();

  @SubscribeEvent
  void registerProcessors(FMLCommonSetupEvent event) {

  }

  public static void registerProcessor(String key, PixieProcessor processor) {
    str_processors.put(key, processor);
  }

  //

  public PacketDistributor() {
    str_processors = Maps.newHashMap();
    cla_processors = Maps.newHashMap();
  }

  public void forwardPacketToProcessor(PixiePacket packet, Context context) throws CourierException {

      // Null check packet
      if (packet == null)
        throw new CourierException(PIXIE_PACKET_COULD_NOT_BE_PROCESSED_AS_THE_PACKET_WAS_NULL);

      // Find the processor for the packet type
      PixieProcessor processor = get(packet.getType(), packet.getFlags().get(Flags.F_PROCESSOR));
      if (processor == null) {
        throw new CourierException(PIXIE_PACKET_COULD_NOT_BE_PROCESSED_AS_NO_NULL_PROCESSOR_WAS_FOUND);
      }

      // Process
      processor.process(packet, context);

  }

  /**
   * Get a processor for the given type and name.
   * 
   * @param type
   * @param name
   * @return
   */
  public PixieProcessor get(Class<?> type, String name) {
    PixieProcessor processor;
    processor = str_processors.get(name);

    if (!processor.getCompatibleTypes().contains(type) || processor == null) {
      processor = cla_processors.get(type);
    }

    if (processor == null) {
      throw new NullPointerException("No " + PixieProcessor.class.getSimpleName() + " registered for name=" + name + " or type=" + type.getName());
    }

    return processor;
  }

  /**
   * Applies {@link #isProcessorTypeAssignableToType(PixieProcessor, Class)} to all values of
   * {@link PixieProcessor#getCompatibleTypes()} for the given {@link PixieProcessor}
   * 
   * @param processor
   * @param typeIn
   * @return If any results of {@link #isProcessorTypeAssignableToType(PixieProcessor, Class)} were
   *         true
   */
  protected boolean processorContainsAnApplicableType(PixieProcessor processor, Class<?> typeIn) {
    for (Class<?> processorType : processor.getCompatibleTypes()) {
      if (processorType.isAssignableFrom(typeIn) || typeIn.isAssignableFrom(processorType)) {
        return true;
      }
    }
    return false;
  }

}
