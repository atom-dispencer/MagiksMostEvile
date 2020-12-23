/**
 * 
 */
package genelectrovise.magiksmostevile.common.item.equipment.staff;

import java.awt.Color;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.particle.glyph.GlyphParticleData;
import genelectrovise.magiksmostevile.common.ritual.glyph.Glyph;
import genelectrovise.magiksmostevile.common.ritual.glyph.Glyph.GlyphOrientation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShootableItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * @author GenElectrovise 15 Jun 2020
 * @see BowItem
 */
public class AmethystStaff extends ShootableItem {

  /**
   * @param properties
   */
  public AmethystStaff(Properties properties) {
    super(properties);
  }

  @Override
  public Predicate<ItemStack> getInventoryAmmoPredicate() {
    return new Predicate<ItemStack>() {

      @Override
      public boolean test(ItemStack t) {
        return t.getItem() == Items.BLAZE_ROD;
      }
    };
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn,
      Hand handIn) {

    try {
      if (worldIn.isRemote) {
        Glyph glyph = new Glyph(new ResourceLocation(MagiksMostEvile.MODID,
            "textures/items/general/powered_amethyst.png"));
        glyph.drawCentered(worldIn, playerIn.getPosition(), 0.5, GlyphOrientation.HORIZONTAL);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    ItemStack stack = playerIn.getHeldItem(handIn);

    if (!getInventoryAmmoPredicate()
        .test(playerIn.getHeldItem(handIn == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND))
        && !playerIn.isCreative()) {
      return ActionResult.resultFail(stack);
    }

    Random random = new Random();
    int radius = 3;

    AxisAlignedBB aabb = new AxisAlignedBB(playerIn.getPosition().east(radius).north(radius).up(),
        playerIn.getPosition().west(3).south(3).down());
    List<Entity> entities = worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, aabb);

    if (!worldIn.isRemote) {
      for (Entity entity : entities) {
        playerIn.attackTargetEntityWithCurrentItem(entity);
        entity.setFire(1);
      }
    }

    if (worldIn.isRemote) {
      for (Entity entity : entities) {

        double vec = (random.nextDouble() - 0.5) / 2;
        worldIn.addParticle(new GlyphParticleData(Color.MAGENTA, 10), true, entity.getPosX(),
            entity.getPosY(), entity.getPosZ(), vec, vec, vec);
      }
    }

    return ActionResult.resultSuccess(stack);
  }

  /**
   * I have no idea what this is. Cross-bow = 8, Bow = 15!!?
   */
  @Override
  public int func_230305_d_() {
    return 10;
  }

}
