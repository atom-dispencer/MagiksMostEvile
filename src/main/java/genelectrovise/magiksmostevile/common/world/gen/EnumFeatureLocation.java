package genelectrovise.magiksmostevile.common.world.gen;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import com.mojang.serialization.Codec;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.biome.Biome;
import static net.minecraft.world.biome.Biome.*;

/**
 * {@link RuinedPortalPiece.Location}
 * 
 * @author GenElectrovise
 *
 */
public enum EnumFeatureLocation implements IStringSerializable {
  DEFAULT("default", Category.NONE), //
  DESERT("desert", Category.DESERT), //
  JUNGLE("jungle", Category.JUNGLE), //
  SWAMP("swamp", Category.SWAMP), //
  ICY("mountain", Category.ICY), //
  OCEAN("ocean", Category.OCEAN), //
  NETHER("nether", Category.NETHER), //
  END("end", Category.THEEND), //
  ;

  // Codec
  public static final Codec<EnumFeatureLocation> CODEC =
      IStringSerializable.createEnumCodec(EnumFeatureLocation::values, EnumFeatureLocation::get);

  // Map
  private static final Map<String, EnumFeatureLocation> MAP =
      Arrays.stream(values()).collect(Collectors.toMap(EnumFeatureLocation::getName, (location) -> {
        return location;
      }));

  // Enum
  private final String name;
  private final Biome.Category biomeCategory;

  private EnumFeatureLocation(String name, Biome.Category biomeCategory) {
    this.name = name;
    this.biomeCategory = biomeCategory;
  }

  public String getName() {
    return this.name;
  }

  public Biome.Category getBiomeCategory() {
    return biomeCategory;
  }

  // IStringSerializable

  public static EnumFeatureLocation get(String name) {
    return MAP.get(name);
  }

  public String getString() {
    return this.getName();
  }
}
