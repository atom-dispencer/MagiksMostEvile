package genelectrovise.magiksmostevile.registry.orbital.registries;

import genelectrovise.magiksmostevile.core.support.EvileItemGroup;
import genelectrovise.magiksmostevile.registry.orbital.IOrbitalRegistry;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class BlockItemOrbitalRegistry implements IOrbitalRegistry {

  public static final RegistryObject<Item> AMETHYST_BLOCK_ITEM = ItemOrbitalRegistry.ITEMS
      .register("amethyst_block", () -> new BlockItem(BlockOrbitalRegistry.AMETHYST_BLOCK.get(),
          new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> LEAD_BLOCK_ITEM = ItemOrbitalRegistry.ITEMS
      .register("lead_block", () -> new BlockItem(BlockOrbitalRegistry.LEAD_BLOCK.get(),
          new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));

  // Amethyst Ore
  public static final RegistryObject<Item> AMETHYST_ORE_OVERWORLD_ITEM =
      ItemOrbitalRegistry.ITEMS.register("amethyst_ore_overworld",
          () -> new BlockItem(BlockOrbitalRegistry.AMETHYST_ORE_OVERWORLD.get(),
              new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> AMETHYST_ORE_NETHER_ITEM =
      ItemOrbitalRegistry.ITEMS.register("amethyst_ore_nether",
          () -> new BlockItem(BlockOrbitalRegistry.AMETHYST_ORE_NETHER.get(),
              new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> AMETHYST_ORE_END_ITEM = ItemOrbitalRegistry.ITEMS
      .register("amethyst_ore_end", () -> new BlockItem(BlockOrbitalRegistry.AMETHYST_ORE_END.get(),
          new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));

  // Lead Ore
  public static final RegistryObject<Item> LEAD_ORE_OVERWORLD_ITEM =
      ItemOrbitalRegistry.ITEMS.register("lead_ore_overworld",
          () -> new BlockItem(BlockOrbitalRegistry.LEAD_ORE_OVERWORLD.get(),
              new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> LEAD_ORE_NETHER_ITEM = ItemOrbitalRegistry.ITEMS
      .register("lead_ore_nether", () -> new BlockItem(BlockOrbitalRegistry.LEAD_ORE_NETHER.get(),
          new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> LEAD_ORE_END_ITEM = ItemOrbitalRegistry.ITEMS
      .register("lead_ore_end", () -> new BlockItem(BlockOrbitalRegistry.LEAD_ORE_END.get(),
          new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));

  // Assorted
  public static final RegistryObject<Item> AMETHYST_CRYSTAL_ITEM = ItemOrbitalRegistry.ITEMS
      .register("amethyst_crystal", () -> new BlockItem(BlockOrbitalRegistry.AMETHYST_CRYSTAL.get(),
          new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));
  public static final RegistryObject<Item> ALTAR_ITEM = ItemOrbitalRegistry.ITEMS.register("altar",
      () -> new BlockItem(BlockOrbitalRegistry.ALTAR.get(),
          new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE)));

  @Override
  public int priority() {
    return 3;
  }

  @Override
  public void initialise() {

  }

  @Override
  public String name() {
    return "blockitems";
  }

}
