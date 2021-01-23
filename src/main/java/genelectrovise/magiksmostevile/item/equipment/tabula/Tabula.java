package genelectrovise.magiksmostevile.item.equipment.tabula;

import genelectrovise.magiksmostevile.core.support.EvileItemGroup;
import genelectrovise.magiksmostevile.registry.orbital.registries.ItemOrbitalRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;

public abstract class Tabula extends Item {

  public Tabula() {
    super(new Item.Properties().maxStackSize(1).group(EvileItemGroup.ITEMGROUP_EVILE).rarity(Rarity.UNCOMMON).containerItem(ItemOrbitalRegistry.BLANK_TABLULA.get()));
  }

}
