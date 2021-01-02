package genelectrovise.magiksmostevile.common.entity.boss.the_kraken.squid_missile;

import java.util.function.Function;
import com.google.common.primitives.Doubles;
import genelectrovise.magiksmostevile.common.core.SetupManager;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.particles.ParticleTypes;
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
  
  public float squidPitch;
  public float prevSquidPitch;
  public float squidYaw;
  public float prevSquidYaw;
  public float squidRotation;
  public float prevSquidRotation;
  public float tentacleAngle;
  public float lastTentacleAngle;
  private float rotationVelocity;
  private int tentacleTick = 0;

  public SquidMissileEntity(EntityType<? extends MobEntity> type, World worldIn) {
    super(type, worldIn);
  }

  public static final int EXPLOSION_POWER = 2;
  private static final int MAX_PARTICLES = 20;

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
  public void livingTick() {
    
    this.prevSquidPitch = this.squidPitch;
    this.prevSquidYaw = this.squidYaw;
    this.prevSquidRotation = this.squidRotation;
    this.lastTentacleAngle = this.tentacleAngle;
    this.squidRotation += this.rotationVelocity;

    if (!isNotColliding(this.world)) {
      
      // Server
      if (!this.world.isRemote) {
        ServerWorld serverWorld = (ServerWorld) world;

        // Get the griefing event caused by this
        boolean flag = ForgeEventFactory.getMobGriefingEvent(this.world, this);

        // flag = (causesFire), (mode ? DESTROY : NONE)
        serverWorld.createExplosion((Entity) null, this.getPosX(), this.getPosY(), this.getPosZ(), (float) SquidMissileEntity.EXPLOSION_POWER, flag,
            flag ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);

        this.remove();
        return;
      }

      // Client
      if (this.world.isRemote) {
        ClientWorld clientWorld = (ClientWorld) world;

        for (int spawned = 0; spawned < MAX_PARTICLES; spawned++) {

          // Get the position of this
          double pX = positionSalter.apply(this.getPosX());
          double pY = positionSalter.apply(this.getPosY());
          double pZ = positionSalter.apply(this.getPosZ());

          // Clamp each in 0.0-0.5, then take 0.25 to give a possible negative
          double vX = velocitySalter.apply(rand.nextDouble());
          double vY = velocitySalter.apply(rand.nextDouble());
          double vZ = velocitySalter.apply(rand.nextDouble());

          clientWorld.addOptionalParticle(ParticleTypes.SQUID_INK, true, pX, pY, pZ, vX, vY, vZ);
        }
      }
    }
  }

  public double getTentacleAngle() {
    return tentacleAngle;
  }

  public int getTentacleTick() {
    return tentacleTick;
  }

}
