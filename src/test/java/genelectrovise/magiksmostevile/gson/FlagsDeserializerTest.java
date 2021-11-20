package genelectrovise.magiksmostevile.gson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import genelectrovise.magiksmostevile.network.pixiecourier.Flags;

public class FlagsDeserializerTest {

  final FlagsSerializer serializer = new FlagsSerializer();

  final Gson gson = GsonConfigurator.newConfiguredInstance();
  final Flags inputObject = new Flags(new String[] {"flag1", "flag2"});
  final String idealString = "{\"flags\":[\"flag1\",\"flag2\"]}";

  @Mock JsonSerializationContext context;

  public FlagsDeserializerTest() {}

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testSerialize() {
    JsonObject ideal = gson.fromJson(idealString, JsonObject.class);
    JsonObject actual = (JsonObject) serializer.serialize(inputObject, Flags.class, context);
    assertEquals(ideal, actual);
  }

}
