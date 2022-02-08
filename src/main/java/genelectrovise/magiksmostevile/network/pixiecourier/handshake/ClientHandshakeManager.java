package genelectrovise.magiksmostevile.network.pixiecourier.handshake;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.NetworkEvent;

public class ClientHandshakeManager implements HandshakeManager {
    protected OngoingHandshake ongoingHandshake;

    @Override
    public CourierHandshakePacket getResponse(CourierHandshakePacket packet, OngoingHandshake ongoingHandshake, NetworkEvent.Context context) {
        //TODO Get client response to handshake
        return null;
    }

    @Override
    public OngoingHandshake getOngoingHandshake(ServerPlayerEntity sender) {
        return ongoingHandshake;
    }
}
