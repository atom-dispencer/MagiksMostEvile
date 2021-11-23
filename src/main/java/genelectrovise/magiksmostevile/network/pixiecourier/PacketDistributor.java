package genelectrovise.magiksmostevile.network.pixiecourier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class PacketDistributor {

  public static final String PIXIE_PACKET_COULD_NOT_BE_PROCESSED_AS_NO_NULL_PROCESSOR_WAS_FOUND = "PixiePacket could not be processed as no (null) processor was found.";
  public static final String PIXIE_PACKET_COULD_NOT_BE_PROCESSED_AS_THE_PACKET_WAS_NULL = "PixiePacket could not be processed as the packet was null.";

  public static final Logger LOGGER = LogManager.getLogger(PacketDistributor.class);

  public PacketDistributor() {}

  public void forwardPacketToProcessor(PixiePacket packet, Context context) throws CourierException {

    // Null check packet
    if (packet == null)
      throw new CourierException(PIXIE_PACKET_COULD_NOT_BE_PROCESSED_AS_THE_PACKET_WAS_NULL);

    // Find the processor for the packet type
    PixieProcessor processor = PixieProcessor.Registry.INSTANCE.get(packet.getType());
    if (processor == null) {
      throw new CourierException(PIXIE_PACKET_COULD_NOT_BE_PROCESSED_AS_NO_NULL_PROCESSOR_WAS_FOUND);
    }

    // Process
    processor.process(packet, context);

  }

}
