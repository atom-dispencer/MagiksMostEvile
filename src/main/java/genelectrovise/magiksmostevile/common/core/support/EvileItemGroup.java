package genelectrovise.magiksmostevile.common.core.support;

import genelectrovise.magiksmostevile.common.core.registry.EvileDeferredRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class EvileItemGroup {
  public static final ItemGroup ITEMGROUP_EVILE = new ItemGroup("itemgroup_evile") {
    @Override
    public ItemStack createIcon() {
      return new ItemStack(EvileDeferredRegistry.AMETHYST.get());
    }
  };
}
