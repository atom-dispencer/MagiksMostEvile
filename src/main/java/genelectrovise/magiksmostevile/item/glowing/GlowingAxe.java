package genelectrovise.magiksmostevile.item.glowing;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;

public class GlowingAxe extends AxeItem {

  public GlowingAxe(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
    super(tier, attackDamageIn, attackSpeedIn, builder);
  }

  @Override
  public boolean hasEffect(ItemStack stack) {
    return true;
  }

}
