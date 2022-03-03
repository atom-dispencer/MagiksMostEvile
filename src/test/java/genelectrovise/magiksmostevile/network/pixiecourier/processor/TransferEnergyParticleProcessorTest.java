package genelectrovise.magiksmostevile.network.pixiecourier.processor;

import com.google.gson.Gson;
import genelectrovise.magiksmostevile.item.pixiecourier.PixiePacket;
import genelectrovise.magiksmostevile.item.pixiecourier.processor.TransferEnergyParticleProcessor;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TransferEnergyParticleProcessorTest {

    @Mock
    TransferEnergyParticleProcessor processor;
    @Mock
    PixiePacket packet;
    @Mock
    Context context;
    Gson gson;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testWhenToClient_workEnqueued() {
        when(context.getDirection()).thenReturn(NetworkDirection.PLAY_TO_CLIENT);
        doNothing().when(processor).enqueueParticleSpawn(any(), any(), any());
        doCallRealMethod().when(processor).process(any(), any(), any());

        processor.process(packet, context, gson);
        verify(processor, times(1)).enqueueParticleSpawn(any(), any(), any());
    }

    @Test
    void testWhenToServer_noWorkEnqueued() {
        when(context.getDirection()).thenReturn(NetworkDirection.PLAY_TO_SERVER);
        doNothing().when(processor).enqueueParticleSpawn(any(), any(), any());
        doCallRealMethod().when(processor).process(any(), any(), any());

        processor.process(packet, context, gson);
        verify(processor, never()).enqueueParticleSpawn(any(), any(), any());
    }

}
