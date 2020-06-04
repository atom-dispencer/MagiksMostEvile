/**
 * 
 */
package genelectrovise.magiksmostevile.common.entity.goal;

import genelectrovise.magiksmostevile.common.entity.vampire_bat.VampireBatEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;

/**
 * @author GenElectrovise 4 Jun 2020
 */
public class VampireBatBiteGoal extends Goal {

	private VampireBatEntity vampireBat;
	private LivingEntity target;
	private BlockPos targetPos;

	public VampireBatBiteGoal(VampireBatEntity vampireBat) {
		this.vampireBat = vampireBat;
	}

	@Override
	public boolean shouldExecute() {
		return vampireBat.getAttackTarget() != null;
	}
	
	@Override
	public void startExecuting() {
		target = vampireBat.getAttackTarget();
		targetPos = target.getPosition();
	}

}
