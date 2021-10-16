package genelectrovise.magiksmostevile.network.pixiecourier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import genelectrovise.magiksmostevile.guice.Guicer;

public class MessageTest {

  final Injector injector = Guicer.createInjector();
  final String key_value = "{\"key\":\"value\"}";

  @Inject
  @Named("Message_Test") Message<JsonObject> message;

  @BeforeEach
  void beforeEach() {
    injector.injectMembers(this);
  }

  @Test
  void ifProtocolIsGood_thenReturnTrue() {
    assertEquals(true, Message.isProtocolGoodVersion(Message.PROTOCOL_VERSION));
  }

  @Test
  void ifProtocolIsBad_thenReturnFalse() {
    assertEquals(false, Message.isProtocolGoodVersion(Message.PROTOCOL_VERSION + "_"));
  }

  @Test
  void whenGivenStringKeyAndValue_thenElementToStringEqualsInput() {
    JsonElement elem = message.parseToJsonObject(key_value);
    assertEquals(key_value, elem.toString());
  }

}
