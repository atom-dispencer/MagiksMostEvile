package genelectrovise.magiksmostevile.network.pixiecourier;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CourierHandshakePacket {

    public static CourierHandshakePacket decode(PacketBuffer buffer) {
        return new CourierHandshakePacket();
    }

    public static CourierHandshakePacket getNewExchangePacket() {
        return new CourierHandshakePacket();
    }

    public void encode(PacketBuffer buffer) {

    }

    public static void handleMessage(CourierHandshakePacket message, Supplier<NetworkEvent.Context> ctxSupplier) {

        NetworkEvent.Context ctx = ctxSupplier.get();
        NetworkDirection direction = ctx.getDirection();
        LogicalSide side = direction.getReceptionSide();

        if (side == LogicalSide.CLIENT) {
            handleForClient(message, ctx);
        } else if (side == LogicalSide.SERVER) {
            handleForServer(message, ctx);
        } else {
            handleNullDirection(message, ctx);
        }
    }

    protected static void handleNullDirection(CourierHandshakePacket message, NetworkEvent.Context ctx) {
    }

    protected static void handleForServer(CourierHandshakePacket message, NetworkEvent.Context ctx) {
    }

    protected static void handleForClient(CourierHandshakePacket message, NetworkEvent.Context ctx) {
    }
}