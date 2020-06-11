/**
 * 
 */
package genelectrovise.magiksmostevile.common.tileentity.altar;

import java.util.ArrayList;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import genelectrovise.magiksmostevile.common.main.support.TrackableIntegerHolder;
import genelectrovise.magiksmostevile.common.ritual.Ritual;
import genelectrovise.magiksmostevile.common.tileentity.CommonContainer;
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
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

/**
 * @author GenElectrovise 14 May 2020
 */
public class AltarContainer extends CommonContainer {

	protected AltarTileEntity altar;
	protected TrackableIntegerHolder currentAmethystFlux;
	protected TrackableIntegerHolder maxAmethystFlux;
	public TrackableIntegerHolder isCasting = new TrackableIntegerHolder(0);

	protected RitualSelector selector;

	protected PlayerInventory inv;

	public AltarContainer(int windowId, PlayerInventory inv, PacketBuffer data) {
		this(windowId, inv, new ItemStackHandler(4), (AltarTileEntity) Minecraft.getInstance().world.getTileEntity(data.readBlockPos()));
	}

	public AltarContainer(int windowId, PlayerInventory inv, IItemHandler handler, AltarTileEntity altar) {
		super(EvileDeferredRegistry.ALTAR_CONTAINER.get(), windowId, 4);
		MagiksMostEvile.LOGGER.dev("Constructing AltarContainer! (Constructor 3 : id, inv, callable)");

		this.altar = altar;
		this.maxAmethystFlux = altar.energyStorage.maxAmethystFlux;
		this.currentAmethystFlux = altar.energyStorage.currentAmethystFlux;
		this.inv = inv;

		trackInt(maxAmethystFlux);
		trackInt(currentAmethystFlux);
		trackInt(isCasting);

		isCasting.set(altar.isCasting ? 1 : 0);

		this.selector = new RitualSelector();
		selector.init();

		addSlots(inv, handler);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		if ((altar.isCasting ? 1 : 0) != isCasting.get()) {
			this.isCasting.set(altar.isCasting ? 1 : 0);
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

	/**
	 * @return the currentAmethystFlux
	 */
	public TrackableIntegerHolder getCurrentAmethystFlux() {
		return currentAmethystFlux;
	}

	/**
	 * @param currentAmethystFlux the currentAmethystFlux to set
	 */
	public void setCurrentAmethystFlux(int currentAmethystFlux) {
		this.currentAmethystFlux.set(currentAmethystFlux);
	}

	/**
	 * @return the maxAmethystFlux
	 */
	public TrackableIntegerHolder getMaxAmethystFlux() {
		return maxAmethystFlux;
	}

	/**
	 * @param maxAmethystFlux the maxAmethystFlux to set
	 */
	public void setMaxAmethystFlux(int maxAmethystFlux) {
		this.maxAmethystFlux.set(maxAmethystFlux);
	}

	/**
	 * @return the selector
	 */
	public RitualSelector getSelector() {
		return selector;
	}

	/**
	 * Handles ritual selection.
	 * 
	 * @author GenElectrovise 10 Jun 2020
	 */
	public class RitualSelector {
		protected int selectedIndex = 0;
		private ArrayList<Supplier<Ritual>> possibleRituals = new ArrayList<Supplier<Ritual>>();
		private ArrayList<Supplier<Ritual>> availableRituals = new ArrayList<Supplier<Ritual>>();
		private PlayerEntity player = inv.player;
		private World world = player.world;

		public void init() {
			EvileDeferredRegistry.RITUALS.getEntries().forEach((ritual) -> {
				possibleRituals.add(ritual);

				if (playerHasRitualAdvancement(inv.player, ritual.get())) {
					availableRituals.add(ritual);
				}
			});
		}

		/**
		 * @param player
		 * @param ritual
		 * @return
		 */
		private boolean playerHasRitualAdvancement(PlayerEntity player, Ritual ritual) {
			try {
				if (!world.isRemote) {
					ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
					PlayerAdvancements advancements = serverPlayer.getAdvancements();
					AdvancementManager manager = serverPlayer.server.getAdvancementManager();

					ResourceLocation location = ritual.getRegistryName();
					AdvancementProgress progress = advancements.getProgress(manager.getAdvancement(location));

					if (progress.isDone()) {
						return true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return false;
		}

		/**
		 * @return the selectedIndex
		 */
		public int getSelectedIndexAsInt() {
			return selectedIndex;
		}

		/**
		 * @return the {@link Supplier}<{@link Ritual}> of the currently selected
		 *         ritual.
		 */
		@Nullable
		public Supplier<Ritual> getSelectedIndex() {
			
			try {
				return availableRituals.get(selectedIndex);

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

		}

		public void cycle(int numberOfCycles) {
			for (int i = 0; i < numberOfCycles; i++) {
				if (selectedIndex < availableRituals.size()) {
					selectedIndex++;
				} else {
					selectedIndex = 0;
				}
			}
		}

		public void cycle() {
			cycle(1);
		}
	}

}
