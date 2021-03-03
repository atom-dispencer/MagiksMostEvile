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
package genelectrovise.magiksmostevile.entity.boss.tinder_and_cinder;

import genelectrovise.magiksmostevile.core.SetupManager;
import genelectrovise.magiksmostevile.entity.boss.BossMob;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

/**
 * @author GenElectrovise 23 Jun 2020
 */
public class TinderAndCinderEntity extends BossMob {

  /**
   * @param type
   * @param worldIn
   */
  public TinderAndCinderEntity(EntityType<? extends TinderAndCinderEntity> type, World worldIn) {
    super(type, worldIn);
  }

  /**
   * Static! Non-inherited! Create a map of attributes. Called from {@link SetupManager}.
   */
  public static AttributeModifierMap.MutableAttribute getEntityAttributes() {
    return MobEntity.func_233666_p_() //
        .createMutableAttribute(Attributes.MAX_HEALTH, 3.0D)
        .createMutableAttribute(Attributes.FLYING_SPEED, 2.0f)
        .createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.0f)
        .createMutableAttribute(Attributes.ATTACK_SPEED, 5.0f)
        .createMutableAttribute(Attributes.FOLLOW_RANGE, 64.0f);
  }

}
