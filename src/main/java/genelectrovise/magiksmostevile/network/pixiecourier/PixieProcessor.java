package genelectrovise.magiksmostevile.network.pixiecourier;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import genelectrovise.magiksmostevile.network.pixiecourier.packet.TransferEnergyParticlePacket;
import genelectrovise.magiksmostevile.network.pixiecourier.processor.TransferEnergyParticleProcessor;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Map;

public interface PixieProcessor {

    PixieProcessor DO_NOTHING = (packet, context, gson) -> {
    };

    void process(PixiePacket packet, NetworkEvent.Context context, Gson gson);

    /**
     * @author adam_
     */
    class Registry {

        public static final String CANNOT_REGISTER_PROCESSOR_AS_TYPE_NULL = "Cannot register processor as type null";
        public static final String CANNOT_REGISTER_PROCESSOR_AS_PROCESSOR_NULL = "Cannot register processor as processor null";
        public static final String NO_PROCESSOR_FOR_TYPE = "No processor for type";
        public static final String CANNOT_GET_PROCESSOR_AS_TYPE_NULL = "Cannot get processor as type null";


        // Static
        protected static final Registry INSTANCE = new Registry();

        // Instance
        public Map<Class<?>, PixieProcessor> processors = Maps.newHashMap();

        private Registry() {
        }

        public static Registry getInstance() {
            return INSTANCE;
        }

        /**
         * Associates the given type and processor in the {@link #processors} {@link Map}.
         *
         * @param type      The class of the packet to register
         * @param processor A processor to process the given type of packet
         * @throws CourierException If: type==null || processor==null
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
         * @param type The type of packet for which to get a processor instance for
         * @return The instance of PixieProcessor associated with the given type
         * @throws CourierException If type==null || processor==null
         */
        public PixieProcessor get(Class<?> type) throws CourierException {

            // Null check type
            if (type == null) {
                throw new CourierException(CANNOT_GET_PROCESSOR_AS_TYPE_NULL);
            }

            PixieProcessor processor = n_get(type);

            // Null check processor
            if (processor == null) {
                throw new CourierException(NO_PROCESSOR_FOR_TYPE + ": " + type.getName());
            }

            return processor;
        }

        public PixieProcessor n_get(Class<?> type) {
            return getProcessors().get(type);
        }

        public Map<Class<?>, PixieProcessor> getProcessors() {
            return processors;
        }

        /**
         * @param event Injected FMLCommonSetupEvent instance
         * @throws CourierException If fails, should crash the game because not registering some packets
         *                          could be nasty later on
         */
        @SubscribeEvent
        void registerProcessors(FMLCommonSetupEvent event) throws CourierException {
            register(TransferEnergyParticlePacket.class, new TransferEnergyParticleProcessor());
        }
    }
}
