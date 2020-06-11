package genelectrovise.magiksmostevile.common.tileentity.altar;

import java.util.ArrayList;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import genelectrovise.magiksmostevile.common.ritual.Ritual;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * Handles ritual selection in an {@link AltarContainer}
 * 
 * @author GenElectrovise 10 Jun 2020
 */
public class RitualSelector {
	protected int selectedIndex = 0;
	private ArrayList<Supplier<Ritual>> possibleRituals = new ArrayList<Supplier<Ritual>>();
	private ArrayList<Supplier<Ritual>> availableRituals = new ArrayList<Supplier<Ritual>>();
	private PlayerEntity player;
	private World world;

	/**
	 * 
	 */
	public RitualSelector(PlayerEntity player) {
		this.player = player;
		this.world = player.world;

		init();
	}

	private void init() {
		EvileDeferredRegistry.RITUALS.getEntries().forEach((ritual) -> {
			possibleRituals.add(ritual);

			if (playerHasRitualAdvancement(player, ritual.get())) {
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