/**
 * 
 */
package genelectrovise.magiksmostevile.common.ritual;

import com.google.common.collect.ImmutableMap;

/**
 * @author GenElectrovise 19 May 2020
 */
public class Rituals {
	public static final ImmutableMap<String, Class<? extends Ritual>> ALL = ImmutableMap.of("convert_amethyst", RitualConvertAmethyst.class);
}
