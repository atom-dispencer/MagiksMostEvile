package genelectrovise.magiksmostevile.core.registry.orbital.registries;

import genelectrovise.magiksmostevile.core.registry.orbital.IOrbitalRegistry;
import genelectrovise.magiksmostevile.core.support.EvileItemGroup;
import genelectrovise.magiksmostevile.item.equipment.staff.AmethystStaff;
import genelectrovise.magiksmostevile.item.equipment.staff.DebuggingStaff;
import genelectrovise.magiksmostevile.item.equipment.staff.DimensionWarpingStaff;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class StaffOrbitalRegistry implements IOrbitalRegistry {

  // =========STAFFS====================================================================================================================
  public static final RegistryObject<Item> AMETHYST_STAFF =
      ItemOrbitalRegistry.ITEMS.register("amethyst_staff", () -> new AmethystStaff(
          new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE).maxStackSize(1)));
  public static final RegistryObject<Item> DIMENSION_WARPING_STAFF =
      ItemOrbitalRegistry.ITEMS.register("dimension_warping_staff", () -> new DimensionWarpingStaff(
          new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE).maxStackSize(1)));
  public static final RegistryObject<Item> STAFF_OF_DEGUGGING =
      ItemOrbitalRegistry.ITEMS.register("debugging_staff", () -> new DebuggingStaff(
          new Item.Properties().group(EvileItemGroup.ITEMGROUP_EVILE).maxStackSize(1)));

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
