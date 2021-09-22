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
package genelectrovise.magiksmostevile.entity.vampire_bat;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.core.SetupManager;
import genelectrovise.magiksmostevile.entity.goal.VampireBatBiteGoal;
import genelectrovise.magiksmostevile.entity.goal.VampireBatFlapGoal;
import genelectrovise.magiksmostevile.entity.goal.VampireBatHangGoal;
import genelectrovise.magiksmostevile.entity.goal.VampireBatNearestAttackableTargetGoal;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.monster.VexEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.horse.TraderLlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

/**
 * @see CreeperEntity
 * @see BeeEntity
 * @see VexEntity
 * @see PhantomEntity
 * @see FlyingEntity
 * @see FlyingMovementController
 * @see ZombieEntity
 * @see BatEntity
 * @author GenElectrovise 1 Jun 2020
 */
public class VampireBatEntity extends MonsterEntity {
  private static final DataParameter<Byte> HANGING = EntityDataManager.defineId(VampireBatEntity.class, DataSerializers.BYTE);
  public static final EntityPredicate entityPredicate = new EntityPredicate().range(4.0D).allowSameTeam();
  public BlockPos spawnPosition;

  public static final ResourceLocation LOOT_TABLE = new ResourceLocation(MagiksMostEvile.MODID, "loot_tab/vampire_bat");

  public static final int REINFORCEMENT_CHANCE = 2;
  public static final int REINFORCEMENT_COOLDOWN = 100;
  public static final int MAX_REINFORCEMENTS = 10;
  public static final int REINFORCEMENT_DETECTION_RADIUS = 15;
  public static final int REINFORCEMENT_CALLING_RADIUS = 25;
  public static final int MINIMUM_REINFORCEMENTS = 5;

  public VampireBatEntity(EntityType<? extends VampireBatEntity> entityType, World world) {
    super(entityType, world);
    this.setIsBatHanging(false);
    this.moveControl = new VampireBatMoveHelperController(this, 60, true);
    this.navigation = new FlyingPathNavigator(this, this.level);

    navigation.setSpeedModifier(this.getAttribute(Attributes.FLYING_SPEED).getValue());
    navigation.setCanFloat(false);
  }

  protected void registerData() {
    super.defineSynchedData();
    this.entityData.define(HANGING, (byte) 0);
  }

  /**
   * Returns the volume for the sounds this mob makes.
   */
  @Override
  protected float getSoundVolume() {
    return 0.1F;
  }

  /**
   * Gets the pitch of living sounds in living entities.
   */
  @Override
  protected float getVoicePitch() {
    return super.getVoicePitch() * 0.95F;
  }

  @Override
  @Nullable
  public SoundEvent getAmbientSound() {
    return this.getIsBatHanging() && this.random.nextInt(4) != 0 ? null
        : SoundEvents.BAT_AMBIENT;
  }

  protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
    return SoundEvents.BAT_HURT;
  }

  protected SoundEvent getDeathSound() {
    return SoundEvents.BAT_DEATH;
  }

  /**
   * Returns true if this entity should push and be pushed by other entities when colliding.
   */
  @Override
  public boolean isPushable() {
    return true;
  }

  @Override
  protected void doPush(Entity entityIn) {}

  @Override
  protected void pushEntities() {}

  /**
   * Static! Non-inherited! Create a map of attributes. Called from {@link SetupManager}.
   */
  public static AttributeModifierMap.MutableAttribute getEntityAttributes() {
    return MobEntity.createMobAttributes() //
        .add(Attributes.MAX_HEALTH, 3.0D)
        .add(Attributes.FLYING_SPEED, 2.0f)
        .add(Attributes.ATTACK_DAMAGE, 1.0f)
        .add(Attributes.ATTACK_SPEED, 5.0f)
        .add(Attributes.FOLLOW_RANGE, 64.0f);
  }

  public boolean getIsBatHanging() {
    return (this.entityData.get(HANGING) & 1) != 0;
  }

  public void setIsBatHanging(boolean isHanging) {
    byte b0 = this.entityData.get(HANGING);
    if (isHanging) {
      this.entityData.set(HANGING, (byte) (b0 | 1));
    } else {
      this.entityData.set(HANGING, (byte) (b0 & -2));
    }

  }

  /**
   * Called to update the entity's position/logic.
   */
  @Override
  public void tick() {
    super.tick();

    if (this.isSunBurnTick() && this.isAlive()) {
      this.setSecondsOnFire(2);
    }

    if (this.getIsBatHanging()) {
      this.setDeltaMovement(Vector3d.ZERO);
      this.setPosRaw(this.getX(),
          (double) MathHelper.floor(this.getY()) + 1.0D - (double) this.getBbHeight(),
          this.getZ());
    } else {
      this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D));
    }

  }

  @Override
  protected void registerGoals() {
    // MagiksMostEvile.LOGGER.debug("Registering goals for new vampire bat!");
    this.goalSelector.addGoal(1, new VampireBatBiteGoal(this));
    this.goalSelector.addGoal(19, new VampireBatHangGoal(this));
    this.goalSelector.addGoal(20, new VampireBatFlapGoal(this));

    this.targetSelector.addGoal(1,
        (new HurtByTargetGoal(this)).setAlertOthers(VampireBatEntity.class));
    this.targetSelector.addGoal(2, new VampireBatNearestAttackableTargetGoal<WanderingTraderEntity>(
        this, WanderingTraderEntity.class, false));
    this.targetSelector.addGoal(2, new VampireBatNearestAttackableTargetGoal<TraderLlamaEntity>(
        this, TraderLlamaEntity.class, false));
    this.targetSelector.addGoal(3,
        new VampireBatNearestAttackableTargetGoal<PlayerEntity>(this, PlayerEntity.class, false));
    this.targetSelector.addGoal(4, new VampireBatNearestAttackableTargetGoal<VillagerEntity>(this,
        VillagerEntity.class, false));
    this.targetSelector.addGoal(5,
        new VampireBatNearestAttackableTargetGoal<CowEntity>(this, CowEntity.class, false));
    this.targetSelector.addGoal(5,
        new VampireBatNearestAttackableTargetGoal<SheepEntity>(this, SheepEntity.class, false));
    this.targetSelector.addGoal(5,
        new VampireBatNearestAttackableTargetGoal<PigEntity>(this, PigEntity.class, false));
    this.targetSelector.addGoal(5,
        new VampireBatNearestAttackableTargetGoal<ChickenEntity>(this, ChickenEntity.class, false));
    this.targetSelector.addGoal(6,
        new VampireBatNearestAttackableTargetGoal<WolfEntity>(this, WolfEntity.class, false));
  }

  @Override
  public Random getRandom() {
    return random;
  }

  /**
   * AKA. Can this entity push other entities?
   */
  @Override
  protected boolean isMovementNoisy() {
    return true;
  }

  /**
   * How much damage should this entity take on falling?
   */
  @Override
  public boolean causeFallDamage(float distance, float damageMultiplier) {
    return false;
  }

  /**
   * Not really necessary as bats can't fall.
   */
  @Override
  protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {

  }

  /**
   * Return whether this entity should NOT trigger a pressure plate or a tripwire.
   */
  @Override
  public boolean isIgnoringBlockTriggers() {
    return true;
  }

  /**
   * Called when the entity is attacked.
   */
  @Override
  public boolean hurt(DamageSource source, float amount) {
    if (this.isInvulnerableTo(source)) {
      return false;
    } else {
      if (!this.level.isClientSide && this.getIsBatHanging()) {
        this.setIsBatHanging(false);
      }

      return super.hurt(source, amount);
    }
  }

  /**
   * (abstract) Protected helper method to read subclass entity data from NBT.
   */
  @Override
  public void readAdditionalSaveData(CompoundNBT compound) {
    super.readAdditionalSaveData(compound);
    this.entityData.set(HANGING, compound.getByte("BatFlags"));
  }

  @Override
  public void addAdditionalSaveData(CompoundNBT compound) {
    super.addAdditionalSaveData(compound);
    compound.putByte("BatFlags", this.entityData.get(HANGING));

  }

  @Override
  protected ResourceLocation getDefaultLootTable() {
    // return LOOT_TABLE;
    return super.getLootTable();
  }

  /**
   * Returns whether a bat can spawn here. Checks Y and light level, with increased spawns near
   * Halloween.
   * 
   * TODO Implement this! Check call heirarchy for usage example.
   * 
   * @param vampireBat
   * @param world
   * @param reason
   * @param spawnPos
   * @param random
   * @return
   */
  @SuppressWarnings("deprecation")
  public static boolean checkBatSpawnRules(EntityType<BatEntity> vampireBat, IWorld world, SpawnReason reason, BlockPos spawnPos, Random random) {
    if (spawnPos.getY() >= world.getSeaLevel()) {
      return false;
    } else {
      int i = world.getLightEmission(spawnPos);
      int j = 4;
      if (isNearHalloween()) {
        j = 7;
      } else if (random.nextBoolean()) {
        return false;
      }

      return i > random.nextInt(j) ? false : checkMobSpawnRules(vampireBat, world, reason, spawnPos, random);
    }
  }

  @Override
  public boolean checkSpawnRules(IWorld world, SpawnReason reason) {
    return super.checkSpawnRules(world, reason);
  }

  private static boolean isNearHalloween() {
    LocalDate localdate = LocalDate.now();
    int i = localdate.get(ChronoField.DAY_OF_MONTH);
    int j = localdate.get(ChronoField.MONTH_OF_YEAR);
    return j == 10 && i >= 20 || j == 11 && i <= 3;
  }

  @Override
  protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
    return sizeIn.height / 2.0F;
  }

  /**
   * Extends {@link FlyingMovementController}.
   * 
   * @author GenElectrovise 5 Jun 2020
   */
  class VampireBatMoveHelperController extends FlyingMovementController {

    /**
     * @param vampireBat
     * @param unknownInt
     * @param unknownBool
     */
    public VampireBatMoveHelperController(MobEntity vampireBat, int unknownInt,
        boolean unknownBool) {
      super(vampireBat, unknownInt, unknownBool);
    }

  }

  /**
   * @return
   */
  public List<VampireBatEntity> batsWithinArea(double radius) {
    AxisAlignedBB bounds =
        new AxisAlignedBB(this.getX() + radius, this.getY() + radius, this.getZ() + radius,
            this.getX() - radius, this.getY() - radius, this.getZ() - radius);
    return this.level.getEntitiesOfClass(VampireBatEntity.class, bounds);
  }

}
