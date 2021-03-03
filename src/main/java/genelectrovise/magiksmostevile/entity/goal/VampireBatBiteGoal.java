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

import java.util.Random;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.entity.vampire_bat.VampireBatEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

/**
 * @author GenElectrovise 4 Jun 2020
 */
public class VampireBatBiteGoal extends MeleeAttackGoal {

  private VampireBatEntity vampireBat;

  private int cooldown;
  private int cooldownMax;
  private int maxReinforcements;

  public VampireBatBiteGoal(VampireBatEntity vampireBat) {
    super(vampireBat, vampireBat.getAttribute(Attributes.FLYING_SPEED).getValue(), true);
    this.vampireBat = vampireBat;

    this.cooldownMax = VampireBatEntity.REINFORCEMENT_COOLDOWN;
    this.maxReinforcements = VampireBatEntity.MAX_REINFORCEMENTS;
    this.cooldown = cooldownMax;
  }

  @Override
  public void tick() {
    vampireBat.setIsBatHanging(false);

    if (shouldSummonAid()) {
      summonAid();
    }

    super.tick();
  }

  @Override
  public boolean shouldExecute() {

    if (!vampireBat.isInActiveLightLevel()) {
      return false;
    }

    return super.shouldExecute();
  }

  private boolean shouldSummonAid() {

    if (cooldown == 0) {

      if (vampireBat.getRNG().nextInt(VampireBatEntity.REINFORCEMENT_CHANCE) == 0
          && vampireBat.batsWithinArea(VampireBatEntity.REINFORCEMENT_DETECTION_RADIUS)
              .size() < VampireBatEntity.MINIMUM_REINFORCEMENTS) {
        cooldown = cooldownMax;
        return true;
      } else {
        if (vampireBat.getAttackTarget() != null & vampireBat.getAttackTarget().isAlive()) {
          for (VampireBatEntity bat : vampireBat
              .batsWithinArea(VampireBatEntity.REINFORCEMENT_CALLING_RADIUS)) {
            bat.setAttackTarget(vampireBat.getAttackTarget());
          }
        }
      }

    } else {
      cooldown--;
    }

    return false;
  }

  private void summonAid() {

    MagiksMostEvile.LOGGER.debug("Flappys!");

    if (vampireBat.world instanceof ServerWorld && vampireBat.getRandom().nextInt(10) == 0) {
      ServerWorld world = (ServerWorld) vampireBat.world;

      for (int i = 0; i < maxReinforcements + 1; i++) {
        Random rand = vampireBat.getRandom();

        if (rand.nextBoolean()) {
          double x = vampireBat.getPosX();
          double y = vampireBat.getPosY();
          double z = vampireBat.getPosZ();

          double nearbyX = x + nearbyPos(rand, 5);
          double nearbyY = y + nearbyPos(rand, 5);
          double nearbyZ = z + nearbyPos(rand, 5);

          if (world.getBlockState(new BlockPos(nearbyX, nearbyY, nearbyZ))
              .getBlock() == Blocks.AIR) {
            VampireBatEntity newBat = (VampireBatEntity) vampireBat.getType().create(world);
            newBat.setLocationAndAngles(nearbyX, nearbyY, nearbyZ, vampireBat.rotationYaw,
                vampireBat.rotationPitch);
            world.addEntity(newBat);
          }
        }
      }
    }

    cooldown--;

    super.startExecuting();
  }

  private double nearbyPos(Random rand, int radius) {
    return rand.nextInt(radius * 2) - radius + 0.5d;
  }

}
