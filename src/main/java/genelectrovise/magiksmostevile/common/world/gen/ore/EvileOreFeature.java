package genelectrovise.magiksmostevile.common.world.gen.ore;

import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class EvileOreFeature extends OreFeature {

	public EvileOreFeature(Function<Dynamic<?>, ? extends OreFeatureConfig> p_i51472_1_) {
		super(p_i51472_1_);
	}

}
