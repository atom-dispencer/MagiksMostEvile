package genelectrovise.magiksmostevile.common.ritual.result;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import genelectrovise.magiksmostevile.common.ritual.ConvertAmethystRitual;
import genelectrovise.magiksmostevile.common.ritual.ResultHandler;
import genelectrovise.magiksmostevile.common.tileentity.altar.AltarTileEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ConvertAmethystResultHandler extends ResultHandler<ConvertAmethystRitual> {

	private AltarTileEntity altar;
	private ConvertAmethystRitual ritual;

	/**
	 * @param altar
	 * @param ritual
	 */
	public ConvertAmethystResultHandler(AltarTileEntity altar, ConvertAmethystRitual ritual) {
		super(altar, ritual);

		this.altar = altar;
		this.ritual = ritual;
	}

	@Override
	public void handleSuccess() {
		ritual.setDone(true);
		MagiksMostEvile.LOGGER.info("Done!");

		LazyOptional<IItemHandler> itemHandler = altar.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);

		ItemStack[] stacks = new ItemStack[4];
		itemHandler.ifPresent((handler) -> {
			stacks[0] = handler.getStackInSlot(0);
			stacks[1] = handler.getStackInSlot(1);
			stacks[2] = handler.getStackInSlot(2);
			stacks[3] = handler.getStackInSlot(3);
		});

		for (int i = 0; i < stacks.length; i++) {
			if (stacks[i].getItem() == EvileDeferredRegistry.AMETHYST.get()) {

				int slot = i;
				itemHandler.ifPresent((handler) -> {
					// Have to extract old item stack before inserting new one
					handler.extractItem(slot, stacks[slot].getCount(), false);

					int count = stacks[slot].getCount();
					ItemStack stack = new ItemStack(EvileDeferredRegistry.POWERED_AMETHYST.get(), count);
					handler.insertItem(slot, stack, false);
				});
			}
		}

	}

	@Override
	public void handleCasting() {

	}

	@Override
	public void handleFailure() {
		MagiksMostEvile.LOGGER.info("Failed!");
	}

	@Override
	public void handleCataclysm() {
		MagiksMostEvile.LOGGER.info("BOOM!");
	}

}