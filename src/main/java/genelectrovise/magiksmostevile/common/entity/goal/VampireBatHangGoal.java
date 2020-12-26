/**
 * 
 */
package genelectrovise.magiksmostevile.common.entity.goal;

import javax.annotation.Nullable;
import genelectrovise.magiksmostevile.common.entity.vampire_bat.VampireBatEntity;
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
    this.world = vampireBat.world;
  }

  @Override
  public boolean shouldExecute() {

    if (!vampireBat.isInActiveLightLevel()) {
      return false;
    }

    if (!blockAboveIsHangable()) {
      return false;
    }

    if (vampireBat.getAttackTarget() != null) {
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
  public boolean shouldContinueExecuting() {

    // Randomly, return false
    if (vampireBat.getRandom().nextInt(100) == 0) {
      makeBatNotHang();
      return false;
    }

    // Randomly, rotate head
    if (vampireBat.getRandom().nextInt(200) == 0) {
      vampireBat.rotationYawHead = (float) vampireBat.getRandom().nextInt(360);
    }

    return shouldExecute();
  }

  @Override
  public void startExecuting() {
    vampireBat.setIsBatHanging(true);
  }

  // Logic

  private boolean blockAboveIsHangable() {
    return world.getBlockState(vampireBat.getPosition().up()).isNormalCube(this.world,
        vampireBat.getPosition().up());
  }

  private void makeBatNotHang() {
    vampireBat.setIsBatHanging(false);
    this.world.playEvent((PlayerEntity) null, 1025, vampireBat.getPosition(), 0);
  }

  private PlayerEntity getClosestPredicateFulfillingPlayer() {
    return this.world.getClosestPlayer(VampireBatEntity.entityPredicate, vampireBat);
  }

  private boolean playerIsSurvivalAndNotNull(@Nullable PlayerEntity player) {
    if (player != null && !player.isCreative() && !player.isSpectator()) {
      return true;
    }

    return false;
  }

}
