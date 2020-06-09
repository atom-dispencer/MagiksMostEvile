/**
 * 
 */
package genelectrovise.magiksmostevile.common.ritual;

import com.google.common.collect.ImmutableMap;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import net.minecraft.util.ResourceLocation;

/**
 * @author GenElectrovise 19 May 2020
 */
public class Rituals {
	public static final ImmutableMap<ResourceLocation, Class<? extends Ritual>> ALL = ImmutableMap.of(new ResourceLocation(MagiksMostEvile.MODID, "convert_amethyst"), ConvertAmethystRitual.class);
}
