package genelectrovise.magiksmostevile.registry.orbital.registries;

import genelectrovise.magiksmostevile.core.support.EvileItemGroup;
import genelectrovise.magiksmostevile.item.equipment.staff.AmethystStaff;
import genelectrovise.magiksmostevile.item.equipment.staff.DebuggingStaff;
import genelectrovise.magiksmostevile.item.equipment.staff.DimensionWarpingStaff;
import genelectrovise.magiksmostevile.registry.orbital.IOrbitalRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;

public class StaffOrbitalRegistry implements IOrbitalRegistry {

  // =========STAFFS====================================================================================================================
  public static final RegistryObject<Item> AMETHYST_STAFF =
      ItemOrbitalRegistry.ITEMS.register("amethyst_staff", () -> new AmethystStaff(new Item.Properties().group(EvileItemGroup.MAGIKS_MOST_EVILE).maxStackSize(1).rarity(Rarity.UNCOMMON)));
  public static final RegistryObject<Item> DIMENSION_WARPING_STAFF =
      ItemOrbitalRegistry.ITEMS.register("dimension_warping_staff", () -> new DimensionWarpingStaff(new Item.Properties().group(EvileItemGroup.MAGIKS_MOST_EVILE).maxStackSize(1).rarity(Rarity.EPIC)));
  public static final RegistryObject<Item> STAFF_OF_DEGUGGING =
      ItemOrbitalRegistry.ITEMS.register("debugging_staff", () -> new DebuggingStaff(new Item.Properties().group(EvileItemGroup.MAGIKS_MOST_EVILE).maxStackSize(1).rarity(Rarity.EPIC)));

  @Override
  public int priority() {
    return 5;
  }

  @Override
  public void initialise() {

  }

  @Override
  public String name() {
    return "staffs";
  }

}
