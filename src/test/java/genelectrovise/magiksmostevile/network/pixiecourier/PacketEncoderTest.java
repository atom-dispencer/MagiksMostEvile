package genelectrovise.magiksmostevile.network.pixiecourier;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import genelectrovise.magiksmostevile.item.pixiecourier.CourierException;
import genelectrovise.magiksmostevile.item.pixiecourier.Flags;
import genelectrovise.magiksmostevile.item.pixiecourier.PacketEncoder;
import genelectrovise.magiksmostevile.item.pixiecourier.PixiePacket;
import net.minecraft.network.PacketBuffer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PacketEncoderTest {

    private final String SAMPLE_JSON = "{\"type\":\"java.lang.String\",\"flags\":{\"flags\":[\"flag1\",\"flag2\"]},\"content\":{\"key\":\"value\"}}";

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

    @Test
    void testDecodePacket() throws CourierException {
        PacketBuffer packetBuffer = Mockito.mock(PacketBuffer.class);

        when(packetBuffer.readUtf()).thenReturn(SAMPLE_JSON);
        when(packetBuffer.isReadable()).thenReturn(true);

        PixiePacket pixiePacket = PacketEncoder.decode(packetBuffer);
        verify(packetBuffer, times(1)).readUtf();

        assertEquals(String.class, pixiePacket.getType());
        assertEquals(new Flags(new String[]{"flag1", "flag2"}), pixiePacket.getFlags());
        JsonObject object = new JsonObject();
        object.addProperty("key", "value");
        assertEquals(object, pixiePacket.getContent());
    }

    @Test
    void testDecodePacket_throwsCourier() throws CourierException {

        CourierException exception = assertThrows(CourierException.class, () -> {
            PacketBuffer packetBuffer = Mockito.mock(PacketBuffer.class);

            when(packetBuffer.readUtf()).thenReturn(SAMPLE_JSON);
            when(packetBuffer.isReadable()).thenReturn(false);

            PixiePacket pixiePacket = PacketEncoder.decode(packetBuffer);
            verify(packetBuffer, times(1)).readUtf();

            assertEquals(String.class, pixiePacket.getType());
            assertEquals(new Flags(new String[]{"flag1", "flag2"}), pixiePacket.getFlags());
            assertEquals(new Gson().toJsonTree("{\"key\":\"value\"}").getAsJsonObject(), pixiePacket.getContent());
        });

        String expectedMessage = PacketEncoder.PACKET_BUFFER_IS_NOT_READABLE_FOR_DECODING_PIXIE_PACKET;
        Assertions.assertTrue(exception.getMessage().contains(expectedMessage));
    }

}
