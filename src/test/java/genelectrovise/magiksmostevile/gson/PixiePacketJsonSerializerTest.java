package genelectrovise.magiksmostevile.gson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import genelectrovise.magiksmostevile.item.pixiecourier.Flags;
import genelectrovise.magiksmostevile.item.pixiecourier.PixiePacket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PixiePacketJsonSerializerTest {

    final PixiePacketJsonSerializer serializer = new PixiePacketJsonSerializer();

    final Gson gson = GsonConfigurator.newConfiguredInstance();
    final PixiePacket idealPacket = new PixiePacket(String.class, gson.fromJson("{\"key\":\"value\"}", JsonObject.class), new Flags(new String[]{"flag1", "flag2"}));
    final String idealString = "{\"type\":\"java.lang.String\",\"flags\":{\"flags\":[\"flag1\",\"flag2\"]},\"content\":{\"key\":\"value\"}}";

    @Mock
    JsonSerializationContext context;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSerialize() {
        JsonObject idealResult = gson.fromJson(idealString, JsonObject.class);
        JsonObject actualResult = (JsonObject) serializer.serialize(idealPacket, PixiePacket.class, context);
        assertEquals(idealResult, actualResult);
    }
}
