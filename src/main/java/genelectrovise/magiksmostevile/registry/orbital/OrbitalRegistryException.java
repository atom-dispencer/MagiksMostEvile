package genelectrovise.magiksmostevile.registry.orbital;

public class OrbitalRegistryException extends RuntimeException {
    public OrbitalRegistryException(Exception ex) {
        super(ex);
    }

    public OrbitalRegistryException(String s) {
        super(s);
    }
}
