package genelectrovise.magiksmostevile.common.core.registry.orbital.registries;

import genelectrovise.magiksmostevile.common.core.registry.orbital.IOrbitalRegistry;
import genelectrovise.magiksmostevile.common.core.support.EnumEvileArmorMaterial;
import genelectrovise.magiksmostevile.common.core.support.EvileItemGroup;
import genelectrovise.magiksmostevile.common.item.equipment.armor.EvileArmorBases.AmethystArmorBase;
import genelectrovise.magiksmostevile.common.item.equipment.armor.EvileArmorBases.OverPoweredAmethystArmorBase;
import genelectrovise.magiksmostevile.common.item.equipment.armor.EvileArmorBases.PoweredAmethystArmorBase;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ArmorOrbitalRegistry implements IOrbitalRegistry {

  // =========ARMOR======================================================================================================================
  public static final RegistryObject<Item> AMETHYST_HELMET =
      ItemOrbitalRegistry.ITEMS.register("amethyst_helmet", () -> new AmethystArmorBase(EnumEvileArmorMaterial.AMETHYST,
          EquipmentSlotType.HEAD, new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> AMETHYST_CHESTPLATE = ItemOrbitalRegistry.ITEMS
      .register("amethyst_chestplate", () -> new AmethystArmorBase(EnumEvileArmorMaterial.AMETHYST,
          EquipmentSlotType.CHEST, new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> AMETHYST_LEGGINGS = ItemOrbitalRegistry.ITEMS.register("amethyst_leggings",
      () -> new AmethystArmorBase(EnumEvileArmorMaterial.AMETHYST, EquipmentSlotType.LEGS,
          new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> AMETHYST_BOOTS =
      ItemOrbitalRegistry.ITEMS.register("amethyst_boots", () -> new AmethystArmorBase(EnumEvileArmorMaterial.AMETHYST,
          EquipmentSlotType.FEET, new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  //
  public static final RegistryObject<Item> POWERED_AMETHYST_HELMET =
      ItemOrbitalRegistry.ITEMS.register("powered_amethyst_helmet",
          () -> new PoweredAmethystArmorBase(EnumEvileArmorMaterial.POWERED_AMETHYST,
              EquipmentSlotType.HEAD, new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> POWERED_AMETHYST_CHESTPLATE =
      ItemOrbitalRegistry.ITEMS.register("powered_amethyst_chestplate",
          () -> new PoweredAmethystArmorBase(EnumEvileArmorMaterial.POWERED_AMETHYST,
              EquipmentSlotType.CHEST,
              new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> POWERED_AMETHYST_LEGGINGS =
      ItemOrbitalRegistry.ITEMS.register("powered_amethyst_leggings",
          () -> new PoweredAmethystArmorBase(EnumEvileArmorMaterial.POWERED_AMETHYST,
              EquipmentSlotType.LEGS, new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> POWERED_AMETHYST_BOOTS =
      ItemOrbitalRegistry.ITEMS.register("powered_amethyst_boots",
          () -> new PoweredAmethystArmorBase(EnumEvileArmorMaterial.POWERED_AMETHYST,
              EquipmentSlotType.FEET, new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  //
  public static final RegistryObject<Item> OVER_POWERED_AMETHYST_HELMET =
      ItemOrbitalRegistry.ITEMS.register("over_powered_amethyst_helmet",
          () -> new OverPoweredAmethystArmorBase(EnumEvileArmorMaterial.OVER_POWERED_AMETHYST,
              EquipmentSlotType.HEAD, new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> OVER_POWERED_AMETHYST_CHESTPLATE =
      ItemOrbitalRegistry.ITEMS.register("over_powered_amethyst_chestplate",
          () -> new OverPoweredAmethystArmorBase(EnumEvileArmorMaterial.OVER_POWERED_AMETHYST,
              EquipmentSlotType.CHEST,
              new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> OVER_POWERED_AMETHYST_LEGGINGS =
      ItemOrbitalRegistry.ITEMS.register("over_powered_amethyst_leggings",
          () -> new OverPoweredAmethystArmorBase(EnumEvileArmorMaterial.OVER_POWERED_AMETHYST,
              EquipmentSlotType.LEGS, new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> OVER_POWERED_AMETHYST_BOOTS =
      ItemOrbitalRegistry.ITEMS.register("over_powered_amethyst_boots",
          () -> new OverPoweredAmethystArmorBase(EnumEvileArmorMaterial.OVER_POWERED_AMETHYST,
              EquipmentSlotType.FEET, new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));

  @Override
  public int priority() {
    return 7;
  }

  @Override
  public void initialise() {

  }

  @Override
  public String name() {
    return "armor";
  }

}
