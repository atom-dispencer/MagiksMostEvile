package com.magiksmostevile.entity.evileAI.tasks;

import java.sql.Time;
import java.util.Random;

import com.magiksmostevile.entity.EntityVampireBat;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EvileVampireBatAIWanderAvoidWaterFlyingBiasToTarget2 extends EntityAIBase {
	private BlockPos spawnPosition;
	private Random rand = new Random();
	private int chanceOutOf;
	private EntityVampireBat vampireBatCreature;
	private BlockPos randomPos;
	private float f1;

	/**
	 * Magiksmostevile version of EntityAIWanderAvoidWaterFlying -- customisable
	 * execution chance and idle time included!
	 * 
	 * @param creature              this
	 * @param speed                 speed to wander at
	 * @param chanceIn              chance that the entity will wander this tick
	 *                              (smaller number more likely) -- ( chanceIn /
	 *                              chanceOutOfIn )
	 * @param minIdleTimeIn         minimum time the entity can be idle for before
	 *                              moving
	 * @param chanceOutOfIn         the denominator for deciding if the task should
	 *                              execute ( chanceIn / chanceOutOfIn )
	 * @param isTaskInterruptibleIn Can the task be interrupted? Recommended to be
	 *                              true.
	 */	
	public EvileVampireBatAIWanderAvoidWaterFlyingBiasToTarget2(EntityVampireBat creatureIn, double speedIn, int chanceOutOfIn) {
		this.chanceOutOf = chanceOutOfIn;
		this.vampireBatCreature = creatureIn;
		//this.spawnPosition = creature.spawn
		this.spawnPosition = new BlockPos((int)vampireBatCreature.posX + this.rand.nextInt(7) - this.rand.nextInt(7), 
				(int)vampireBatCreature.posY + this.rand.nextInt(6) - 2, 
				(int)vampireBatCreature.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
		System.out.println("constructing EvileEntityAIWanderAvoidWaterFlying");

		setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		int randomChance = (rand.nextInt(chanceOutOf) + 1);
		System.out.println("randomChance    = " + randomChance);
		System.out.println("chanceOutOf =  " + chanceOutOf);
		
		if (randomChance == chanceOutOf) {
			System.out.println("shouldExecute has returned true!");
			return true;
		} else {
			System.out.println("Failed randomiser check! Will not start executing!");
			return false;
		}
	}

	@Override
	public void startExecuting() {
		System.out.println("starting execution!");
	}

	@Override
	public boolean shouldContinueExecuting() {
		if (!(rand.nextInt(500) == 1)) {
//		    evaluateDistances();
		    return true;
		} else {
		    return false;
		}
	}

	@Override
	public void resetTask() {
	    System.out.println("Resetting");
	}
	
	private float genRandomPos(EntityVampireBat creature) {
		if (this.spawnPosition != null && (!vampireBatCreature.world.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1))
        {
            this.spawnPosition = null;
        }

        if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.distanceSq((double)((int)vampireBatCreature.posX), (double)((int)vampireBatCreature.posY), (double)((int)vampireBatCreature.posZ)) < 4.0D)
        {
            this.spawnPosition = new BlockPos((int)vampireBatCreature.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int)vampireBatCreature.posY + this.rand.nextInt(6) - 2, (int)vampireBatCreature.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
        }

        double d0 = (double)this.spawnPosition.getX() + 0.5D - vampireBatCreature.posX;
        double d1 = (double)this.spawnPosition.getY() + 0.1D - vampireBatCreature.posY;
        double d2 = (double)this.spawnPosition.getZ() + 0.5D - vampireBatCreature.posZ;
        vampireBatCreature.motionX += (Math.signum(d0) * 0.5D - vampireBatCreature.motionX) * 0.10000000149011612D;
        vampireBatCreature.motionY += (Math.signum(d1) * 0.699999988079071D - vampireBatCreature.motionY) * 0.10000000149011612D;
        vampireBatCreature.motionZ += (Math.signum(d2) * 0.5D - vampireBatCreature.motionZ) * 0.10000000149011612D;
        float f = (float)(MathHelper.atan2(vampireBatCreature.motionZ, vampireBatCreature.motionX) * (180D / Math.PI)) - 90.0F;
        float f1 = MathHelper.wrapDegrees(f - vampireBatCreature.rotationYaw);
        
        return f1;
	}
	
	private void moveToRandomPos(float angle) {
	    vampireBatCreature.moveForward = 0.5F;
	    vampireBatCreature.rotationYaw += angle;
	}
	
	/*
	private void evaluateDistances() {
	    try {
		BlockPos batTargetBlockPos = new BlockPos(vampireBatCreature.getAttackTarget().getPosition());
		double batToAttackTarget = vampireBatCreature.getAttackTarget().getDistance(batTargetBlockPos.getX(), batTargetBlockPos.getY(), batTargetBlockPos.getZ());
		double randToAttackTarget = randomPos.getDistance(xIn, yIn, zIn);
		if(batToAttackTarget < )
		
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	*/

}
