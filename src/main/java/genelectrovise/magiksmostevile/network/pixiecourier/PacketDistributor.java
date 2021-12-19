package genelectrovise.magiksmostevile.network.pixiecourier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class PacketDistributor {

  public static final String PIXIE_PACKET_COULD_NOT_BE_PROCESSED_AS_NO_NULL_PROCESSOR_WAS_FOUND = "PixiePacket could not be processed as no (null) processor was found.";
  public static final String PIXIE_PACKET_COULD_NOT_BE_PROCESSED_AS_THE_PACKET_WAS_NULL = "PixiePacket could not be processed as the packet was null.";

  public static final Logger LOGGER = LogManager.getLogger(PacketDistributor.class);

  public PacketDistributor() {}

  /**
   * Uses {@link #findProcessor(PixiePacket)}, then runs
   * {@link PixieProcessor#process(PixiePacket, Context)} (with added validity checks).
   * 
   * @param packet
   * @param context
   * @throws CourierException
   */
  public void forwardPacketToProcessor(PixiePacket packet, Context context) throws CourierException {

    // Null check packet
    if (packet == null)
      throw new CourierException(PIXIE_PACKET_COULD_NOT_BE_PROCESSED_AS_THE_PACKET_WAS_NULL);

    // Find the processor for the packet type
    PixieProcessor processor = findProcessor(packet);
    if (processor == null) {
      throw new CourierException(PIXIE_PACKET_COULD_NOT_BE_PROCESSED_AS_NO_NULL_PROCESSOR_WAS_FOUND);
    }

    // Process
    processor.process(packet, context);

  }

  protected PixieProcessor findProcessor(PixiePacket packet) throws CourierException {
    PixieProcessor processor = PixieProcessor.Registry.INSTANCE.get(packet.getType());
    return processor;
  }

}
