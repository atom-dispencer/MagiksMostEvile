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
package genelectrovise.magiksmostevile.entity.boss.kitty_the_kraken.squid_missile;

import java.util.function.Function;
import com.google.common.primitives.Doubles;
import genelectrovise.magiksmostevile.core.SetupManager;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;

/**
 * {@link FireballEntity} {@link ArrowRenderer} {@link SquidEntity} {@link ArrowEntity}
 * 
 * @author GenElectrovise
 *
 */
public class SquidMissileEntity extends MobEntity {

  private boolean explosive = false;
  private int ticksUntilIgnition = 0;

  private Vector3d direction = new Vector3d(0, 0, 0);

  public SquidMissileEntity(EntityType<? extends MobEntity> type, World worldIn) {
    super(type, worldIn);
  }

  public static final int EXPLOSION_POWER = 2;
  private static final int MAX_PARTICLES = 50;

  /**
   * Makes a salted double. 234 + (0.366 * (4 + 0.234)) = 235.549644
   */
  Function<Double, Double> positionSalter = (input) -> {
    final int RANGE = 5;
    return input + (rand.nextDouble() * (rand.nextInt(RANGE) + rand.nextDouble()));
  };

  /**
   * Makes a salted double. Random double, Contrain to RANGE, Subtract half the range
   */
  Function<Double, Double> velocitySalter = (input) -> {
    final double RANGE = 0.5;
    return Doubles.constrainToRange(rand.nextDouble(), 0, RANGE) - (RANGE / 2);
  };

  /**
   * Static! Non-inherited! Create a map of attributes. Called from {@link SetupManager}.
   */
  public static AttributeModifierMap.MutableAttribute getEntityAttributes() {
    return MobEntity.func_233666_p_() //
        .createMutableAttribute(Attributes.MAX_HEALTH, 3.0D)
        .createMutableAttribute(Attributes.FLYING_SPEED, 2.0f);
  }

  @Override
  public CompoundNBT serializeNBT() {
    CompoundNBT superNbt = super.serializeNBT();
    CompoundNBT squidNbt = new CompoundNBT();
    CompoundNBT directionNbt = new CompoundNBT();

    squidNbt.putInt("ticksUntilIgnition", ticksUntilIgnition);
    squidNbt.putBoolean("explosive", explosive);

    directionNbt.putDouble("x", direction.x);
    directionNbt.putDouble("y", direction.y);
    directionNbt.putDouble("z", direction.z);

    squidNbt.put("Direction", directionNbt);
    superNbt.put("SquidMissileData", squidNbt);
    return superNbt;
  }

  @Override
  public void deserializeNBT(CompoundNBT nbt) {
    super.deserializeNBT(nbt);
    CompoundNBT squidNbt = nbt.getCompound("SquidMissileData");
    CompoundNBT directionNbt = squidNbt.getCompound("Direction");

    double x = directionNbt.getDouble("x");
    double y = directionNbt.getDouble("y");
    double z = directionNbt.getDouble("z");
    setDirection(new Vector3d(x, y, z));

    this.setExplosive(squidNbt.getBoolean("explosive"));
    this.setTicksUntilIgnition(squidNbt.getInt("ticksUntilIgnition"));
  }

  @Override
  public void tick() {
    super.tick();

    handleMissileMovement();
  }

  @Override
  public void livingTick() {
    super.livingTick();

    checkIgnitionTick();

    checkExplosions();
  }

  private void handleMissileMovement() {

    float speed = (float) this.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue();

    // Position
    Vector3d currentMotion = this.getMotion();
    double newPosX = this.getPosX() + currentMotion.x;
    double newPosY = this.getPosY() + currentMotion.y;
    double newPosZ = this.getPosZ() + currentMotion.z;
    this.setPosition(newPosX, newPosY, newPosZ);

    // Direction
    this.setMotion(currentMotion.add(new Vector3d(direction.x * speed, direction.y * speed, direction.z * speed)).scale(0.01));
  }

  /**
   * Check the ticks until this missile is rearmed, and re-arm if time is 0.
   */
  private void checkIgnitionTick() {
    if (ticksUntilIgnition > 0) {
      ticksUntilIgnition--;
      return;
    }

    setExplosive(true);
  }

  /**
   * Check if this entity is colliding, and explode if needed.
   */
  private void checkExplosions() {
    if (!isNotColliding(this.world) && explosive) {

      // Server
      if (!this.world.isRemote) {
        ServerWorld serverWorld = (ServerWorld) world;

        // Get the griefing event caused by this
        boolean flag = ForgeEventFactory.getMobGriefingEvent(this.world, this);

        // flag = (causesFire), (mode ? DESTROY : NONE)
        serverWorld.createExplosion((Entity) null, this.getPosX(), this.getPosY(), this.getPosZ(), (float) SquidMissileEntity.EXPLOSION_POWER, flag,
            flag ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);

        spawnInkBySendingPacketFromServer(serverWorld, MAX_PARTICLES);

        this.remove();
        return;
      }
    }
  }

  public void spawnInkBySendingPacketFromServer(ServerWorld world, int particles) {

    for (int spawned = 0; spawned < particles; spawned++) {

      // Get the position of this
      double pX = positionSalter.apply(this.getPosX());
      double pY = positionSalter.apply(this.getPosY());
      double pZ = positionSalter.apply(this.getPosZ());

      // Clamp each in 0.0-0.5, then take 0.25 to give a possible negative
      double vX = velocitySalter.apply(rand.nextDouble());
      double vY = velocitySalter.apply(rand.nextDouble());
      double vZ = velocitySalter.apply(rand.nextDouble());

      // Like SquidEntity (if this works its easier than making a custom packet handler)
      // Maintains sided-ness because this method forces the server to send packets to the clients
      world.spawnParticle(ParticleTypes.SQUID_INK, pX, pY, pZ, 1, vX, vY, vZ, 1);
    }
  }

  // Get and set

  public boolean isExplosive() {
    return explosive;
  }

  public void setExplosive(boolean explosive) {
    this.explosive = explosive;
  }

  public void setTicksUntilIgnition(int i) {
    this.ticksUntilIgnition = i;
  }

  public Vector3d getDirection() {
    return direction;
  }

  public void setDirection(Vector3d direction) {
    this.direction = direction;
  }

}
