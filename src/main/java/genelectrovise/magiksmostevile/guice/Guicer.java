package genelectrovise.magiksmostevile.guice;

import java.util.List;
import com.google.common.collect.Lists;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Guicer {

  private static List<AbstractModule> modules = Lists.newArrayList(new GuiceModule());
  private static Injector injector;

  public static Injector createInjector() {
    return Guice.createInjector(modules);
  }

  public static Injector getInjector() {
    injector = (injector == null) ? createInjector() : injector;
    return injector;
  }

  public static List<AbstractModule> getModules() { return modules; }

  public static <T> T get(Class<T> clazz) {
    return getInjector().getInstance(clazz);
  }

}
