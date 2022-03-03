package genelectrovise.magiksmostevile.gson;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import genelectrovise.magiksmostevile.item.pixiecourier.Flags;
import genelectrovise.magiksmostevile.item.pixiecourier.PixiePacket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PixiePacketJsonDeserializerTest {

    final Gson gson = new Gson();
    final PixiePacket idealPacket = new PixiePacket(String.class, gson.fromJson("{\"key\":\"value\"}", JsonObject.class), new Flags(new String[]{"flag1", "flag2"}));
    final String idealString = "{\"type\":\"java.lang.String\",\"flags\":{\"flags\":[\"flag1\",\"flag2\"]},\"content\":{\"key\":\"value\"}}";
    PixiePacketJsonDeserializer deserializer = new PixiePacketJsonDeserializer();
    @Mock
    JsonDeserializationContext context;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeserialize() {
        JsonObject object = gson.fromJson(idealString, JsonObject.class);
        PixiePacket actual = deserializer.deserialize(object, PixiePacket.class, context);
        assertEquals(idealPacket, actual);
    }
}
