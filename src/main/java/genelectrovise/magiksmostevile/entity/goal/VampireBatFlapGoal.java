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
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

/**
 * @author GenElectrovise 4 Jun 2020
 */
public class VampireBatFlapGoal extends Goal {

  private VampireBatEntity vampireBat;

  /**
   * @param vampireBatEntity
   */
  public VampireBatFlapGoal(VampireBatEntity vampireBatEntity) {
    this.vampireBat = vampireBatEntity;
  }

  @Override
  public boolean shouldExecute() {

    if (!vampireBat.isInActiveLightLevel() && vampireBat.getIsBatHanging()) {
      return false;
    }

    // If the bat is hanging, return false.
    if (vampireBat.getIsBatHanging()) {
      return false;
    }

    // If has a target, return false.
    if (vampireBat.getAttackTarget() != null) {
      return false;
    }

    // If block above can be hung on, return false.
    if (vampireBat.world.getBlockState(vampireBat.getPosition().up()).isNormalCube(vampireBat.world,
        vampireBat.getPosition().up())) {
      return false;
    }

    // Otherwise, return true.
    return true;
  }

  @Override
  public boolean shouldContinueExecuting() {

    // Check normal conditions.
    return shouldExecute();
  }

  @Override
  public void startExecuting() {
    execute();
  }

  @Override
  public void tick() {
    execute();
  }

  private void execute() {
    vampireBat.setIsBatHanging(false);
    resetSpawnLocation();
    moveRandomly();
  }

  private void moveRandomly() {
    double d0 = (double) vampireBat.spawnPosition.getX() + 0.5D - vampireBat.getPosX();
    double d1 = (double) vampireBat.spawnPosition.getY() + 0.1D - vampireBat.getPosY();
    double d2 = (double) vampireBat.spawnPosition.getZ() + 0.5D - vampireBat.getPosZ();

    Vector3d vec3d = vampireBat.getMotion();
    Vector3d vec3d1 = vec3d.add((Math.signum(d0) * 0.5D - vec3d.x) * (double) 0.1F,
        (Math.signum(d1) * (double) 0.7F - vec3d.y) * (double) 0.1F,
        (Math.signum(d2) * 0.5D - vec3d.z) * (double) 0.1F);
    vampireBat.setMotion(vec3d1);
    float f =
        (float) (MathHelper.atan2(vec3d1.z, vec3d1.x) * (double) (180F / (float) Math.PI)) - 90.0F;
    float f1 = MathHelper.wrapDegrees(f - vampireBat.rotationYaw);
    vampireBat.moveForward = 0.5F;
    vampireBat.rotationYaw += f1;
  }

  private void resetSpawnLocation() {
    if (hasValidSpawnLocation()) {
      vampireBat.spawnPosition = null;
    }

    if (spawnPosNullOrWithinRange()) {
      double newX = vampireBat.getPosX() + (double) vampireBat.getRandom().nextInt(7)
          - (double) vampireBat.getRandom().nextInt(7);
      double newY = vampireBat.getPosY() + (double) vampireBat.getRandom().nextInt(6) - 2.0D;
      double newZ = vampireBat.getPosZ() + (double) vampireBat.getRandom().nextInt(7)
          - (double) vampireBat.getRandom().nextInt(7);

      vampireBat.spawnPosition = new BlockPos(newX, newY, newZ);
    }
  }

  private boolean spawnPosNullOrWithinRange() {
    return (vampireBat.spawnPosition == null || vampireBat.getRandom().nextInt(30) == 0
        || vampireBat.spawnPosition.withinDistance(vampireBat.getPositionVec(), 2.0D));
  }

  private boolean hasValidSpawnLocation() {
    return (vampireBat.spawnPosition != null
        && (!vampireBat.world.isAirBlock(vampireBat.spawnPosition)
            || vampireBat.spawnPosition.getY() < 1));
  }

}
