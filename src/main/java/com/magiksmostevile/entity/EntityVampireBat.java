package com.magiksmostevile.entity;

import java.util.Calendar;

import javax.annotation.Nullable;

import com.magiksmostevile.EvileLog;
import com.magiksmostevile.EvileLog.EnumLogLevel;
import com.magiksmostevile.entity.evileAI.tasks.EvileVampireBatAIWanderAvoidWaterFlyingBiasToTarget3;
import com.magiksmostevile.handler.LootTableHandler;
import com.magiksmostevile.init.EvileItems;

import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityVampireBat extends EntityMob
{
	private static final DataParameter<Byte> HANGING = EntityDataManager.<Byte>createKey(EntityVampireBat.class, DataSerializers.BYTE);
	/** Coordinates of where the bat spawned. */
	private BlockPos spawnPosition;

	public EntityVampireBat(World worldIn)
	{
		super(worldIn);
		this.setSize(0.5F, 0.9F);
		this.setIsBatHanging(true);
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(HANGING, Byte.valueOf((byte)0));
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume()
	{
		return 0.1F;
	}

	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	protected float getSoundPitch()
	{
		return super.getSoundPitch() * 0.95F;
	}

	@Nullable
	public SoundEvent getAmbientSound()
	{
		return this.getIsBatHanging() && this.rand.nextInt(4) != 0 ? null : SoundEvents.ENTITY_BAT_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return SoundEvents.ENTITY_BAT_HURT;
	}

	protected SoundEvent getDeathSound()
	{
		return SoundEvents.ENTITY_BAT_DEATH;
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	public boolean canBePushed()
	{
		return true;
	}

	protected void collideWithEntity(Entity entityIn)
	{
	}

	protected void collideWithNearbyEntities()
	{
	}

	public boolean getIsBatHanging()
	{
		return (((Byte)this.dataManager.get(HANGING)).byteValue() & 1) != 0;
	}

	public void setIsBatHanging(boolean isHanging)
	{
		byte b0 = ((Byte)this.dataManager.get(HANGING)).byteValue();

		if (isHanging)
		{
			this.dataManager.set(HANGING, Byte.valueOf((byte)(b0 | 1)));
		}
		else
		{
			this.dataManager.set(HANGING, Byte.valueOf((byte)(b0 & -2)));
		}
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		super.onUpdate();

		if (this.getIsBatHanging())
		{
			this.motionX = 0.0D;
			this.motionY = 0.0D;
			this.motionZ = 0.0D;
			this.posY = (double)MathHelper.floor(this.posY) + 1.0D - (double)this.height;
		}
		else
		{
			this.motionY *= 0.6000000238418579D;
		}
	}

	/**
	 * Doesn't appear to update AI tasks, but rather sets whether the bat is hanging (Calls super.updateAITasks in EntityLiving which contains nothing)
	 */
	protected void updateAITasks()
	{
		super.updateAITasks();
		BlockPos blockpos = new BlockPos(this);
		BlockPos blockpos1 = blockpos.up();

		if(this.onGround) {
			this.moveRelative(0F, 1.5F, 0.5F, 0F);
		}
		
		if (this.getIsBatHanging())
		{
		    if(rand.nextInt(1000) == 1) {
			if (!world.isRemote) {
			    Entity entity = new EntityItem(world, this.posX, this.posY, this.posZ,
				    new ItemStack(EvileItems.VAMPIRE_BAT_GUANO, 1));
			    world.spawnEntity(entity);
			}
		    }
			if (this.world.getBlockState(blockpos1).isNormalCube())
			{
				if (this.rand.nextInt(200) == 0)
				{
					this.rotationYawHead = (float)this.rand.nextInt(360);
				}

				if (this.world.getNearestPlayerNotCreative(this, 4.0D) != null)
				{
					this.setIsBatHanging(false);
					this.world.playEvent((EntityPlayer)null, 1025, blockpos, 0);
				}
			}
			else
			{
				this.setIsBatHanging(false);
				this.world.playEvent((EntityPlayer)null, 1025, blockpos, 0);
			}
		}
		else
		{
			if (this.spawnPosition != null && (!this.world.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1))
			{
				this.spawnPosition = null;
			}

			if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.distanceSq((double)((int)this.posX), (double)((int)this.posY), (double)((int)this.posZ)) < 4.0D)
			{
				this.spawnPosition = new BlockPos((int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
			}
			
			EvileLog.logText(false, EnumLogLevel.INFO, "Bat used to move at this point!");
			//HERE!! This is where the bat used to move from
			//
			//
			//
			// =============================================================================================

			if (this.rand.nextInt(100) == 0 && this.world.getBlockState(blockpos1).isNormalCube())
			{
				this.setIsBatHanging(true);
			}
		}
	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	 * prevent them from trampling crops
	 */
	protected boolean canTriggerWalking()
	{
		return false;
	}

	public void fall(float distance, float damageMultiplier)
	{
	}

	protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos)
	{
	}

	/**
	 * Return whether this entity should NOT trigger a pressure plate or a tripwire.
	 */
	public boolean doesEntityNotTriggerPressurePlate()
	{
		return true;
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (this.isEntityInvulnerable(source))
		{
			return false;
		}
		else
		{
			if (!this.world.isRemote && this.getIsBatHanging())
			{
				this.setIsBatHanging(false);
			}

			return super.attackEntityFrom(source, amount);
		}
	}

	public static void registerFixesBat(DataFixer fixer)
	{
		EntityLiving.registerFixesMob(fixer, EntityVampireBat.class);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		this.dataManager.set(HANGING, Byte.valueOf(compound.getByte("BatFlags")));
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		compound.setByte("BatFlags", ((Byte)this.dataManager.get(HANGING)).byteValue());
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere()
	{
		BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

		if (blockpos.getY() >= this.world.getSeaLevel())
		{
			return false;
		}
		else
		{
			int i = this.world.getLightFromNeighbors(blockpos);
			int j = 4;

			if (this.isDateAroundHalloween(this.world.getCurrentDate()))
			{
				j = 7;
			}
			else if (this.rand.nextBoolean())
			{
				return false;
			}

			return i > this.rand.nextInt(j) ? false : super.getCanSpawnHere();
		}
	}

	/**
	 * gets whether the date is near Halloween
	 * @param p_175569_1_ An instance of Calendar
	 * @return ??? 
	 */
	private boolean isDateAroundHalloween(Calendar p_175569_1_)
	{
		return p_175569_1_.get(2) + 1 == 10 && p_175569_1_.get(5) >= 20 || p_175569_1_.get(2) + 1 == 11 && p_175569_1_.get(5) <= 3;
	}

	/**
	 * returns the eye-height for the model I guess
	 */
	public float getEyeHeight()
	{
		return this.height / 2.0F;
	}

	/**
	 * Returns the loot table to get dropped items from
	 */
	@Nullable
	protected ResourceLocation getLootTable()
	{
		return LootTableHandler.VAMPIRE_BAT_LOOT;
	}
	
	/**
	 * Sets how the entity attacks -- in case of EntityVampireBat is same as EntityZombie
	 */
	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		float f = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
		int i = 0;

		if (entityIn instanceof EntityLivingBase)
		{
			f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase)entityIn).getCreatureAttribute());
			i += EnchantmentHelper.getKnockbackModifier(this);
		}

		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);

		if (flag)
		{
			if (i > 0 && entityIn instanceof EntityLivingBase)
			{
				((EntityLivingBase)entityIn).knockBack(this, (float)i * 0.5F, (double)MathHelper.sin(this.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(this.rotationYaw * 0.017453292F)));
				this.motionX *= 0.6D;
				this.motionZ *= 0.6D;
			}

			int j = EnchantmentHelper.getFireAspectModifier(this);

			if (j > 0)
			{
				entityIn.setFire(j * 4);
			}

			if (entityIn instanceof EntityPlayer)
			{
				EntityPlayer entityplayer = (EntityPlayer)entityIn;
				ItemStack itemstack = this.getHeldItemMainhand();
				ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;

				if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem().canDisableShield(itemstack, itemstack1, entityplayer, this) && itemstack1.getItem().isShield(itemstack1, entityplayer))
				{
					float f1 = 0.25F + (float)EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

					if (this.rand.nextFloat() < f1)
					{
						entityplayer.getCooldownTracker().setCooldown(itemstack1.getItem(), 100);
						this.world.setEntityState(entityplayer, (byte)30);
					}
				}
			}

			this.applyEnchantments(this, entityIn);
		}

		return flag;
	}
	

	/**
	 * @return whether the 1.7+ AI is enabled -- only really useful for 1.7.10 version as always true for 1.12.2 entities
	 */
	protected boolean isAIEnabled()
	{
		return true;
	}

	/**
	 * Applies attributes such as max health and attack damage
	 */
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);;
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(5.0D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(96.0D);

	}

	/**
	 * Clear's the bat's AI tasks lists
	 */
	protected void clearAITasks()
	{
		
		try {
			tasks.taskEntries.clear();
			targetTasks.taskEntries.clear();

			tasks.taskEntries.removeAll(tasks.taskEntries);
			targetTasks.taskEntries.removeAll(targetTasks.taskEntries);
		} catch (Exception e) {
			System.out.println("Error while clearing vampire_bat AI task lists!");
			e.printStackTrace();
		}
	}

	/**
	 * Adds AI tasks to normal "tasks" list
	 */
	@Override
	protected void initEntityAI()
	{
		this.clearAITasks();
		this.applyEntityAI();
		
		this.tasks.addTask(3, new EvileVampireBatAIWanderAvoidWaterFlyingBiasToTarget3(this, 1.0D, 150));
		
		this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 10));
		this.tasks.addTask(10, new EntityAILookIdle(this));

	}

	/**
	 * Adds AI tasks to "targetTasks" list
	 */
	protected void applyEntityAI()
	{
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPig>(this, EntityPig.class, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityCow>(this, EntityCow.class, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntitySheep>(this, EntitySheep.class, false));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<EntityChicken>(this, EntityChicken.class, false));
	}
}














