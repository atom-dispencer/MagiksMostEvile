package genelectrovise.magiksmostevile.item.equipment.tabula;

import genelectrovise.magiksmostevile.core.support.EvileItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;

public abstract class Tabula extends Item {

  public Tabula() {
    super(new Item.Properties().maxStackSize(1).group(EvileItemGroup.MAGIKS_MOST_EVILE).rarity(Rarity.UNCOMMON));
  }

}
