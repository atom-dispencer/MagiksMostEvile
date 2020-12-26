package genelectrovise.magiksmostevile.common.item.glowing;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GlowingItem extends Item {

  public GlowingItem(Properties properties) {
    super(properties);
  }

  @Override
  public boolean hasEffect(ItemStack stack) {
    return true;
  }

}
