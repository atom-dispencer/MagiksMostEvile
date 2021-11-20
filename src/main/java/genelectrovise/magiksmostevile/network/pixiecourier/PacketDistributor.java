package genelectrovise.magiksmostevile.network.pixiecourier;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.common.collect.Maps;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class PacketDistributor {

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

  public void forwardPacketToProcessor(PixiePacket packet, Context context) {

    // Indicator to tidy up the code a bit. Any exceptions will trigger this to be set false
    // So any errors, and the finally block will print debug information
    // Info is the same for any exception, so no need to duplicate the code!
    boolean allIsWell = true;

    try {

      // Null check packet
      if (packet == null)
        throw new IllegalArgumentException("PixiePacket could not be processed as the packet was null!");

      // Find the processor for the packet type
      PixieProcessor processor = get(packet.getType(), packet.getFlags().get(Flags.F_PROCESSOR));
      if (processor == null) {
        throw new IllegalArgumentException("PixiePacket could not be processed as the processor was null!");
      }

      // Process
      processor.process(packet, context);

      // Error during pre-processing
    } catch (IllegalArgumentException a) {
      allIsWell = false;
      LOGGER.error("Unable to process packet! (pre-processing error, debug follows)");
      a.printStackTrace();

      // Any other error (don't want to crash the server so catches any exceptions)
    } catch (Exception e) {
      allIsWell = false;
      LOGGER.error("Unable to process packet! (see debug for further messages)");
      e.printStackTrace();

      // Always executed.
      // Print debug info if not everything is well :(
    } finally {
      if (!allIsWell) {
        LOGGER.debug("Packet error debug: ");
        LOGGER.debug("packet=" + packet.toString());
        LOGGER.debug("content=" + packet.getContent());
        LOGGER.debug("type=" + (packet.getType() != null ? packet.getType().getName() : packet.getType()));
        LOGGER.debug("flags=" + packet.getFlags());
      }
    }
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
