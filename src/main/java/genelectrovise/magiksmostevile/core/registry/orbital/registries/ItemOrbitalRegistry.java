package genelectrovise.magiksmostevile.core.registry.orbital.registries;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.core.registry.orbital.IOrbitalRegistry;
import genelectrovise.magiksmostevile.core.registry.orbital.OrbitalRegistryGenerator;
import genelectrovise.magiksmostevile.core.support.EvileItemGroup;
import genelectrovise.magiksmostevile.item.glowing.GlowingItem;
import net.minecraft.item.Item;
import net.minecraft.item.WrittenBookItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemOrbitalRegistry implements IOrbitalRegistry {

  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, MagiksMostEvile.MODID);

  // =========ITEMS=======================================================================================================================

  // Amethysts
  public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst",
      () -> new Item(new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> POWERED_AMETHYST = ITEMS.register("powered_amethyst",
      () -> new GlowingItem(new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> OVER_POWERED_AMETHYST =
      ITEMS.register("over_powered_amethyst",
          () -> new GlowingItem(new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));

  // Ingots
  public static final RegistryObject<Item> LEAD_INGOT = ITEMS.register("lead_ingot",
      () -> new Item(new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));

  // Power stones
  public static final RegistryObject<Item> LESSER_POWER_STONE = ITEMS.register("lesser_power_stone",
      () -> new Item(new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> GREATER_POWER_STONE =
      ITEMS.register("greater_power_stone",
          () -> new GlowingItem(new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));

  // Mob related
  public static final RegistryObject<Item> VAMPIRE_BAT_TOOTH = ITEMS.register("vampire_bat_tooth",
      () -> new WrittenBookItem(new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));

  // Assorted
  public static final RegistryObject<Item> GOING_TO_THE_BALL =
      ITEMS.register("going_to_the_ball", () -> new Item(new Item.Properties()));
  public static final RegistryObject<Item> INTRO_BOOK = ITEMS.register("intro_book",
      () -> new Item(new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));

  @Override
  public void initialise() {
    OrbitalRegistryGenerator.registerDeferredRegister(ITEMS);
  }

  @Override
  public int priority() {
    return 2;
  }

  @Override
  public String name() {
    return "items";
  }

}
