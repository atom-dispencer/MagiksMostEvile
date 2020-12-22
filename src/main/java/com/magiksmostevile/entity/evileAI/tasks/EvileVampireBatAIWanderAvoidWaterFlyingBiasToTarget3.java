package com.magiksmostevile.entity.evileAI.tasks;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.magiksmostevile.EvileLog;
import com.magiksmostevile.EvileLog.EnumLogLevel;
import com.magiksmostevile.entity.EntityVampireBat;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EvileVampireBatAIWanderAvoidWaterFlyingBiasToTarget3 extends EntityAIBase {
	private BlockPos spawnPosition;
	private Random rand = new Random();
	private int chanceOutOf;
	private EntityVampireBat vampireBatCreature;
	private BlockPos randomPos;

	// private List<BlockPos> posArray = new ArrayList<BlockPos>();

	double d0;
	double d1;
	double d2;
	float f;
	float f1;
	private double angleToAttackTargetDeg;

	/**
	 * Magiksmostevile version of EntityAIWanderAvoidWaterFlying -- customisable execution chance and idle time included!
	 * 
	 * @param creature              this
	 * @param speed                 speed to wander at
	 * @param chanceIn              chance that the entity will wander this tick (smaller number more likely) -- ( chanceIn / chanceOutOfIn )
	 * @param minIdleTimeIn         minimum time the entity can be idle for before moving
	 * @param chanceOutOfIn         the denominator for deciding if the task should execute ( chanceIn / chanceOutOfIn )
	 * @param isTaskInterruptibleIn Can the task be interrupted? Recommended to be true.
	 */
	public EvileVampireBatAIWanderAvoidWaterFlyingBiasToTarget3(EntityVampireBat creatureIn, double speedIn, int chanceOutOfIn) {
		this.chanceOutOf = chanceOutOfIn;
		this.vampireBatCreature = creatureIn;
		// this.spawnPosition = creature.spawn
		this.spawnPosition = new BlockPos((int) vampireBatCreature.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int) vampireBatCreature.posY + this.rand.nextInt(6) - 2, (int) vampireBatCreature.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
		System.out.println("constructing EvileEntityAIWanderAvoidWaterFlyingBiasToTarget");

		setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		if (this.vampireBatCreature.getAttackTarget() != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void startExecuting() {
		System.out.println("starting execution!");
	}

	@Override
	public boolean shouldContinueExecuting() {
		try {
			generateRandomAngle();
			if (vampireBatCreature.getAttackTarget() != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			EvileLog.logText(true, EnumLogLevel.WARN, "Exception caught! Maybe the bat has no attack target?");
			return false;
		}
	}

	@Override
	public void resetTask() {
		System.out.println("Resetting");
	}

	private void generateRandomAngle() {
		if (this.spawnPosition != null && (!vampireBatCreature.world.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1)) {
			this.spawnPosition = null;
		}

		if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.distanceSq((double) ((int) vampireBatCreature.posX), (double) ((int) vampireBatCreature.posY), (double) ((int) vampireBatCreature.posZ)) < 4.0D) {
			this.spawnPosition = new BlockPos((int) vampireBatCreature.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int) vampireBatCreature.posY + this.rand.nextInt(6) - 2, (int) vampireBatCreature.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
		}

		this.d0 = (double) this.spawnPosition.getX() + 0.5D - vampireBatCreature.posX;
		this.d1 = (double) this.spawnPosition.getY() + 0.1D - vampireBatCreature.posY;
		this.d2 = (double) this.spawnPosition.getZ() + 0.5D - vampireBatCreature.posZ;

		vampireBatCreature.motionX += (Math.signum(d0) * 0.5D - vampireBatCreature.motionX) * 0.10000000149011612D;
		vampireBatCreature.motionY += (Math.signum(d1) * 0.699999988079071D - vampireBatCreature.motionY) * 0.10000000149011612D;
		vampireBatCreature.motionZ += (Math.signum(d2) * 0.5D - vampireBatCreature.motionZ) * 0.10000000149011612D;

		this.f = (float) (MathHelper.atan2(vampireBatCreature.motionZ, vampireBatCreature.motionX) * (180D / Math.PI)) - 90.0F; // Gets the atan2, then converts the radians returned to degrees, then takes 90F, and casts to a float
		this.f1 = MathHelper.wrapDegrees(f - vampireBatCreature.rotationYaw);

		prepTrig();

		if ((vampireBatCreature.rotationYaw += f1) > this.angleToAttackTargetDeg - 30 && (vampireBatCreature.rotationYaw += f1) < this.angleToAttackTargetDeg + 30) {
			System.out.println("Rotation Yaw: " + (vampireBatCreature.rotationYaw += f1));
			moveForward();
		}

	}

	private void moveForward() {
		vampireBatCreature.moveForward = 0.5F;
		vampireBatCreature.rotationYaw += f1;

		System.out.println("f1:" + f1);
		System.out.println("+++++++++++++++++++++++++++++++++++++++");
	}

	private void prepTrig() {
		if (vampireBatCreature.getAttackTarget() != null) {

			BlockPos point3Pos = new BlockPos(vampireBatCreature.getAttackTarget().getPosition().getX(), vampireBatCreature.getPosition().getY(), vampireBatCreature.getPosition().getZ());
			BlockPos vampireBatPos = new BlockPos(vampireBatCreature.getPosition().getX(), vampireBatCreature.getPosition().getY(), vampireBatCreature.getPosition().getZ());
			BlockPos targetPos = new BlockPos(vampireBatCreature.getAttackTarget().getPosition().getX(), vampireBatCreature.getAttackTarget().getPosition().getY(), vampireBatCreature.getAttackTarget().getPosition().getZ());

			// System.out.println("point3Pos: " + point3Pos);
			// System.out.println("batPos: " + vampireBatPos);
			// System.out.println("targetPos: " + targetPos);

			doTrig(point3Pos, vampireBatPos, targetPos);
		}
	}

	private void doTrig(BlockPos point3Pos, BlockPos vampireBatPos, BlockPos targetPos) {
		System.out.println("doing trig");

		double Opposite_targetToPoint3 = vampireBatCreature.getAttackTarget().getDistance(point3Pos.getX(), point3Pos.getY(), point3Pos.getZ());
		double Adjacent_batToPoint3 = vampireBatCreature.getDistance(point3Pos.getX(), point3Pos.getY(), point3Pos.getZ());
		System.out.println("Opp: " + Opposite_targetToPoint3);
		System.out.println("Adj: " + Adjacent_batToPoint3);

		double angleToAttackTargetRad = Math.tan(Opposite_targetToPoint3 / Adjacent_batToPoint3);
		this.angleToAttackTargetDeg = angleToAttackTargetRad * (180 / Math.PI);
		System.out.println("AngleRad: " + angleToAttackTargetRad);
		System.out.println("AngleDeg: " + angleToAttackTargetDeg);

		//angleToAttackTargetDeg = MathHelper.wrapDegrees(angleToAttackTargetDeg);
	}

}
