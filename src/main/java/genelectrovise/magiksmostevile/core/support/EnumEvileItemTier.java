/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise
 *
 * This file is part of Magiks Most Evile. Magiks Most Evile is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile.
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
package genelectrovise.magiksmostevile.core.support;

import java.util.function.Supplier;
import genelectrovise.magiksmostevile.registry.orbital.registries.ItemOrbitalRegistry;
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
