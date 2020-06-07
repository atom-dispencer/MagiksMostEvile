/**
 * 
 */
package genelectrovise.magiksmostevile.common.entity.vampire_bat;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.Event.HasResult;

/**
 * @author GenElectrovise 6 Jun 2020
 */
public class VampireBatEvent extends EntityEvent {

	/**
	 * @param entity
	 */
	public VampireBatEvent(VampireBatEntity vampireBat) {
		super(vampireBat);
	}

	public VampireBatEntity getBat() {
		return (VampireBatEntity) getEntity();
	}

	@HasResult
	public static class SummonAidEvent extends VampireBatEvent {
		private ZombieEntity customSummonedAid;

		private final World world;
		private final int x;
		private final int y;
		private final int z;
		private final LivingEntity attacker;
		private final double summonChance;

		public SummonAidEvent(VampireBatEntity entity, World world, int x, int y, int z, LivingEntity attacker, double summonChance) {
			super(entity);
			this.world = world;
			this.x = x;
			this.y = y;
			this.z = z;
			this.attacker = attacker;
			this.summonChance = summonChance;
		}

		/**
		 * Populate this field to have a custom zombie instead of a normal zombie
		 * summoned
		 */
		public ZombieEntity getCustomSummonedAid() {
			return customSummonedAid;
		}

		public void setCustomSummonedAid(ZombieEntity customSummonedAid) {
			this.customSummonedAid = customSummonedAid;
		}

		public World getWorld() {
			return world;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getZ() {
			return z;
		}

		public LivingEntity getAttacker() {
			return attacker;
		}

		public double getSummonChance() {
			return summonChance;
		}
	}

}
