package genelectrovise.magiksmostevile.entity.goal;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.core.registry.orbital.registries.EntityOrbitalRegistry;
import genelectrovise.magiksmostevile.entity.boss.kitty_the_kraken.KittyTheKrakenEntity;
import genelectrovise.magiksmostevile.entity.boss.kitty_the_kraken.squid_missile.SquidMissileEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

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
  private static final int ATTACK_AT = 200;
  private static final int LIMIT = 200;
  private static final float MISSILE_SPEED_MODIFIER = 5;

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

    MagiksMostEvile.LOGGER.debug("attackTimer: " + attackTimer);

    if (kraken.canEntityBeSeen(target) /* target visible */ && kraken.getDistanceSq(target) < (RANGE * RANGE) /* range */) {

      this.attackTimer++;

      // Ghast: if attackTimer == 10 AND silent, play screech

      if (this.attackTimer == ATTACK_AT) {
        attack(target);
        attackTimer = 0;
      }

    }

  }

  private void attack(LivingEntity target) {

    if (!kraken.world.isRemote) {
      Vector3d targetPosition = target.getPositionVec();
      Vector3d krakenPosition = kraken.getPositionVec();

      Vector3d subtractedDirection = targetPosition.subtract(krakenPosition);

      SquidMissileEntity missile =
          (SquidMissileEntity) EntityOrbitalRegistry.SQUID_MISSILE.get().spawn((ServerWorld) kraken.world, null, null, kraken.getPosition().up(5), SpawnReason.MOB_SUMMONED, false, false);
      missile.setExplosive(false);
      missile.setTicksUntilIgnition(20);
      
      missile.setDirection(subtractedDirection);
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
