package com.magiksmostevile.equipment.DoomStaff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityDoom extends EntityThrowable
{ 
    public EntityDoom(World world)
    {
        super(world);
    }
 
    public EntityDoom(World world, EntityLivingBase entity)
    {
        super(world, entity);
    }
 
    private void explode()
    {
        worldObj.createExplosion(this, posX, posY, posZ, 100F, true);
 
        setDead();
    }
    @Override
    public void onUpdate()
    {
        super.onUpdate();
        if (ticksExisted > 400)
        {
            explode();
        }
 
        for (int i = 0; i < 10; i++)
        {
            double x = (double)(rand.nextInt(10) - 5) / 8.0D;
            double y = (double)(rand.nextInt(10) - 5) / 8.0D;
            double z = (double)(rand.nextInt(10) - 5) / 8.0D;
            worldObj.spawnParticle("fireworksSpark", posX, posY, posZ, x, y, z);
        }
    }
 
    @Override
    protected float getGravityVelocity()
    {
        return 0.005F;
    }
 
    @Override
    public void onImpact(MovingObjectPosition movingObjectPosition)
    {
        explode();
    }
}