/**
 * 
 */
package genelectrovise.magiksmostevile.common.entity.boss.cinder;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

/**
 * @author GenElectrovise 23 Jun 2020
 */
public class CinderEntity extends MonsterEntity {

	/**
	 * @param entityType
	 * @param world
	 */
	public CinderEntity(EntityType<? extends CinderEntity> entityType, World world) {
		super(entityType, world);
	}

}
