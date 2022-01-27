package genelectrovise.magiksmostevile.network.pixiecourier;

import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

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

        when(ctx.getDirection()).thenReturn(direction);
        packet.when(() -> CourierHandshakePacket.handleMessage(any(), any())).thenCallRealMethod();

        CourierHandshakePacket.handleMessage(message, () -> ctx);

        // No need to doNothing as all methods doNothing by default on mocks
        // Verify - this copies the logic in CourierHandshakePacket.handleMessage
        LogicalSide side = direction.getReceptionSide();
        if (side == LogicalSide.CLIENT) {
            packet.verify(() -> CourierHandshakePacket.handleForClient(any(), any()), times(1));
        } else if (side == LogicalSide.SERVER) {
            packet.verify(() -> CourierHandshakePacket.handleForServer(any(), any()), times(1));
        } else {
            packet.verify(() -> CourierHandshakePacket.handleNullDirection(any(), any()), times(1));
        }
    }
}