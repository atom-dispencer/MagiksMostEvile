package genelectrovise.magiksmostevile.common.tileentity.altar;

import java.util.ArrayList;
import java.util.function.Supplier;
import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.core.registry.EvileDeferredRegistry;
import genelectrovise.magiksmostevile.common.ritual.Ritual;
import net.minecraft.util.ResourceLocation;

/**
 * Handles ritual selection in an {@link AltarContainer}
 * 
 * @author GenElectrovise 10 Jun 2020
 */
public class RitualSelector {

  private ResourceLocation location =
      new ResourceLocation(MagiksMostEvile.MODID, "convert_amethyst_ritual");

  /**
   * @param location
   */
  public void setLocation(ResourceLocation location) {
    this.location = location;
  }

  /**
   * @return the location
   */
  public ResourceLocation getLocation() {
    return location != null ? location
        : new ResourceLocation(MagiksMostEvile.MODID, "convert_amethyst_ritual");
  }

  public Supplier<Ritual> getRitualSupplier() {
    return getRitualSupplier(location);
  }

  public Supplier<Ritual> getRitualSupplier(ResourceLocation location) {

    ArrayList<Supplier<Ritual>> ritualSuppliers = new ArrayList<Supplier<Ritual>>();

    EvileDeferredRegistry.RITUALS.getEntries().forEach((ritualSupplier) -> {
      ritualSuppliers.add(ritualSupplier);
    });

    for (Supplier<Ritual> supplier : ritualSuppliers) {
      if (supplier.get().getRegistryName().equals(location)) {
        return supplier;
      }
    }

    MagiksMostEvile.LOGGER.error("No valid ritual of ID " + location + " in ritual registry!");
    MagiksMostEvile.LOGGER.debug("Registry: \n " + EvileDeferredRegistry.RITUALS);
    return null;
  }
}
