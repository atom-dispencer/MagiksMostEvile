package genelectrovise.magiksmostevile.common.world.gen.structure;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;

public class StructureData {

  private String name;
  private Decoration stage;
  private ImmutableList<Biome.Category> categories;

  public StructureData(String name, Decoration stage) {
    this.name = name;
    this.stage = stage;
    this.categories = ImmutableList.copyOf(categories);
  }
  
  public String getName() {
    return name;
  }
  
  public Decoration getStage() {
    return stage;
  }
  
  public ImmutableList<Biome.Category> getCategories() {
    return categories;
  }

}
