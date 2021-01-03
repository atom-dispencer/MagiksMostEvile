package genelectrovise.magiksmostevile.common.entity.goal;

import genelectrovise.magiksmostevile.common.core.registry.orbital.registries.EntityOrbitalRegistry;
import genelectrovise.magiksmostevile.common.entity.boss.kitty_the_kraken.KittyTheKrakenEntity;
import genelectrovise.magiksmostevile.common.entity.boss.kitty_the_kraken.squid_missile.SquidMissileEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.util.math.vector.Vector3d;

/**
 * {@link GhastEntity.FireballAttackGoal}
 * 
 * @author GenElectrovise
 *
 */
public class SquidMissileAttackGoal extends Goal {

  private final KittyTheKrakenEntity kraken;
  private int attackTimer;

  private static final int ZERO = 0;
  private static final int RANGE = 64;
  private static final int ATTACK_AT = 20;
  private static final int MISSILE_SPEED_MODIFIER = 2;

  public SquidMissileAttackGoal(KittyTheKrakenEntity kraken) {
    this.kraken = kraken;
    this.attackTimer = 0;
  }

  @Override
  public boolean shouldExecute() {

    if (kraken.getAttackTarget() == null)
      return false;
    if (kraken.getFirstUsableArm() == null)
      return false;

    return true;
  }

  @Override
  public void startExecuting() {
    this.attackTimer = 0;
  }

  @Override
  public void resetTask() {
    kraken.setSquidMissileAttacking(false);
  }

  @Override
  public void tick() {
    LivingEntity target = kraken.getAttackTarget();

    if (kraken.canEntityBeSeen(target) /* target visible */ && kraken.getDistanceSq(target) < (RANGE * RANGE) /* range */) {

      this.attackTimer++;

      // Ghast: if attackTimer == 10 AND silent, play screech

      if (this.attackTimer == ATTACK_AT) {
        attack(target);
      }

    } else if (attackTimer > ZERO) {
      attackTimer--;
    }



  }

  private void attack(LivingEntity target) {

    if (!kraken.world.isRemote) {
      Vector3d targetPosition = new Vector3d(target.getPosX(), target.getPosY(), target.getPosZ());
      Vector3d krakenPosition = new Vector3d(kraken.getPosX(), kraken.getPosY(), kraken.getPosZ());

      Vector3d direction = targetPosition.subtract(targetPosition);
      direction.normalize();

      SquidMissileEntity missile = EntityOrbitalRegistry.SQUID_MISSILE.get().create(kraken.world);
      missile.setExplosive(false);
      missile.setTicksUntilIgnition(20);
      missile.setPositionAndUpdate(krakenPosition.getX(), krakenPosition.getY(), krakenPosition.getZ());
      missile.setVelocity(direction.x * MISSILE_SPEED_MODIFIER, direction.y * MISSILE_SPEED_MODIFIER, direction.z * MISSILE_SPEED_MODIFIER);
    }
  }

  // Get and set

  public KittyTheKrakenEntity getKraken() {
    return kraken;
  }

  public int getAttackTimer() {
    return attackTimer;
  }

  public void setAttackTimer(int attackTimer) {
    this.attackTimer = attackTimer;
  }

}
