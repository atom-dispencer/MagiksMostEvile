package genelectrovise.magiksmostevile.common.core.registry.orbital.registries;

import genelectrovise.magiksmostevile.common.core.registry.orbital.IOrbitalRegistry;
import genelectrovise.magiksmostevile.common.core.support.EnumEvileItemTier;
import genelectrovise.magiksmostevile.common.core.support.EvileItemGroup;
import genelectrovise.magiksmostevile.common.item.glowing.GlowingAxe;
import genelectrovise.magiksmostevile.common.item.glowing.GlowingHoe;
import genelectrovise.magiksmostevile.common.item.glowing.GlowingPickaxe;
import genelectrovise.magiksmostevile.common.item.glowing.GlowingShovel;
import genelectrovise.magiksmostevile.common.item.glowing.GlowingSword;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;

public class ToolOrbitalRegistry implements IOrbitalRegistry {

  // =========TOOLS=======================================================================================================================
  public static final RegistryObject<Item> AMETHYST_SWORD = ItemOrbitalRegistry.ITEMS
      .register("amethyst_sword", () -> new SwordItem(EnumEvileItemTier.AMETHYST, 1, -0.5F,
          new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> AMETHYST_AXE = ItemOrbitalRegistry.ITEMS
      .register("amethyst_axe", () -> new AxeItem(EnumEvileItemTier.AMETHYST, 1, -0.5F,
          new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> AMETHYST_PICKAXE = ItemOrbitalRegistry.ITEMS
      .register("amethyst_pickaxe", () -> new PickaxeItem(EnumEvileItemTier.AMETHYST, 1, -0.5F,
          new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> AMETHYST_SHOVEL = ItemOrbitalRegistry.ITEMS
      .register("amethyst_shovel", () -> new ShovelItem(EnumEvileItemTier.AMETHYST, 1, -0.5F,
          new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> AMETHYST_HOE = ItemOrbitalRegistry.ITEMS
      .register("amethyst_hoe", () -> new HoeItem(EnumEvileItemTier.AMETHYST, 1, -0.5F,
          new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> POWERED_AMETHYST_SWORD = ItemOrbitalRegistry.ITEMS
      .register("powered_amethyst_sword", () -> new GlowingSword(EnumEvileItemTier.POWERED_AMETHYST,
          2, -0.25F, new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> POWERED_AMETHYST_AXE = ItemOrbitalRegistry.ITEMS
      .register("powered_amethyst_axe", () -> new GlowingAxe(EnumEvileItemTier.POWERED_AMETHYST, 2,
          -0.25F, new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> POWERED_AMETHYST_PICKAXE =
      ItemOrbitalRegistry.ITEMS.register("powered_amethyst_pickaxe",
          () -> new GlowingPickaxe(EnumEvileItemTier.POWERED_AMETHYST, 2, -0.25F,
              new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> POWERED_AMETHYST_SHOVEL =
      ItemOrbitalRegistry.ITEMS.register("powered_amethyst_shovel",
          () -> new GlowingShovel(EnumEvileItemTier.POWERED_AMETHYST, 2, -0.25F,
              new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> POWERED_AMETHYST_HOE = ItemOrbitalRegistry.ITEMS
      .register("powered_amethyst_hoe", () -> new GlowingHoe(EnumEvileItemTier.POWERED_AMETHYST, 2,
          -0.25F, new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));

  @Override
  public int priority() {
    return 4;
  }

  @Override
  public void initialise() {

  }

  @Override
  public String name() {
    return "tools";
  }

}
