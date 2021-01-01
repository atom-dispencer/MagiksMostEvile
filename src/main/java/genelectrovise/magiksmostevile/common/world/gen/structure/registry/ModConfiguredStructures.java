package genelectrovise.magiksmostevile.common.world.gen.structure.registry;

import java.util.HashMap;
import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.world.gen.EnumFeatureLocation;
import genelectrovise.magiksmostevile.common.world.gen.structure.shrine.OvergroundShrineFeatureConfig;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.StructureFeature;

public class ModConfiguredStructures {

  public static final HashMap<String, StructureFeature<?, ?>> map = new HashMap<String, StructureFeature<?, ?>>();

  public static final StructureFeature<?, ?> OVERGROUND_SHRINE_default = ModStructures.OVERGROUND_SHRINE.withConfiguration(new OvergroundShrineFeatureConfig(EnumFeatureLocation.DEFAULT));

  public static void registerConfiguredStructures() {
    register(new ResourceLocation(MagiksMostEvile.MODID, "overground_shrine_default"), OVERGROUND_SHRINE_default);

  }

  public static void register(ResourceLocation resourceLocation, StructureFeature<?, ?> feature) {
    map.put(resourceLocation.toString(), Registry.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, resourceLocation, feature));
  }

}
