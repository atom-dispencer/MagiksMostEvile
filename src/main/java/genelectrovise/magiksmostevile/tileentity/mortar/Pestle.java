package genelectrovise.magiksmostevile.tileentity.mortar;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

public class Pestle extends Item {

  private EnumPestleLevel level;

  public Pestle(Properties properties, EnumPestleLevel level) {
    super(properties);
    this.level = level;
  }

  public EnumPestleLevel getLevel() {
    return level;
  }

  @Override
  public ActionResultType onItemUse(ItemUseContext context) {
    return ActionResultType.PASS;
  }

}
