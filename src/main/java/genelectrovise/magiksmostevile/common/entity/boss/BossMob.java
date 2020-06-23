/**
 * 
 */
package genelectrovise.magiksmostevile.common.entity.boss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;

/**
 * @author GenElectrovise 23 Jun 2020
 * @see WitherEntity
 */
public class BossMob extends MonsterEntity {

	protected final ServerBossInfo bossInfo = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);

	/**
	 * @param type
	 * @param worldIn
	 */
	public BossMob(EntityType<? extends BossMob> type, World worldIn) {
		super(type, worldIn);
	}

	/**
	 * Update the AI task list for the {@link Entity}. In this case also updates the
	 * {@link BossInfo}
	 */
	@Override
	protected void updateAITasks() {
		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
	}

	/**
	 * Add the given player to the list of players tracking this entity. For
	 * instance, a player may track a boss in order to view its associated boss bar.
	 */
	public void addTrackingPlayer(ServerPlayerEntity player) {
		super.addTrackingPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	/**
	 * Removes the given player from the list of players tracking this entity. See
	 * {@link Entity#addTrackingPlayer} for more information on tracking.
	 */
	public void removeTrackingPlayer(ServerPlayerEntity player) {
		super.removeTrackingPlayer(player);
		this.bossInfo.removePlayer(player);
	}

	/**
	 * @return the bossInfo
	 */
	public ServerBossInfo getBossInfo() {
		return bossInfo;
	}
}
