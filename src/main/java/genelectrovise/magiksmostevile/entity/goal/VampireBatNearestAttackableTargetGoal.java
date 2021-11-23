/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise
 *
 * This file is part of Magiks Most Evile. Magiks Most Evile is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile.
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
/**
 * 
 */
package genelectrovise.magiksmostevile.entity.goal;

import genelectrovise.magiksmostevile.entity.vampire_bat.VampireBatEntity;
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
  public boolean canUse() {

    if (bat.getTarget() == null) {
      return false;
    }

    return super.canUse();
  }

  @Override
  public void start() {
    super.start();
  }

}
