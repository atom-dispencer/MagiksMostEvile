/**
 * 
 */
package genelectrovise.magiksmostevile.common.ritual;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

/**
 * @author GenElectrovise 19 May 2020
 */
public class ConvertAmethystRitual extends Ritual {
	public static final String displayName = "Magikify Amethyst";
	public static final String description = "Imbue an amethyst with magiky powers!";

	public ConvertAmethystRitual() {
		super(displayName, description);
	}

	@Override
	protected boolean canStart() {
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
				return true;
			}
		}

		return super.canStart();
	}

	@Override
	protected void tick() {
		super.tick();
		if (isBetweenTicks(0, 100)) {
			MagiksMostEvile.LOGGER.info("0/10 excl.");
		}

		if (isBetweenTicks(150, 200, true)) {
			MagiksMostEvile.LOGGER.info("15/20 incl");
		}

		if (isBetweenTicks(200, 201, true)) {
			setDone(true);
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

					int count = i;
					itemHandler.ifPresent((handler) -> {
						handler.insertItem(count, new ItemStack(EvileDeferredRegistry.POWERED_AMETHYST.get(), stacks[count].getCount()), false);
					});
				}
			}
		}
	}

}
