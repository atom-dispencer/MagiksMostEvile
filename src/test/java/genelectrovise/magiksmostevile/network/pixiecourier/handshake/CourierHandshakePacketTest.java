package genelectrovise.magiksmostevile.network.pixiecourier.handshake;

import genelectrovise.magiksmostevile.item.pixiecourier.handshake.CourierHandshakePacket;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class CourierHandshakePacketTest {

    CourierHandshakePacket message = Mockito.mock(CourierHandshakePacket.class);
    NetworkEvent.Context ctx = Mockito.mock(NetworkEvent.Context.class);

    static MockedStatic<CourierHandshakePacket> packet;

    @BeforeAll
    static void beforeAll() {
        packet = Mockito.mockStatic(CourierHandshakePacket.class);
    }

    @ParameterizedTest
    @EnumSource(NetworkDirection.class)
    void testProcessMessage(NetworkDirection direction) {

    }
}