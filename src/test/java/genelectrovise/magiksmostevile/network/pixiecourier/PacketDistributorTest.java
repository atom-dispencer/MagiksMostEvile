package genelectrovise.magiksmostevile.network.pixiecourier;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import net.minecraftforge.fml.network.NetworkEvent;

public class PacketDistributorTest {

  @Mock PixiePacket packet;

  @Mock NetworkEvent.Context context;
  
  @Mock PixieProcessor processor;
  
  @Mock PixieProcessor.Registry registry;

  @InjectMocks PacketDistributor distributor;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testForwardPacketToProcessor() throws CourierException {
    doNothing().when(processor).process(any(), any());
    distributor.processPacketWithChecks(packet, context, processor);
  }

  @Test
  void testForwardPacketToProcessor_throwsCourier_whenNoPacket() throws CourierException {
    assertThrows(CourierException.class, () -> {
      distributor.processPacketWithChecks(null, context, processor);
    });
  }

  @Test
  void testForwardPacketToProcessor_throwsCourier_whenNoContext() throws CourierException {
    assertThrows(CourierException.class, () -> {
      distributor.processPacketWithChecks(packet, null, processor);
    });
  }

  @Test
  void testForwardPacketToProcessor_throwsCourier_whenNoProcessor() throws CourierException {
    assertThrows(CourierException.class, () -> {
      distributor.processPacketWithChecks(packet, context, null);
    });
  }
  
  @Test
  void testFindProcessor() throws CourierException {
    when(registry.get(any())).thenReturn(processor);
    distributor.findProcessor(packet, registry);
  }

}
