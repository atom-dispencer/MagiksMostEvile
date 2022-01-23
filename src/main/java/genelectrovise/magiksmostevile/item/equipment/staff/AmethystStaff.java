/**
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise
 * <p>
 * This file is part of Magiks Most Evile. Magiks Most Evile is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 * <p>
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile.
 * If not, see <https://www.gnu.org/licenses/>.
 */
/**
 *
 */
package genelectrovise.magiksmostevile.item.equipment.staff;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.particle.glyph.GlyphParticleData;
import genelectrovise.magiksmostevile.ritual.glyph.Glyph;
import genelectrovise.magiksmostevile.ritual.glyph.Glyph.GlyphOrientation;
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

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

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
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return new Predicate<ItemStack>() {

            @Override
            public boolean test(ItemStack t) {
                return t.getItem() == Items.BLAZE_ROD;
            }
        };
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {

        try {
            if (worldIn.isClientSide) {
                Glyph glyph = new Glyph(new ResourceLocation(MagiksMostEvile.MODID, "textures/items/general/powered_amethyst.png"));
                glyph.drawCentered(worldIn, playerIn.blockPosition(), 0.5, GlyphOrientation.HORIZONTAL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ItemStack stack = playerIn.getItemInHand(handIn);

        if (!getAllSupportedProjectiles().test(playerIn.getItemInHand(handIn == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND)) && !playerIn.isCreative()) {
            return ActionResult.fail(stack);
        }

        Random random = new Random();
        int radius = 3;

        AxisAlignedBB aabb = new AxisAlignedBB(playerIn.blockPosition().east(radius).north(radius).above(), playerIn.blockPosition().west(3).south(3).below());
        List<Entity> entities = worldIn.getEntities(playerIn, aabb);

        if (!worldIn.isClientSide) {
            for (Entity entity : entities) {
                if (!entity.equals(playerIn)) {
                    playerIn.attack(entity);
                    entity.setSecondsOnFire(1);
                }
            }
        }

        if (worldIn.isClientSide) {
            for (Entity entity : entities) {

                double vec = (random.nextDouble() - 0.5) / 2;
                worldIn.addParticle(new GlyphParticleData(Color.MAGENTA, 10), true, entity.getX(), entity.getY(), entity.getZ(), vec, vec, vec);
            }
        }

        return ActionResult.success(stack);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 10;
    }

}
