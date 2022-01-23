package genelectrovise.magiksmostevile.guice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.inject.AbstractModule;

public class GuiceModule extends AbstractModule {

    public GuiceModule() {
    }

    @Override
    protected void configure() {
        super.configure();

        // Configuration

        bind(Gson.class).toProvider(() -> new GsonBuilder().create());
        bind(JsonParser.class).toProvider(() -> new JsonParser());

    }

}
