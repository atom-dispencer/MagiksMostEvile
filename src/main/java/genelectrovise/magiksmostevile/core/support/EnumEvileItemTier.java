package genelectrovise.magiksmostevile.core.support;

import java.util.function.Supplier;
import genelectrovise.magiksmostevile.core.registry.orbital.registries.ItemOrbitalRegistry;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;;

public enum EnumEvileItemTier implements IItemTier {

  // Tags to allow repairing of items in an anvil
  // Amethyst
  AMETHYST(2, 512, 9.0F, 1.5F, 50, () -> {
    return Ingredient.fromItems(ItemOrbitalRegistry.AMETHYST.get());
  }),
  // Powered amethyst
  POWERED_AMETHYST(3, 512, 15.0F, 3.0F, 200, () -> {
    return Ingredient.fromItems(ItemOrbitalRegistry.POWERED_AMETHYST.get());
  });

  private final int harvestLevel;
  private final int maxUses;
  private final float efficiency;
  private final float attackDamage;
  private final int enchantability;
  private final LazyValue<Ingredient> repairMaterial;

  private EnumEvileItemTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn,
      float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {
    this.harvestLevel = harvestLevelIn;
    this.maxUses = maxUsesIn;
    this.efficiency = efficiencyIn;
    this.attackDamage = attackDamageIn;
    this.enchantability = enchantabilityIn;
    this.repairMaterial = new LazyValue<>(repairMaterialIn);
  }

  public int getMaxUses() {
    return this.maxUses;
  }

  public float getEfficiency() {
    return this.efficiency;
  }

  public float getAttackDamage() {
    return this.attackDamage;
  }

  public int getHarvestLevel() {
    return this.harvestLevel;
  }

  public int getEnchantability() {
    return this.enchantability;
  }

  public Ingredient getRepairMaterial() {
    return this.repairMaterial.getValue();
  }

}
