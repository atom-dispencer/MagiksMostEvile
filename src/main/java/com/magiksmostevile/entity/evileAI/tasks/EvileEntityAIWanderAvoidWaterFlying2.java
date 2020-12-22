package com.magiksmostevile.entity.evileAI.tasks;

import java.util.Random;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EvileEntityAIWanderAvoidWaterFlying2 extends EntityAIBase
{
    private final EntityCreature creature;
    private BlockPos targetPos;
    private int creatureX;
    private int creatureY;
    private int creatureZ;
    private double movePosX;
    private double movePosY;
    private double movePosZ;
    private final double speed;
    private Random rand = new Random();

    public EvileEntityAIWanderAvoidWaterFlying2(EntityCreature creature, double speedIn)
    {
        this.creature = creature;
        this.speed = speedIn;
        this.setMutexBits(1);
        
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	this.creatureX = creature.getPosition().getX();
    	this.creatureY = creature.getPosition().getY();
    	this.creatureZ = creature.getPosition().getZ();
    	
    	int max = 10;
    	int min = -10;
    	
        this.targetPos = new BlockPos(creatureX +  rand.nextInt(max - min) + min , creatureY + rand.nextInt(max - min) + min , creatureZ + rand.nextInt(max - min) + min);

        if (this.targetPos == null)
        {
            return false;
        }
        else
        {
            Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockTowards(this.creature, 16, 7, new Vec3d(this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ()));

            if (vec3d == null)
            {
                return false;
            }
            else
            {
                this.movePosX = vec3d.x;
                this.movePosY = (vec3d.y) + rand.nextInt(max - min) + min;
                this.movePosZ = vec3d.z;
                return true;
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        return !this.creature.getNavigator().noPath();
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
        this.targetPos = null;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.creature.getNavigator().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.speed);
    }
}