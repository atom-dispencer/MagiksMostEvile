package genelectrovise.magiksmostevile.core.registry.orbital.registries;

import genelectrovise.magiksmostevile.core.registry.orbital.IOrbitalRegistry;
import genelectrovise.magiksmostevile.core.support.EvileItemGroup;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;

public class FoodOrbitalRegistry implements IOrbitalRegistry {

  // =========FOOD========================================================================================================================
  public static final RegistryObject<Item> AMETHYST_POTATO =
      ItemOrbitalRegistry.ITEMS.register("amethyst_potato",
          () -> new Item(new Item.Properties().food((new Food.Builder()).hunger(3).saturation(0.8F)
              .effect(() -> new EffectInstance(Effects.SPEED, 400, 1), 1.0F).fastToEat().build())
              .group(EvileItemGroup.ITEMGROUP_EVILE)));

  @Override
  public int priority() {
    return 8;
  }

  @Override
  public void initialise() {

  }

  @Override
  public String name() {
    return "foods";
  }

}
