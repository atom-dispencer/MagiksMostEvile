package genelectrovise.magiksmostevile.guice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import genelectrovise.magiksmostevile.network.pixiecourier.Message;

public class GuiceModule extends AbstractModule {

  public GuiceModule() {}

  @Override
  protected void configure() {
    super.configure();

    // Configuration

    bind(Gson.class).toProvider(() -> new GsonBuilder().create());
    bind(JsonParser.class).toProvider(() -> new JsonParser());
    bind(Message.class).annotatedWith(Names.named("Message_Test")).toProvider(() -> new Message<JsonObject>(Guicer.get(JsonParser.class).parse("{\"key\":\"value\"}")));

  }

}
