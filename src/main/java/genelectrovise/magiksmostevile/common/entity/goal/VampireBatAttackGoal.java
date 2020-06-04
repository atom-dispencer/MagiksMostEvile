/**
 * 
 */
package genelectrovise.magiksmostevile.common.entity.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;

/**
 * @author GenElectrovise
 * 4 Jun 2020
 */
public class VampireBatAttackGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

	/**
	 * @param goalOwnerIn
	 * @param targetClassIn
	 * @param checkSight
	 */
	public VampireBatAttackGoal(MobEntity goalOwnerIn, Class<T> targetClassIn, boolean checkSight) {
		super(goalOwnerIn, targetClassIn, checkSight);
	}

}
