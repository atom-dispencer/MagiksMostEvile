/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise
 *
 * This file is part of Magiks Most Evile. Magiks Most Evile is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile.
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
/**
 * 
 */
package genelectrovise.magiksmostevile.entity.boss;

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

  protected ServerBossInfo serverBossInfo;

  public BossMob(EntityType<? extends BossMob> type, World worldIn) {
    this(type, worldIn, BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS);
  }

  public BossMob(EntityType<? extends BossMob> type, World worldIn, BossInfo.Color bossBarColor, BossInfo.Overlay bossBarType) {
    this(type, worldIn, bossBarColor, bossBarType, true, false);
  }

  public BossMob(EntityType<? extends BossMob> type, World worldIn, BossInfo.Color bossBarColor, BossInfo.Overlay bossBarType, boolean darkenSky, boolean createFog) {
    super(type, worldIn);
    this.serverBossInfo = new ServerBossInfo(this.getDisplayName(), bossBarColor, bossBarType);
    this.serverBossInfo.setDarkenScreen(darkenSky);
    this.serverBossInfo.setCreateWorldFog(createFog);
  }

  /**
   * Update the AI task list for the {@link Entity}. In this case also updates the {@link BossInfo}
   */
  @Override
  protected void registerGoals() {
    this.serverBossInfo.setPercent(this.getHealth() / this.getMaxHealth());
  }

  /**
   * Add the given player to the list of players tracking this entity. For instance, a player may
   * track a boss in order to view its associated boss bar.
   */
  public void addTrackingPlayer(ServerPlayerEntity player) {
    super.startSeenByPlayer(player);
    this.serverBossInfo.addPlayer(player);
  }

  /**
   * Removes the given player from the list of players tracking this entity. See
   * {@link Entity#addTrackingPlayer} for more information on tracking.
   */
  public void removeTrackingPlayer(ServerPlayerEntity player) {
    super.stopSeenByPlayer(player);
    this.serverBossInfo.removePlayer(player);
  }

  /**
   * @return the bossInfo
   */
  public ServerBossInfo getServerBossInfo() {
    return serverBossInfo;
  }

  public void setServerBossInfo(ServerBossInfo bossInfo) {
    this.serverBossInfo = bossInfo;
  }
}
