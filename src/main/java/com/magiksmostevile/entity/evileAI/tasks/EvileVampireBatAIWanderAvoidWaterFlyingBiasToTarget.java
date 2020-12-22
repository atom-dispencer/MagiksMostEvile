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

public class EvileVampireBatAIWanderAvoidWaterFlyingBiasToTarget extends EntityAIBase {
    private BlockPos spawnPosition;
    private Random rand = new Random();
    private int chanceOutOf;
    private EntityVampireBat vampireBatCreature;
    private BlockPos randomPos;

    private List<BlockPos> posArray = new ArrayList<BlockPos>();

    double d0;
    double d1;
    double d2;
    float f;
    float f1;

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
    public EvileVampireBatAIWanderAvoidWaterFlyingBiasToTarget(EntityVampireBat creatureIn, double speedIn,
	    int chanceOutOfIn) {
	this.chanceOutOf = chanceOutOfIn;
	this.vampireBatCreature = creatureIn;
	// this.spawnPosition = creature.spawn
	this.spawnPosition = new BlockPos((int) vampireBatCreature.posX + this.rand.nextInt(7) - this.rand.nextInt(7),
		(int) vampireBatCreature.posY + this.rand.nextInt(6) - 2,
		(int) vampireBatCreature.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
	System.out.println("constructing EvileEntityAIWanderAvoidWaterFlyingBiasToTarget");

	setMutexBits(1);
    }
    
    private boolean shouldExecuteRandomiser() {
	if(rand.nextInt(50) == 1) {
	    EvileLog.logText(true, EnumLogLevel.INFO, "Randomiser check succeeded! Starting execution!");
	    return true;
	}
	else {
	    EvileLog.logText(true, EnumLogLevel.INFO, "Failed randomiser check!");
	    return false;
	}
    }

    @Override
    public boolean shouldExecute() {
	if(this.vampireBatCreature.getAttackTarget() != null && shouldExecuteRandomiser()) {
	   return true;
	}
	else { 
	    return false;
	}
    }

    @Override
    public void startExecuting() {
	System.out.println("starting execution!");
    }

    @Override
    public boolean shouldContinueExecuting() {
	generateRandomPos(vampireBatCreature);
	try {
	    if (vampireBatCreature.getAttackTarget() != null /*&& !stoppedMoving*/) {
		return true;
	    } else {
		return false;
	    }
	} catch (Exception e) {
	    EvileLog.logText(true, EnumLogLevel.INFO, "Exception caught! Maybe the bat has no attack target?");
	    return false;
	}
    }

    @Override
    public void resetTask() {
	System.out.println("Resetting");
    }

    private void generateRandomPos(EntityVampireBat creature) {
	if (this.spawnPosition != null
		&& (!vampireBatCreature.world.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1)) {
	    this.spawnPosition = null;
	}

	if (this.spawnPosition == null || this.rand.nextInt(30) == 0
		|| this.spawnPosition.distanceSq((double) ((int) vampireBatCreature.posX),
			(double) ((int) vampireBatCreature.posY), (double) ((int) vampireBatCreature.posZ)) < 4.0D) {
	    this.spawnPosition = new BlockPos(
		    (int) vampireBatCreature.posX + this.rand.nextInt(7) - this.rand.nextInt(7),
		    (int) vampireBatCreature.posY + this.rand.nextInt(6) - 2,
		    (int) vampireBatCreature.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
	}

	this.d0 = (double) this.spawnPosition.getX() + 0.5D - vampireBatCreature.posX;
	this.d1 = (double) this.spawnPosition.getY() + 0.1D - vampireBatCreature.posY;
	this.d2 = (double) this.spawnPosition.getZ() + 0.5D - vampireBatCreature.posZ;
	vampireBatCreature.motionX += (Math.signum(d0) * 0.5D - vampireBatCreature.motionX) * 0.10000000149011612D;
	vampireBatCreature.motionY += (Math.signum(d1) * 0.699999988079071D - vampireBatCreature.motionY)
		* 0.10000000149011612D;
	vampireBatCreature.motionZ += (Math.signum(d2) * 0.5D - vampireBatCreature.motionZ) * 0.10000000149011612D;
	this.f = (float) (MathHelper.atan2(vampireBatCreature.motionZ, vampireBatCreature.motionX) * (180D / Math.PI))
		- 90.0F;
	this.f1 = MathHelper.wrapDegrees(f - vampireBatCreature.rotationYaw);

	this.randomPos = new BlockPos(d0, d1, d2);
	this.posArray.add(vampireBatCreature.getPosition());

	if(evaluateRandomPos()) {
	    vampireBatCreature.moveForward = 0.5F;
	    vampireBatCreature.rotationYaw += f1;
	}
    }

    private boolean evaluateRandomPos() {
	try {
	    if(vampireBatCreature.getAttackTarget() == null) {
		throw (new NullPointerException("vampire_bat has no target! Don't panic!"));
	    }
	    
	    double batToTargetDist = this.vampireBatCreature.getDistanceSq(vampireBatCreature.getAttackTarget());
	    double randomPosToTargetDist = this.vampireBatCreature.getAttackTarget().getDistanceSq(this.randomPos);
	    
	    if (randomPosToTargetDist < batToTargetDist) {
		return true;
	    } else {
		return false;
	    }
	}
	catch (NullPointerException e){
	    EvileLog.logText(true, EnumLogLevel.INFO, "NullPointerException caught! Will not print stacktrace as is likely caused by vampire_bat lacking a target!");
	}
	catch (Exception e) {
	    EvileLog.logText(true, EnumLogLevel.INFO,
		    "Oops! Looks like something went wrong when evaluating a vampire_bat's randomPos! If it's a nulled value, that should be fixed by next iteration!");
	    e.printStackTrace();
	}
	return false;
    }

    private boolean stoppedMoving() {
	int i = 0;
	while (i <= posArray.size()) {

	}

	if (posArray.size() > 49) {
	    posArray.clear();
	    return true;
	}
	// TODO Add return statement
	return false;
    }

}
