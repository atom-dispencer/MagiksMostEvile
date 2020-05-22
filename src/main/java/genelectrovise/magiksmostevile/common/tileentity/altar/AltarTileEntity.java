package genelectrovise.magiksmostevile.common.tileentity.altar;

import java.util.ArrayList;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import genelectrovise.magiksmostevile.common.tileentity.ICustomContainer;
import genelectrovise.magiksmostevile.common.tileentity.amethyst_crystal.AmethystCrystalTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.client.gui.screen.EnchantmentScreen;
import net.minecraft.client.particle.EnchantmentTableParticle.EnchantmentTable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.EnchantmentContainer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.EnchantingTableTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

/**
 * @see EnchantmentTable
 * @see EnchantmentContainer
 * @see EnchantmentScreen
 * @see EnchantingTableBlock
 * @see EnchantingTableTileEntity
 * @author GenElectrovise 14 May 2020
 */
public class AltarTileEntity extends TileEntity implements ITickableTileEntity, ICustomContainer {

	private static final int BASE_ENERGY_CAPACITY = 50;
	private static final int ADDITIONAL_STORAGE_PER_CRYSTAL = 50;
	private static final int DAY_ENERGY_PER_CRYSTAL = 1;
	private static final int NIGHT_ENERGY_PER_CRYSTAL = 3;

	private int tickIncr = 0;
	private int recieveFluxCountdown = 0;
	private ArrayList<AmethystCrystalTileEntity> crystals = new ArrayList<AmethystCrystalTileEntity>();

	// IItemHandler
	protected ItemStackHandler slot_0;
	protected ItemStackHandler slot_1;
	protected ItemStackHandler slot_2;
	protected ItemStackHandler slot_3;

	private final LazyOptional<IItemHandler> slot_0_holder = LazyOptional.of(() -> slot_0);
	private final LazyOptional<IItemHandler> slot_1_holder = LazyOptional.of(() -> slot_1);
	private final LazyOptional<IItemHandler> slot_2_holder = LazyOptional.of(() -> slot_2);
	private final LazyOptional<IItemHandler> slot_3_holder = LazyOptional.of(() -> slot_3);

	private final LazyOptional<IItemHandler> allSlots = LazyOptional.of(() -> new CombinedInvWrapper(slot_0, slot_1, slot_2, slot_3));

	// IEnergyStorage
	protected AmethystFluxEnergyStorage energyStorage;

	private final LazyOptional<IEnergyStorage> energyStorageLazyOptional = LazyOptional.of(() -> energyStorage);

	// Constructor

	public AltarTileEntity() {
		super(EvileDeferredRegistry.TILE_ENTITY_ALTAR.get());
		MagiksMostEvile.LOGGER.debug("Constructing class : AltarTileEntity");

		// IItemHandler
		slot_0 = new ItemStackHandler() {
			@Override
			protected void onContentsChanged(int slot) {
				markDirty();
			}
		};

		slot_1 = new ItemStackHandler() {
			@Override
			protected void onContentsChanged(int slot) {
				markDirty();
			}
		};

		slot_2 = new ItemStackHandler() {
			@Override
			protected void onContentsChanged(int slot) {
				markDirty();
			}
		};

		slot_3 = new ItemStackHandler() {
			@Override
			protected void onContentsChanged(int slot) {
				markDirty();
			}
		};

		// IEnergyStorage
		energyStorage = new AmethystFluxEnergyStorage(BASE_ENERGY_CAPACITY, 1, 1, 0, MagiksMostEvile.MODID + ":energyStorage") {

		};
	}

	// IItemHandler
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction facing) {
		// IItemHandler
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			this.markDirty();
			if (world != null && world.getBlockState(pos).getBlock() != this.getBlockState().getBlock()) {// if the block at myself isn't myself, allow full access (Block Broken)
				return allSlots.cast();
			}
			if (facing == null) {
				return allSlots.cast();
			}
		}

		// IEnergyStorage
		if (capability == CapabilityEnergy.ENERGY) {
			this.markDirty();

			if (world != null && world.getBlockState(pos).getBlock() != this.getBlockState().getBlock()) {// if the block at myself isn't myself, allow full access (Block Broken)
				return energyStorageLazyOptional.cast();
			}
			if (facing == null) {
				return energyStorageLazyOptional.cast();
			}
		}

		return super.getCapability(capability, facing);
	}

	// Generic stuff for tile entities

	@Override
	public void remove() {
		super.remove();
		slot_0_holder.invalidate();
		slot_1_holder.invalidate();
		slot_2_holder.invalidate();
		slot_3_holder.invalidate();
		allSlots.invalidate();

		energyStorageLazyOptional.invalidate();
	}

	@Override
	public void read(CompoundNBT tag) {
		super.read(tag);
		slot_0.deserializeNBT(tag.getCompound(MagiksMostEvile.MODID + ":slot_0"));
		slot_1.deserializeNBT(tag.getCompound(MagiksMostEvile.MODID + ":slot_1"));
		slot_2.deserializeNBT(tag.getCompound(MagiksMostEvile.MODID + ":slot_2"));
		slot_3.deserializeNBT(tag.getCompound(MagiksMostEvile.MODID + ":slot_3"));

		energyStorage.fromNbt(tag.getCompound(energyStorage.nbtKey));
	}

	@Override
	public CompoundNBT write(CompoundNBT tag) {
		tag = super.write(tag);
		tag.put(MagiksMostEvile.MODID + ":slot_0", slot_0.serializeNBT());
		tag.put(MagiksMostEvile.MODID + ":slot_1", slot_1.serializeNBT());
		tag.put(MagiksMostEvile.MODID + ":slot_2", slot_2.serializeNBT());
		tag.put(MagiksMostEvile.MODID + ":slot_3", slot_3.serializeNBT());
		tag.put(energyStorage.nbtKey, energyStorage.toNbt());
		return tag;
	}

	@Override
	public void onLoad() {
		super.onLoad();
	}

	// ITickableTileEntity
	@Override
	public void tick() {

		if (!world.isRemote) {
			// Test for crystals nearby
			if (tickIncr % 100 == 0) {
				crystals.clear();

				for (int x = -4; x < 4; x++) {
					for (int y = -4; y < 4; y++) {
						for (int z = -4; z < 4; z++) {
							BlockPos position = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
							BlockState state = world.getBlockState(position);
							if (state.getBlock() == EvileDeferredRegistry.AMETHYST_CRYSTAL.get() || world.getTileEntity(position) instanceof AmethystCrystalTileEntity) {
								crystals.add((AmethystCrystalTileEntity) world.getTileEntity(position));
								// MagiksMostEvile.LOGGER.dev("added: " + world.getTileEntity(position));
							}
						}
					}
				}

				// Increase energy capacity
				energyStorage.setCapacity(BASE_ENERGY_CAPACITY + (crystals.size() * ADDITIONAL_STORAGE_PER_CRYSTAL));
				MagiksMostEvile.LOGGER.dev("new energy capacity: " + energyStorage.getMaxEnergyStored());
			}

			// Receive amethyst flux
			if (recieveFluxCountdown > 20) {
				if (world instanceof ServerWorld && !world.isDaytime()) {
					energyStorage.receiveEnergy(1, false);
					energyStorage.receiveEnergy(crystals.size() * NIGHT_ENERGY_PER_CRYSTAL, false);
					recieveFluxCountdown = 0;
					MagiksMostEvile.LOGGER.dev("Recieved night energy");
				} else {
					energyStorage.receiveEnergy(crystals.size() * DAY_ENERGY_PER_CRYSTAL, false);
					MagiksMostEvile.LOGGER.dev("Recieved day energy per crystal");
				}
			} else {
				recieveFluxCountdown++;
			}

			// Should reset tickIncr
			if (tickIncr > 100) {
				tickIncr = 0;
			} else {
				tickIncr++;
			}

			MagiksMostEvile.LOGGER.dev("rfc:" + recieveFluxCountdown + " energyCurrent:" + energyStorage.getEnergyStored() + " energyMax:" + energyStorage.getMaxEnergyStored() + " tickIncr:" + tickIncr + " crystals:" + crystals);
		}
	}

	@Override
	public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity player) {
		return new AltarContainer(id, playerInv, new CombinedInvWrapper(slot_0, slot_1, slot_2, slot_3), this, energyStorage);
	}

	@Override
	public void openGUI(ServerPlayerEntity player) {
		if (!world.isRemote) {
			NetworkHooks.openGui(player, this, getPos());
		}
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent(MagiksMostEvile.MODID + ":container.altar");
	}

}
