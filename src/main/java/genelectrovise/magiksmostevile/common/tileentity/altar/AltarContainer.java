/**
 * 
 */
package genelectrovise.magiksmostevile.common.tileentity.altar;

import java.util.ArrayList;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import genelectrovise.magiksmostevile.common.main.support.TrackableIntegerHolder;
import genelectrovise.magiksmostevile.common.tileentity.CommonContainer;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

/**
 * @author GenElectrovise 14 May 2020
 */
public class AltarContainer extends CommonContainer {
	protected ArrayList<Advancement> completedAdvancements = new ArrayList<Advancement>();
	protected ArrayList<String> possibleAdvancements = new ArrayList<String>();

	protected AltarTileEntity altar;
	protected TrackableIntegerHolder currentAmethystFlux;
	protected TrackableIntegerHolder maxAmethystFlux;

	protected PlayerInventory inv;

	public AltarContainer(int windowId, PlayerInventory inv, PacketBuffer data) {
		this(windowId, inv, new ItemStackHandler(4), (AltarTileEntity) Minecraft.getInstance().world.getTileEntity(data.readBlockPos()), new AmethystFluxEnergyStorage(50, 1, 1, 0, MagiksMostEvile.MODID + ":energyStorage"));
	}

	public AltarContainer(int windowId, PlayerInventory inv, IItemHandler handler, AltarTileEntity altar, AmethystFluxEnergyStorage energy) {
		super(EvileDeferredRegistry.ALTAR_CONTAINER.get(), windowId, 4);
		MagiksMostEvile.LOGGER.debug("Constructing AltarContainer! (Constructor 3 : id, inv, callable)");

		this.altar = altar;
		this.maxAmethystFlux = energy.maxAmethystFlux;
		this.currentAmethystFlux = energy.currentAmethystFlux;
		this.inv = inv;

		trackInt(maxAmethystFlux);
		trackInt(currentAmethystFlux);

		addPossibleAdvancements();
		getCastableRitualsByAdvancements(inv.player);

		addSlots(inv, handler);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
	}

	private void addPossibleAdvancements() {
		possibleAdvancements.add("tome_convert_amethyst_advancement");

	}

	private void getCastableRitualsByAdvancements(PlayerEntity playerEntity) {

		if (!playerEntity.world.isRemote) {
			ServerPlayerEntity serverPlayer = (ServerPlayerEntity) playerEntity;

			PlayerAdvancements advancements = serverPlayer.getAdvancements();
			AdvancementManager manager = serverPlayer.server.getAdvancementManager();

			for (String adv : possibleAdvancements) {
				try {
					ResourceLocation location = new ResourceLocation(MagiksMostEvile.MODID, adv);
					AdvancementProgress progress = advancements.getProgress(manager.getAdvancement(location));
					Advancement advancement = manager.getAdvancement(location);

					if (progress.isDone()) {
						completedAdvancements.add(advancement);
					}

				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			}

		}
	}

	private void addSlots(PlayerInventory playerInventory, IItemHandler handler) {
		int x1 = 69;
		int y1 = 76;
		int xDiff = 22;
		int yDiff = 22;

		// Altar
		addSlot(new SlotAltar(handler, 0, x1, y1));
		addSlot(new SlotAltar(handler, 1, x1 + xDiff, y1));
		addSlot(new SlotAltar(handler, 2, x1, y1 + yDiff));
		addSlot(new SlotAltar(handler, 3, x1 + xDiff, y1 + yDiff));

		// Player inventory
		for (int i = 0; i < 3; ++i) { // Y
			for (int j = 0; j < 9; ++j) { // X
				Slot invSlot = new Slot(playerInventory, j + i * 9 + 9, 184 + i * 18, 8 + j * 18 - 5);
				addSlot(invSlot);
			}
		}

		for (int k = 0; k < 9; ++k) {
			Slot hotbarSlot = new Slot(playerInventory, k, 242, 8 + k * 18 - 5);
			addSlot(hotbarSlot);
		}
	}

}
