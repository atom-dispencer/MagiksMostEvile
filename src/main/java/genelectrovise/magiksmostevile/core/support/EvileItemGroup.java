package genelectrovise.magiksmostevile.core.support;

import genelectrovise.magiksmostevile.registry.orbital.registries.ItemOrbitalRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class EvileItemGroup {
  public static final ItemGroup MAGIKS_MOST_EVILE = new ItemGroup("itemgroup_evile") {
    @Override
    public ItemStack createIcon() {
      return new ItemStack(ItemOrbitalRegistry.AMETHYST.get());
    }
  };
}
