package genelectrovise.magiksmostevile.item.pixiecourier;

import com.google.gson.Gson;
import genelectrovise.magiksmostevile.gson.GsonConfigurator;
import net.minecraft.network.PacketBuffer;

public class PacketEncoder {

    public static final String PACKET_BUFFER_IS_NOT_READABLE_FOR_DECODING_PIXIE_PACKET = "PacketBuffer is not readable for decoding PixiePacket";
    public static final String PACKET_BUFFER_IS_NOT_WRITABLE_FOR_ENCODING_PIXIE_PACKET = "PacketBuffer is not writable for encoding PixiePacket";

    public PacketEncoder() {
    }

    public static String encode(PixiePacket packet, PacketBuffer buffer) throws CourierException {

        if (!buffer.isWritable()) {
            throw new CourierException(PACKET_BUFFER_IS_NOT_WRITABLE_FOR_ENCODING_PIXIE_PACKET);
        }

        Gson gson = GsonConfigurator.newConfiguredInstance();
        String json = gson.toJson(packet);
        buffer.writeUtf(json);
        return json;
    }

    public static PixiePacket decode(PacketBuffer buffer) throws CourierException {

        if (!buffer.isReadable()) {
            throw new CourierException(PACKET_BUFFER_IS_NOT_READABLE_FOR_DECODING_PIXIE_PACKET);
        }

        Gson gson = GsonConfigurator.newConfiguredInstance();
        String json = buffer.readUtf();
        PixiePacket packet = gson.fromJson(json, PixiePacket.class);
        return packet;
    }

    public static boolean isValidVersion(String version) {
        return true;
    }

}
