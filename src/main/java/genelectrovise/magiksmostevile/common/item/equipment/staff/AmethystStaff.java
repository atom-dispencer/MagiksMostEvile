/**
 * 
 */
package genelectrovise.magiksmostevile.common.item.equipment.staff;

import java.awt.Color;
import java.util.Random;
import java.util.function.Predicate;

import genelectrovise.magiksmostevile.common.particle.glyph.GlyphParticleData;
import genelectrovise.magiksmostevile.common.particle.glyph.GlyphParticleFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShootableItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * @author GenElectrovise 15 Jun 2020
 * @see BowItem
 */
public class AmethystStaff extends ShootableItem {

	/**
	 * @param p_i50040_1_
	 */
	public AmethystStaff(Properties p_i50040_1_) {
		super(p_i50040_1_);
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
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

		ItemStack stack = playerIn.getHeldItem(handIn);

		if (worldIn.isRemote()) {
			for (int i = random.nextInt(10); i < 25; i++) {

				double playerX = new Double(playerIn.getPosX());
				double playerY = new Double(playerIn.getPosY());
				double playerZ = new Double(playerIn.getPosZ());

				worldIn.addParticle(new GlyphParticleData(Color.MAGENTA, 10), true, playerX + random.nextDouble() - 0.5, playerY + random.nextDouble() - 0.5, playerZ + random.nextDouble() - 0.5, random.nextDouble() - 0.5, random.nextDouble() - 0.5, random.nextDouble() - 0.5);
			}
		}

		return (getInventoryAmmoPredicate().test(stack) ? ActionResult.resultConsume(stack) : ActionResult.resultFail(stack));
	}

}
