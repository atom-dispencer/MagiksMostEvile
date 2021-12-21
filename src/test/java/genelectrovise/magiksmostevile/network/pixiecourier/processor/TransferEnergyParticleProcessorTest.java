package genelectrovise.magiksmostevile.network.pixiecourier.processor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.google.gson.Gson;
import genelectrovise.magiksmostevile.network.pixiecourier.PixiePacket;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class TransferEnergyParticleProcessorTest {

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
  }

  @Mock TransferEnergyParticleProcessor processor;
  @Mock PixiePacket packet;
  @Mock Context context;
  @Mock Gson gson;

  @Test
  void testWhenToClient_workEnqueued() {
    when(context.getDirection()).thenReturn(NetworkDirection.PLAY_TO_CLIENT);
    doNothing().when(context).enqueueWork(any());

    processor.process(packet, context, gson);
    verify(context, times(1)).enqueueWork(any());
  }

  @Test
  void testWhenToServer_noWorkEnqueued() {
    when(context.getDirection()).thenReturn(NetworkDirection.PLAY_TO_SERVER);
    doNothing().when(context).enqueueWork(any());

    processor.process(packet, context, gson);
    verify(context, never()).enqueueWork(any());
  }

}
