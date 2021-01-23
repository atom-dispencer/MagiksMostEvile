package genelectrovise.magiksmostevile.item.glowing;

import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;

public class GlowingHoe extends HoeItem {

  public GlowingHoe(IItemTier tier, int maxDamage, float attackSpeedIn, Properties builder) {
    super(tier, maxDamage, attackSpeedIn, builder);
  }

  @Override
  public boolean hasEffect(ItemStack stack) {
    return true;
  }

}
