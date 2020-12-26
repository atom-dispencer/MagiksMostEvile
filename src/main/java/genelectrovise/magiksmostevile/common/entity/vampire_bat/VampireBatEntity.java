/**
 * 
 */
package genelectrovise.magiksmostevile.common.entity.vampire_bat;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.core.SetupManager;
import genelectrovise.magiksmostevile.common.entity.goal.VampireBatBiteGoal;
import genelectrovise.magiksmostevile.common.entity.goal.VampireBatFlapGoal;
import genelectrovise.magiksmostevile.common.entity.goal.VampireBatHangGoal;
import genelectrovise.magiksmostevile.common.entity.goal.VampireBatNearestAttackableTargetGoal;
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
 * @author GenElectrovise 1 Jun 2020
 */
public class VampireBatEntity extends MonsterEntity {
  private static final DataParameter<Byte> HANGING =
      EntityDataManager.createKey(VampireBatEntity.class, DataSerializers.BYTE);
  public static final EntityPredicate entityPredicate =
      new EntityPredicate().setDistance(4.0D).allowFriendlyFire();
  public BlockPos spawnPosition;

  public static final ResourceLocation LOOT_TABLE =
      new ResourceLocation(MagiksMostEvile.MODID, "loot_tab/vampire_bat");

  public static final int REINFORCEMENT_CHANCE = 2;
  public static final int REINFORCEMENT_COOLDOWN = 100;
  public static final int MAX_REINFORCEMENTS = 10;
  public static final int REINFORCEMENT_DETECTION_RADIUS = 15;
  public static final int REINFORCEMENT_CALLING_RADIUS = 25;
  public static final int MINIMUM_REINFORCEMENTS = 5;

  public VampireBatEntity(EntityType<? extends VampireBatEntity> entityType, World world) {
    super(entityType, world);
    this.setIsBatHanging(false);

    this.moveController = new VampireBatMoveHelperController(this, 60, true);
    this.navigator = new FlyingPathNavigator(this, this.world);

    navigator.setSpeed(this.getAttribute(Attributes.FLYING_SPEED).getValue());
    navigator.setCanSwim(false);
  }

  protected void registerData() {
    super.registerData();
    this.dataManager.register(HANGING, (byte) 0);
  }

  /**
   * Returns the volume for the sounds this mob makes.
   */
  protected float getSoundVolume() {
    return 0.1F;
  }

  /**
   * Gets the pitch of living sounds in living entities.
   */
  protected float getSoundPitch() {
    return super.getSoundPitch() * 0.95F;
  }

  @Nullable
  public SoundEvent getAmbientSound() {
    return this.getIsBatHanging() && this.rand.nextInt(4) != 0 ? null
        : SoundEvents.ENTITY_BAT_AMBIENT;
  }

  protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
    return SoundEvents.ENTITY_BAT_HURT;
  }

  protected SoundEvent getDeathSound() {
    return SoundEvents.ENTITY_BAT_DEATH;
  }

  /**
   * Returns true if this entity should push and be pushed by other entities when colliding.
   */
  public boolean canBePushed() {
    return true;
  }

  protected void collideWithEntity(Entity entityIn) {}

  protected void collideWithNearbyEntities() {}

  /**
   * Static! Non-inherited! Create a map of attributes. Called from {@link SetupManager}.
   */
  public static AttributeModifierMap.MutableAttribute getEntityAttributes() {
    return MobEntity.func_233666_p_() //
        .createMutableAttribute(Attributes.MAX_HEALTH, 3.0D)
        .createMutableAttribute(Attributes.FLYING_SPEED, 2.0f)
        .createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.0f)
        .createMutableAttribute(Attributes.ATTACK_SPEED, 5.0f)
        .createMutableAttribute(Attributes.FOLLOW_RANGE, 64.0f);
  }

  public boolean getIsBatHanging() {
    return (this.dataManager.get(HANGING) & 1) != 0;
  }

  public void setIsBatHanging(boolean isHanging) {
    byte b0 = this.dataManager.get(HANGING);
    if (isHanging) {
      this.dataManager.set(HANGING, (byte) (b0 | 1));
    } else {
      this.dataManager.set(HANGING, (byte) (b0 & -2));
    }

  }

  /**
   * Called to update the entity's position/logic.
   */
  public void tick() {
    super.tick();

    if (this.isInDaylight() && this.isAlive()) {
      this.setFire(2);
    }

    if (this.getIsBatHanging()) {
      this.setMotion(Vector3d.ZERO);
      this.setRawPosition(this.getPosX(),
          (double) MathHelper.floor(this.getPosY()) + 1.0D - (double) this.getHeight(),
          this.getPosZ());
    } else {
      this.setMotion(this.getMotion().mul(1.0D, 0.6D, 1.0D));
    }

  }

  @Override
  protected void registerGoals() {
    // MagiksMostEvile.LOGGER.dev("Registering goals for new vampire bat!");
    this.goalSelector.addGoal(1, new VampireBatBiteGoal(this));
    this.goalSelector.addGoal(19, new VampireBatHangGoal(this));
    this.goalSelector.addGoal(20, new VampireBatFlapGoal(this));

    this.targetSelector.addGoal(1,
        (new HurtByTargetGoal(this)).setCallsForHelp(VampireBatEntity.class));
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

  protected void updateAITasks() {
    super.updateAITasks();
  }

  public Random getRandom() {
    return rand;
  }

  /**
   * AKA. Can this entity push other entities?
   */
  protected boolean canTriggerWalking() {
    return true;
  }

  /**
   * How much damage should this entity take on falling?
   */
  public boolean onLivingFall(float distance, float damageMultiplier) {
    return false;
  }

  /**
   * Not really necessary as bats can't fall.
   */
  protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {

  }

  /**
   * Return whether this entity should NOT trigger a pressure plate or a tripwire.
   */
  public boolean doesEntityNotTriggerPressurePlate() {
    return true;
  }

  /**
   * Called when the entity is attacked.
   */
  public boolean attackEntityFrom(DamageSource source, float amount) {
    if (this.isInvulnerableTo(source)) {
      return false;
    } else {
      if (!this.world.isRemote && this.getIsBatHanging()) {
        this.setIsBatHanging(false);
      }

      return super.attackEntityFrom(source, amount);
    }
  }

  /**
   * (abstract) Protected helper method to read subclass entity data from NBT.
   */
  public void readAdditional(CompoundNBT compound) {
    super.readAdditional(compound);
    this.dataManager.set(HANGING, compound.getByte("BatFlags"));
  }

  public void writeAdditional(CompoundNBT compound) {
    super.writeAdditional(compound);
    compound.putByte("BatFlags", this.dataManager.get(HANGING));
  }

  @Override
  protected ResourceLocation getLootTable() {
    // return LOOT_TABLE;
    return super.getLootTable();
  }

  /**
   * Returns whether a bat can spawn here. Checks Y and light level, with increased spawns near
   * Halloween.
   * 
   * @param vampireBat
   * @param world
   * @param reason
   * @param spawnPos
   * @param random
   * @return
   */
  public static boolean func_223369_b(EntityType<BatEntity> vampireBat, IWorld world,
      SpawnReason reason, BlockPos spawnPos, Random random) {
    if (spawnPos.getY() >= world.getSeaLevel()) {
      return false;
    } else {
      int i = world.getLight(spawnPos);
      int j = 4;
      if (isNearHalloween()) {
        j = 7;
      } else if (random.nextBoolean()) {
        return false;
      }

      return i > random.nextInt(j) ? false
          : canSpawnOn(vampireBat, world, reason, spawnPos, random);
    }
  }

  private static boolean isNearHalloween() {
    LocalDate localdate = LocalDate.now();
    int i = localdate.get(ChronoField.DAY_OF_MONTH);
    int j = localdate.get(ChronoField.MONTH_OF_YEAR);
    return j == 10 && i >= 20 || j == 11 && i <= 3;
  }

  protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
    return sizeIn.height / 2.0F;
  }

  public boolean isInActiveLightLevel() {
    return this.world.getLightFor(LightType.SKY, getPosition()) < 8 || this.isInDaylight() == false;
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
        new AxisAlignedBB(this.getPosX() + radius, this.getPosY() + radius, this.getPosZ() + radius,
            this.getPosX() - radius, this.getPosY() - radius, this.getPosZ() - radius);
    return world.getEntitiesWithinAABB(VampireBatEntity.class, bounds);
  }

}
