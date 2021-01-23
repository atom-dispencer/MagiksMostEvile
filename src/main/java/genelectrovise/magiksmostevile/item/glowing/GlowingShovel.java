package genelectrovise.magiksmostevile.item.glowing;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;

public class GlowingShovel extends ShovelItem {

  public GlowingShovel(IItemTier tier, float attackDamageIn, float attackSpeedIn,
      Properties builder) {
    super(tier, attackDamageIn, attackSpeedIn, builder);
  }

  @Override
  public boolean hasEffect(ItemStack stack) {
    return true;
  }

}
