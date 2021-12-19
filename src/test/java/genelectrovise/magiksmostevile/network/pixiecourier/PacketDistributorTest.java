package genelectrovise.magiksmostevile.network.pixiecourier;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import net.minecraftforge.fml.network.NetworkEvent;

public class PacketDistributorTest {

  @Mock PixiePacket packet;

  @Mock NetworkEvent.Context context;

  @InjectMocks PacketDistributor distributor;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
  }

  /*
   * @Test void testForwardPacket() throws CourierException assertDoesNotThrow(() -> {
   * distributor.forwardPacketToProcessor(packet, context); }); }
   */

}
