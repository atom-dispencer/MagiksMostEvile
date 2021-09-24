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
package genelectrovise.magiksmostevile.entity.boss.kitty_the_kraken;

import javax.annotation.Nullable;
import genelectrovise.magiksmostevile.entity.EntityAttributeManager;
import genelectrovise.magiksmostevile.entity.boss.BossMob;
import genelectrovise.magiksmostevile.entity.goal.SquidMissileAttackGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.ShulkerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;

/**
 * CreeperEntity
 * 
 * @author GenElectrovise 23 Jun 2020
 */
public class KittyTheKrakenEntity extends BossMob {

  private boolean squidMissileAttacking = false;
  private final KrakenArm leftArm = new KrakenArm(this, KrakenArm.KrakenArmSide.LEFT);
  private final KrakenArm rightArm = new KrakenArm(this, KrakenArm.KrakenArmSide.RIGHT);

  /**
   * @param type
   * @param worldIn
   */
  public KittyTheKrakenEntity(EntityType<? extends KittyTheKrakenEntity> type, World worldIn) {
    super(type, worldIn, BossInfo.Color.BLUE, BossInfo.Overlay.PROGRESS, true, true);
  }

  @Override
  public void tick() {
    super.tick();
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(0, new SquidMissileAttackGoal(this));

    this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<PlayerEntity>(this, PlayerEntity.class, false));
    this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<ShulkerEntity>(this, ShulkerEntity.class, false));
  }

  /**
   * Static! Non-inherited! Create a map of attributes. Called from {@link EntityAttributeManager}.
   */
  public static AttributeModifierMap.MutableAttribute getEntityAttributes() {
    return MobEntity.createMobAttributes() //
        .add(Attributes.MAX_HEALTH, 100.0D)
        .add(Attributes.FLYING_SPEED, 2.0f)
        .add(Attributes.ATTACK_DAMAGE, 1.0f)
        .add(Attributes.ATTACK_SPEED, 5.0f)
        .add(Attributes.FOLLOW_RANGE, 64.0f);
  }

  @Override
  public double getEyeY() {
    return this.getY() + 4.5;
  }

  /**
   * @return {@link #leftArm}, otherwise {@link #rightArm}. If {@link #rightArm} is not usable, null.
   */
  @Nullable
  public KrakenArm getFirstUsableArm() {
    return leftArm.isUsable() ? leftArm : (rightArm.isUsable() ? rightArm : null);
  }

  // Get and set

  public boolean isSquidMissileAttacking() {
    return squidMissileAttacking;
  }

  public void setSquidMissileAttacking(boolean squidMissileAttacking) {
    this.squidMissileAttacking = squidMissileAttacking;
  }

  public KrakenArm getLeftArm() {
    return leftArm;
  }

  public KrakenArm getRightArm() {
    return rightArm;
  }

}
