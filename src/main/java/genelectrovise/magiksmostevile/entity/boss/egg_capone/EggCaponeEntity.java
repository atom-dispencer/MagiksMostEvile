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
package genelectrovise.magiksmostevile.entity.boss.egg_capone;

import genelectrovise.magiksmostevile.entity.EntityAttributeManager;
import genelectrovise.magiksmostevile.entity.boss.BossMob;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

/**
 * @author GenElectrovise 23 Jun 2020
 */
public class EggCaponeEntity extends BossMob {

  /**
   * @param type
   * @param worldIn
   */
  public EggCaponeEntity(EntityType<? extends EggCaponeEntity> type, World worldIn) {
    super(type, worldIn);
  }

  /**
   * Static! Non-inherited! Create a map of attributes. Called from {@link EntityAttributeManager}.
   */
  public static AttributeModifierMap.MutableAttribute getEntityAttributes() {
    return MobEntity.createMobAttributes() //
        .add(Attributes.MAX_HEALTH, 3.0D)
        .add(Attributes.FLYING_SPEED, 2.0f)
        .add(Attributes.ATTACK_DAMAGE, 1.0f)
        .add(Attributes.ATTACK_SPEED, 5.0f)
        .add(Attributes.FOLLOW_RANGE, 64.0f);
  }

}
