package genelectrovise.magiksmostevile.network.pixiecourier.handshake;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.NetworkEvent;

public interface HandshakeManager {

    CourierHandshakePacket getResponse(CourierHandshakePacket packet, OngoingHandshake ongoingHandshake, NetworkEvent.Context context);

    OngoingHandshake getOngoingHandshake(ServerPlayerEntity sender);
}
