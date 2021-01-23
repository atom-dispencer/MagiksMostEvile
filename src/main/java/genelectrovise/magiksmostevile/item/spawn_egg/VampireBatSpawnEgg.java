/**
 * 
 */
package genelectrovise.magiksmostevile.item.spawn_egg;

import genelectrovise.magiksmostevile.registry.orbital.registries.EntityOrbitalRegistry;
import net.minecraft.entity.EntityType;

/**
 * @author GenElectrovise 5 Jun 2020
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
    return EntityOrbitalRegistry.VAMPIRE_BAT.get();
  }

}
