package genelectrovise.magiksmostevile.common.world.gen.ore;

import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.DepthAveragePlacement;

public class SimpleDepthAveragePlacement extends DepthAveragePlacement {

  public SimpleDepthAveragePlacement() {
    super(DepthAverageConfig.CODEC);
  }
  
  @Override
  public ConfiguredPlacement<DepthAverageConfig> configure(DepthAverageConfig config) {
    return super.configure(config);
  }
  
}
