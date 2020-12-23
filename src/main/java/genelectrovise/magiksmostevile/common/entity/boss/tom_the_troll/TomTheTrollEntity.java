/**
 * 
 */
package genelectrovise.magiksmostevile.common.entity.boss.tom_the_troll;

import genelectrovise.magiksmostevile.common.entity.boss.BossMob;
import genelectrovise.magiksmostevile.common.main.SetupManager;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

/**
 * @author GenElectrovise 23 Jun 2020
 */
public class TomTheTrollEntity extends BossMob {

  /**
   * @param type
   * @param worldIn
   */
  public TomTheTrollEntity(EntityType<? extends TomTheTrollEntity> type, World worldIn) {
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
