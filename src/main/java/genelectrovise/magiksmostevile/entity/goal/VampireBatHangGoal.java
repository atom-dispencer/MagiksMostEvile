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

import javax.annotation.Nullable;
import genelectrovise.magiksmostevile.entity.vampire_bat.VampireBatEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

/**
 * @author GenElectrovise 4 Jun 2020
 */
public class VampireBatHangGoal extends Goal {

  private VampireBatEntity vampireBat;
  private World world;

  /**
   * Replaces the old, horrible vanilla code for making a bat change to its hanging state.
   */
  public VampireBatHangGoal(VampireBatEntity vampireBat) {
    this.vampireBat = vampireBat;
    this.world = vampireBat.level;
  }

  @Override
  public boolean canUse() {

    if (!blockAboveIsHangable()) {
      return false;
    }

    if (vampireBat.getTarget() != null) {
      return false;
    }

    // If there is a player nearby who is not creative or spectator, return false.
    PlayerEntity closestPlayer = getClosestPredicateFulfillingPlayer();
    if (playerIsSurvivalAndNotNull(closestPlayer)) {
      makeBatNotHang();
      return false;
    }

    return true;
  }

  @Override
  public boolean canContinueToUse() {

    // Randomly, return false
    if (vampireBat.getRandom().nextInt(100) == 0) {
      makeBatNotHang();
      return false;
    }

    // Randomly, rotate head
    if (vampireBat.getRandom().nextInt(200) == 0) {
      vampireBat.yHeadRot = (float) vampireBat.getRandom().nextInt(360);
    }

    return canUse();
  }

  @Override
  public void start() {
    vampireBat.setIsBatHanging(true);
  }

  // Logic

  private boolean blockAboveIsHangable() {
    return world.getBlockState(vampireBat.blockPosition().above()).isRedstoneConductor(this.world, vampireBat.blockPosition().above());
  }

  private void makeBatNotHang() {
    vampireBat.setIsBatHanging(false);
    this.world.levelEvent((PlayerEntity) null, 1025, vampireBat.blockPosition(), 0);
  }

  private PlayerEntity getClosestPredicateFulfillingPlayer() { return this.world.getNearestPlayer(VampireBatEntity.entityPredicate, vampireBat); }

  private boolean playerIsSurvivalAndNotNull(@Nullable PlayerEntity player) {
    if (player != null && !player.isCreative() && !player.isSpectator()) {
      return true;
    }

    return false;
  }

}
