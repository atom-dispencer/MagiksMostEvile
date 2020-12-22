/**
 * 
 */
package genelectrovise.magiksmostevile.common.item.spawn_egg;

import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import net.minecraft.entity.EntityType;

/**
 * @author GenElectrovise
 * 5 Jun 2020
 */
public class VampireBatSpawnEgg extends EvileSpawnEgg {

	/**
	 * @param properties
	 */
	public VampireBatSpawnEgg(Properties properties) {
		super(properties);
	}
	
	@Override
	public EntityType<?> getEntityType() {
		return EvileDeferredRegistry.VAMPIRE_BAT.get();
	}

}
