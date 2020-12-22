package com.magiksmostevile.entity.evileAI.tasks;

import java.sql.Time;
import java.util.Random;

import com.magiksmostevile.entity.EntityVampireBat;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EvileAIGetOffTheGround extends EntityAIBase {
	private EntityCreature creature;

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
	public EvileAIGetOffTheGround(EntityCreature creatureIn) {
		this.creature = creatureIn;
		
		setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		if(creature.onGround) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void startExecuting() {
	}

	@Override
	public boolean shouldContinueExecuting() {
		if(creature.onGround) {
			moveUpwards();
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void resetTask() {
	}
	
	private void moveUpwards() {
		BlockPos moveTo = new BlockPos(creature.posX, (creature.posY) + 2, creature.posZ);
		creature.addVelocity(0, 1.5, 0);
	}

}
