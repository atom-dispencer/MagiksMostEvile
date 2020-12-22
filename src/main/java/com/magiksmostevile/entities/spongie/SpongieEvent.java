package com.magiksmostevile.entities.spongie;

import cpw.mods.fml.common.eventhandler.Event.HasResult;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent;

/**
 * SpongieEvent is fired whenever a Spongie is spawned for aid.
 * If a method utilizes this {@link Event} as its parameter, the method will 
 * receive every child event of this class.
 * 
 * All children of this event are fired on the {@link MinecraftForge#EVENT_BUS}.
 **/
public class SpongieEvent extends EntityEvent {

    public SpongieEvent(EntitySpongie entity)
    {
        super(entity);
    }

    public EntitySpongie getSummoner()
    {
        return (EntitySpongie) entity;
    }
    
    /**
     * SummonAidEvent is fired when a Spongie Entity is summoned.
     * This event is fired whenever a Spongie Entity is summoned in 
     * EntitySpongie#attackEntityFrom(DamageSource, float).
     * 
     * This event is fired via the {@link ForgeHooks#fireSpongieSummonAid(EntitySpongie, World, int, int, int, EntityLivingBase, double)}.
     * 
     * {@link #customSummonedAid} remains null, but can be populated with a custom EntitySpongie which will be spawned.
     * {@link #world} contains the world that this summoning is occurring in.
     * {@link #x} contains the x-coordinate at which this summoning event is occurring. 
     * {@link #y} contains the y-coordinate at which this summoning event is occurring. 
     * {@link #z} contains the z-coordinate at which this summoning event is occurring. 
     * {@link #attacker} contains the living Entity that attacked and caused this event to fire.
     * {@link #summonChance} contains the likelihood that a Spongie would successfully be summoned.
     * 
     * This event is not {@link Cancelable}.
     * 
     * This event has a result. {@link HasResult}
     * {@link Result#ALLOW} Spongie is summoned.
     * {@link Result#DENY} Spongie is not summoned.
     * 
     * This event is fired on the {@link MinecraftForge#EVENT_BUS}.
     **/
    @HasResult
    public static class SpongieSummonAidEvent extends SpongieEvent {
        /**
         * Populate this field to have a custom Spongie instead of a normal Spongie summoned
         */
        public EntitySpongie customSummonedAid;
        
        public final World world;
        public final int x;
        public final int y;
        public final int z;
        public final EntityLivingBase attacker;
        public final double summonChance;
        
        public SpongieSummonAidEvent(EntitySpongie entity, World world, int x, int y, int z, EntityLivingBase attacker, double summonChance)
        {
            super(entity);
            this.world = world;
            this.x = x;
            this.y = y;
            this.z = z;
            this.attacker = attacker;
            this.summonChance = summonChance;
        }
        
    }
}