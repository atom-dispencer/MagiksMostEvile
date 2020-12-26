/**
 * 
 */
package genelectrovise.magiksmostevile.common.entity.goal;

import genelectrovise.magiksmostevile.common.entity.vampire_bat.VampireBatEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;

/**
 * @author GenElectrovise 4 Jun 2020
 */
public class VampireBatNearestAttackableTargetGoal<T extends LivingEntity>
    extends NearestAttackableTargetGoal<T> {

  VampireBatEntity bat;

  /**
   * @param goalOwnerIn
   * @param targetClassIn
   * @param checkSight
   */
  public VampireBatNearestAttackableTargetGoal(VampireBatEntity goalOwnerIn, Class<T> targetClassIn,
      boolean checkSight) {
    super(goalOwnerIn, targetClassIn, checkSight);
    this.bat = goalOwnerIn;
  }

  @Override
  public boolean shouldExecute() {

    if (!bat.isInActiveLightLevel() && bat.getAttackTarget() == null) {
      return false;
    }

    return super.shouldExecute();
  }

  @Override
  public void startExecuting() {
    super.startExecuting();
  }

}
