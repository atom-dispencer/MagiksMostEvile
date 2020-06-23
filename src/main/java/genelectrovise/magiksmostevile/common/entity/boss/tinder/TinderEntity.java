/**
 * 
 */
package genelectrovise.magiksmostevile.common.entity.boss.tinder;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

/**
 * @author GenElectrovise
 * 23 Jun 2020
 */
public class TinderEntity extends MonsterEntity {

	/**
	 * @param type
	 * @param worldIn
	 */
	public TinderEntity(EntityType<? extends TinderEntity> type, World worldIn) {
		super(type, worldIn);
	}

}
