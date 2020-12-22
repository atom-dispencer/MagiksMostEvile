/**
 * 
 */
package genelectrovise.magiksmostevile.common.item.equipment.staff;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

/**
 * @author GenElectrovise 18 Jun 2020
 * 
 * @see EnderPearlEntity
 */
public class DimensionWarpingStaff extends Item {

	/**
	 * @param properties
	 */
	public DimensionWarpingStaff(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

		if (!playerIn.isCreative()) {
			if (!worldIn.isRemote) {
				playerIn.sendMessage(TextComponentUtils.toTextComponent(() -> "You can only use this item in creative mode!"));
				return ActionResult.resultPass(playerIn.getHeldItem(handIn));
			}
		}

		if (worldIn.isRemote) {
			// Add particles in the old world
			for (int i = 0; i < 32; ++i) {
				playerIn.world.addParticle(ParticleTypes.PORTAL, true, playerIn.getPosX(), playerIn.getPosY() + random.nextDouble() * 2.0D, playerIn.getPosZ(), random.nextGaussian(), 0.0D, random.nextGaussian());
			}
		}

		if (!worldIn.isRemote) {
			try {

				// Change dimension
				if (playerIn.dimension == DimensionType.OVERWORLD) {
					playerIn.sendMessage(TextComponentUtils.toTextComponent(() -> "Warping to: The Nether"));
					playerIn.changeDimension(DimensionType.THE_NETHER);
				} else if (playerIn.dimension == DimensionType.THE_NETHER) {
					playerIn.sendMessage(TextComponentUtils.toTextComponent(() -> "Warping to: The End"));
					playerIn.changeDimension(DimensionType.THE_END);
				} else if (playerIn.dimension == DimensionType.THE_END) {
					playerIn.sendMessage(TextComponentUtils.toTextComponent(() -> "Warping to: Overworld"));
					playerIn.changeDimension(DimensionType.OVERWORLD);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return ActionResult.resultFail(playerIn.getHeldItem(handIn));
		}

		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

		tooltip.add(TextComponentUtils.toTextComponent(() -> "Ever wanted to just right click to change dimension? Well now you can!"));

		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

}
