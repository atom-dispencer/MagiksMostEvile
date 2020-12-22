package genelectrovise.magiksmostevile.common.item.glowing;

import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;

public class GlowingHoe extends HoeItem {

	public GlowingHoe(IItemTier tier, float attackSpeedIn, Properties builder) {
		super(tier, attackSpeedIn, builder);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

}
