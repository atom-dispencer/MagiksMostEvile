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
package genelectrovise.magiksmostevile.entity.goal;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.entity.boss.kitty_the_kraken.KittyTheKrakenEntity;
import genelectrovise.magiksmostevile.entity.boss.kitty_the_kraken.squid_missile.SquidMissileEntity;
import genelectrovise.magiksmostevile.registry.orbital.registries.EntityOrbitalRegistry;
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

  private static final int RANGE = 64;
  private static final int ATTACK_AT = 200;

  public SquidMissileAttackGoal(KittyTheKrakenEntity kraken) {
    this.kraken = kraken;
    this.attackTimer = 0;
  }

  @Override
  public boolean canUse() {

    if (kraken.getTarget() == null)
      return false;
    if (kraken.getFirstUsableArm() == null)
      return false;

    return true;
  }

  @Override
  public void start() {
    this.attackTimer = 0;
  }

  @Override
  public void stop() {
    kraken.setSquidMissileAttacking(false);
  }

  @Override
  public void tick() {
    LivingEntity target = kraken.getTarget();

    MagiksMostEvile.LOGGER.debug("attackTimer: " + attackTimer);

    if (kraken.canSee(target) /* target visible */ && kraken.distanceToSqr(target) < (RANGE * RANGE) /* range */) {

      this.attackTimer++;

      // Ghast: if attackTimer == 10 AND silent, play screech

      if (this.attackTimer == ATTACK_AT) {
        attack(target);
        attackTimer = 0;
      }

    }

  }

  private void attack(LivingEntity target) {

    if (!kraken.level.isClientSide) {
      Vector3d targetPosition = target.position();
      Vector3d krakenPosition = kraken.position();

      Vector3d subtractedDirection = targetPosition.subtract(krakenPosition);

      SquidMissileEntity missile =
          (SquidMissileEntity) EntityOrbitalRegistry.SQUID_MISSILE.get().spawn((ServerWorld) kraken.level, null, null, kraken.blockPosition().above(5), SpawnReason.MOB_SUMMONED, false, false);
      missile.setExplosive(false);
      missile.setTicksUntilIgnition(20);

      missile.setDirection(subtractedDirection);
    }
  }

  // Get and set

  public KittyTheKrakenEntity getKraken() { return kraken; }

  public int getAttackTimer() { return attackTimer; }

  public void setAttackTimer(int attackTimer) { this.attackTimer = attackTimer; }

}
