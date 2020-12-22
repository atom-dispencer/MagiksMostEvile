package genelectrovise.magiksmostevile.common.main.support;

import java.util.function.Supplier;
import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.main.support.EvileItemTags;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum EnumEvileArmorMaterial implements IArmorMaterial {
  // Amethyst
  AMETHYST(MagiksMostEvile.MODID + ":" + "amethyst", 25, new int[] {3, 5, 4, 2}, 50,
      SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F, () -> {
        return Ingredient.fromTag(EvileItemTags.AMETHYST);
      }, 0),
  // Powered amethyst
  POWERED_AMETHYST(MagiksMostEvile.MODID + ":" + "powered_amethyst", 33, new int[] {4, 6, 5, 3},
      100, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, () -> {
        return Ingredient.fromTag(EvileItemTags.POWERED_AMETHYST);
      }, 0),
  // Over powered amethyst
  OVER_POWERED_AMETHYST(MagiksMostEvile.MODID + ":" + "over_powered_amethyst", 40,
      new int[] {5, 7, 6, 4}, 150, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F, () -> {
        return Ingredient.fromTag(EvileItemTags.OVER_POWERED_AMETHYST);
      }, 0);

  private static final int[] MAX_DAMAGE_ARRAY = new int[] {13, 15, 16, 11};
  private final String name;
  private final int maxDamageFactor;
  private final int[] damageReductionAmountArray;
  private final int enchantability;
  private final SoundEvent soundEvent;
  private final float toughness;
  private final LazyValue<Ingredient> repairMaterial;
  private final int knockbackResistance;

  private EnumEvileArmorMaterial(String nameIn, int maxDamageFactorIn,
      int[] damageReductionAmountsIn, int enchantabilityIn, SoundEvent equipSoundIn,
      float p_i48533_8_, Supplier<Ingredient> repairMaterialSupplier, int knockbackResistance) {
    this.name = nameIn;
    this.maxDamageFactor = maxDamageFactorIn;
    this.damageReductionAmountArray = damageReductionAmountsIn;
    this.enchantability = enchantabilityIn;
    this.soundEvent = equipSoundIn;
    this.toughness = p_i48533_8_;
    this.repairMaterial = new LazyValue<>(repairMaterialSupplier);
    this.knockbackResistance = knockbackResistance;
  }

  public int getDurability(EquipmentSlotType slotIn) {
    return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
  }

  public int getDamageReductionAmount(EquipmentSlotType slotIn) {
    return this.damageReductionAmountArray[slotIn.getIndex()];
  }

  public int getEnchantability() {
    return this.enchantability;
  }

  public SoundEvent getSoundEvent() {
    return this.soundEvent;
  }

  public Ingredient getRepairMaterial() {
    return this.repairMaterial.getValue();
  }

  @OnlyIn(Dist.CLIENT)
  public String getName() {
    return this.name;
  }

  public float getToughness() {
    return this.toughness;
  }

  @Override
  public float getKnockbackResistance() {
    return this.knockbackResistance;
  }

}
