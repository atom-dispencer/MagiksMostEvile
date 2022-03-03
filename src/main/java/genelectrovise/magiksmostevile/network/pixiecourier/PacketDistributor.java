package genelectrovise.magiksmostevile.network.pixiecourier;

import com.google.gson.Gson;
import genelectrovise.magiksmostevile.gson.GsonConfigurator;
import net.minecraftforge.fml.network.NetworkEvent.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PacketDistributor {

    public static final String PIXIE_PACKET_COULD_NOT_BE_PROCESSED_AS_NO_NULL_PROCESSOR_WAS_FOUND = "PixiePacket could not be processed as no (null) processor was found.";
    public static final String PIXIE_PACKET_COULD_NOT_BE_PROCESSED_AS_THE_PACKET_WAS_NULL = "PixiePacket could not be processed as the packet was null.";
    public static final String PIXIE_PACKET_COULD_NOT_BE_PROCESSED_AS_THE_CONTEXT_WAS_NULL = "PixiePacket could not be processed as the context was null.";

    public static final Logger LOGGER = LogManager.getLogger(PacketDistributor.class);

    public PacketDistributor() {
    }

    /**
     * Uses {@link PixieProcessor#process(PixiePacket, Context, Gson)}, with added validity checks.
     *
     * @param packet
     * @param context
     * @throws CourierException
     */
    public void processPacketWithChecks(PixiePacket packet, Context context, PixieProcessor processor) throws CourierException {

        // Null check packet
        if (packet == null)
            throw new CourierException(PIXIE_PACKET_COULD_NOT_BE_PROCESSED_AS_THE_PACKET_WAS_NULL);

        // Null check packet
        if (context == null)
            throw new CourierException(PIXIE_PACKET_COULD_NOT_BE_PROCESSED_AS_THE_PACKET_WAS_NULL);

        // Null check processor
        if (processor == null) {
            throw new CourierException(PIXIE_PACKET_COULD_NOT_BE_PROCESSED_AS_NO_NULL_PROCESSOR_WAS_FOUND);
        }

        // Process
        processor.process(packet, context, GsonConfigurator.newConfiguredInstance());

    }

    public PixieProcessor findProcessor(PixiePacket packet, PixieProcessor.Registry registry) throws CourierException {
        PixieProcessor processor = registry.get(packet.getType());
        return processor;
    }

}
