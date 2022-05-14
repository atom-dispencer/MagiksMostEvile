package genelectrovise.magiksmostevile.registry.orbital;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface OrbitalRegistry {
    String name();
    int priority();
    String registryField();

    @OrbitalRegistry(name="mock", priority = 9999, registryField = "none")
    interface Mock {

    }
}

