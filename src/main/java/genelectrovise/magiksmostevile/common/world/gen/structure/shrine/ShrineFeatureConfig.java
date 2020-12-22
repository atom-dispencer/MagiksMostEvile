package genelectrovise.magiksmostevile.common.world.gen.structure.shrine;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraftforge.registries.ForgeRegistries;

@Deprecated
public class ShrineFeatureConfig implements IFeatureConfig {

	private Biome biome;

	public ShrineFeatureConfig(Biome biome) {
		this.biome = biome;
	}

	@Override
	public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
		return new Dynamic<>(ops, ops.createMap(
				ImmutableMap.of(ops.createString("biome_path"), ops.createString(biome.getRegistryName().getPath()))));
	}

	public static <T> ShrineFeatureConfig deserialize(Dynamic<T> ops) {

		Biome biomeOut = null;
		try {
			biomeOut = ForgeRegistries.BIOMES
					.getValue(new ResourceLocation(ops.get("biome_path").asString("minecraft:plains")));
			return new ShrineFeatureConfig(biomeOut);
		} catch (Exception e) {
			System.err.println("Error deserializing ShrineFeatureConfig");
			e.printStackTrace();
		}
		return null;
	}

}
