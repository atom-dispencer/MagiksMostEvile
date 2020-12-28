package genelectrovise.magiksmostevile.common.world.gen;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import com.mojang.serialization.Codec;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.gen.feature.structure.RuinedPortalPiece;

/**
 * {@link RuinedPortalPiece.Location}
 * 
 * @author GenElectrovise
 *
 */
public enum EnumFeatureLocation implements IStringSerializable {
  DEFAULT("default"), //
  DESERT("desert"), //
  JUNGLE("jungle"), //
  SWAMP("swamp"), //
  MOUNTAIN("mountain"), //
  TUNDRA("tundra"), //
  OCEAN("ocean"), //
  NETHER("nether"), //
  END("end"), //
  ;

  // Codec
  public static final Codec<EnumFeatureLocation> CODEC =
      IStringSerializable.createEnumCodec(EnumFeatureLocation::values, EnumFeatureLocation::get);

  // Map
  private static final Map<String, EnumFeatureLocation> MAP =
      Arrays.stream(values()).collect(Collectors.toMap(EnumFeatureLocation::getName, (location) -> {
        return location;
      }));

  // Enum Code
  private final String name;

  private EnumFeatureLocation(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public static EnumFeatureLocation get(String name) {
    return MAP.get(name);
  }

  public String getString() {
    return this.getName();
  }
}
