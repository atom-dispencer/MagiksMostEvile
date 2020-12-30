package genelectrovise.magiksmostevile.common.core.registry.orbital;

import java.util.Set;
import javax.annotation.Nullable;
import org.reflections.Configuration;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

public class OrbitalRegistryGenerator {

  private boolean initialised;
  private Reflections reflections;

  public static final ConfigurationBuilder REFLECTIONS_CONFIGURATION =
      new ConfigurationBuilder().forPackages("genelectrovise");

  public OrbitalRegistryGenerator(@Nullable Configuration configuration) {
    this.setInitialised(false);
    this.reflections =
        new Reflections(configuration == null ? REFLECTIONS_CONFIGURATION : configuration);
  }

  public void collectOrbitalRegistries() {

    try {

      Set<Class<? extends IOrbitalRegistry>> orbitals =
          reflections.getSubTypesOf(IOrbitalRegistry.class);

      for (Class<? extends IOrbitalRegistry> clazz : orbitals) {
        clazz.getMethod("initialise").invoke(clazz);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  // Get and set

  public boolean isInitialised() {
    return initialised;
  }

  public void setInitialised(boolean initialised) {
    this.initialised = initialised;
  }

  public Reflections getReflections() {
    return reflections;
  }

  public void setReflections(Reflections reflections) {
    this.reflections = reflections;
  }
}
