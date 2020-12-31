package genelectrovise.magiksmostevile.common.world.gen.structure;

import com.google.common.collect.ImmutableList;
import genelectrovise.magiksmostevile.common.world.gen.EnumFeatureLocation;
import net.minecraft.world.gen.GenerationStage.Decoration;

public class StructureData {

  private String name;
  private Decoration stage;
  private ImmutableList<EnumFeatureLocation> locations;

  public StructureData(String name, Decoration stage, EnumFeatureLocation ... locations) {
    this.name = name;
    this.stage = stage;
    this.locations = ImmutableList.copyOf(locations);
  }
  
  public String getName() {
    return name;
  }
  
  public Decoration getStage() {
    return stage;
  }
  
  public ImmutableList<EnumFeatureLocation> getCategories() {
    return locations;
  }

}
