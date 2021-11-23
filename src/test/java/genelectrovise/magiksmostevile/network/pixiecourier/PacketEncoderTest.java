
package genelectrovise.magiksmostevile.network.pixiecourier;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import com.google.gson.JsonObject;
import net.minecraft.network.PacketBuffer;

public class PacketEncoderTest {

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testEncodePacket() throws CourierException {
    PixiePacket pixiePacket = new PixiePacket(String.class, new JsonObject(), Flags.NO_FLAGS);
    PacketBuffer packetBuffer = mock(PacketBuffer.class); // new PacketBuffer(new EmptyByteBuf(ByteBufAllocator.DEFAULT));

    when(packetBuffer.writeUtf(anyString())).thenReturn(packetBuffer);
    when(packetBuffer.isWritable()).thenReturn(true);

    PacketEncoder.encode(pixiePacket, packetBuffer);
    verify(packetBuffer, times(1)).writeUtf(anyString());
  }

  @Test
  void testEncodePacket_throwsCourier() throws CourierException {

    CourierException exception = assertThrows(CourierException.class, () -> {
      PixiePacket pixiePacket = new PixiePacket(String.class, new JsonObject(), Flags.NO_FLAGS);
      PacketBuffer packetBuffer = mock(PacketBuffer.class); // new PacketBuffer(new EmptyByteBuf(ByteBufAllocator.DEFAULT));

      when(packetBuffer.writeUtf(anyString())).thenReturn(packetBuffer);
      when(packetBuffer.isWritable()).thenReturn(false);

      PacketEncoder.encode(pixiePacket, packetBuffer);
    });

    String expectedMessage = PacketEncoder.PACKET_BUFFER_IS_NOT_WRITABLE_FOR_ENCODING_PIXIE_PACKET;
    Assertions.assertTrue(exception.getMessage().contains(expectedMessage));
  }

}
