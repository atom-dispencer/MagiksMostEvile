package genelectrovise.magiksmostevile.item.equipment.tabulae;

import java.util.function.Supplier;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class Signum {

  private final ResourceLocation name;
  private final EnumSignumMaterialType type;
  private final Supplier<Item> item;

  protected Signum(ResourceLocation name, EnumSignumMaterialType type, Supplier<Item> item) {
    this.name = name;
    this.type = type;
    this.item = item;
  }

  public ResourceLocation getName() {
    return name;
  }

  public EnumSignumMaterialType getType() {
    return type;
  }
  
  public Supplier<Item> getItem() {
    return item;
  }

  public static enum EnumSignumMaterialType {
    WOOD, GOLD;
  }
}
