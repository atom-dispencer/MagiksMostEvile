package genelectrovise.magiksmostevile.common.world.gen.structure.shrine;

import com.mojang.serialization.Codec;
import genelectrovise.magiksmostevile.common.world.gen.EnumFeatureLocation;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.RuinedPortalFeature;


/**
 * {@link RuinedPortalFeature}
 * 
 * @author GenElectrovise
 *
 */
public class OvergroundShrineFeatureConfig implements IFeatureConfig {
  public static final Codec<OvergroundShrineFeatureConfig> CODEC = EnumFeatureLocation.CODEC
      .fieldOf("shrine_type").xmap(OvergroundShrineFeatureConfig::new, (feature) -> {
        return feature.location;
      }).codec();
  public final EnumFeatureLocation location;

  public OvergroundShrineFeatureConfig(EnumFeatureLocation location) {
    this.location = location;
  }

}
