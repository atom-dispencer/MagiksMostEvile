package genelectrovise.magiksmostevile.network.pixiecourier.handshake;

import genelectrovise.magiksmostevile.network.pixiecourier.PixieCourier;
import lombok.Data;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

@Data
public class CourierHandshakePacket {

    protected String clientRegistryHash = "<H>";
    protected String serverResponse = "<R>";

    private CourierHandshakePacket() {
    }

    public static CourierHandshakePacket getNewExchangePacket() {
        return new CourierHandshakePacket();
    }

    public void encode(PacketBuffer buffer) {
        //TODO Encode CourierHandshakePacket
    }

    public static CourierHandshakePacket decode(PacketBuffer buffer) {
        return new CourierHandshakePacket();
    }

    /**
     * Takes the incoming message (regardless of logical side), gets this side's response to it, and forwards the message back.
     *
     * @param message     The incoming {@link CourierHandshakePacket}
     * @param ctxSupplier The {@link NetworkEvent.Context} of the incoming packet.
     */
    public static void handleMessage(CourierHandshakePacket message, Supplier<NetworkEvent.Context> ctxSupplier) {
        ServerPlayerEntity sender = ctxSupplier.get().getSender();
        OngoingHandshake ongoingHandshake = PixieCourier.getInstance().getHandshakeManager().getOngoingHandshake(sender);
        CourierHandshakePacket response = PixieCourier.getInstance().getHandshakeManager().getResponse(message, ongoingHandshake, ctxSupplier.get());
        PixieCourier.getInstance().channel.send(net.minecraftforge.fml.network.PacketDistributor.PLAYER.with(() -> sender), response);
    }
}