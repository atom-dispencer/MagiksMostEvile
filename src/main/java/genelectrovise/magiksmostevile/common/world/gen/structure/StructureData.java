package genelectrovise.magiksmostevile.common.world.gen.structure;

import net.minecraft.world.gen.GenerationStage.Decoration;

public class StructureData {

  private String name;
  private Decoration stage;

  public StructureData(String name, Decoration stage) {
    this.name = name;
    this.stage = stage;
  }
  
  public String getName() {
    return name;
  }
  
  public Decoration getStage() {
    return stage;
  }

}
