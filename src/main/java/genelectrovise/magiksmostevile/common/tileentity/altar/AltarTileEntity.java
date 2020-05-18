package genelectrovise.magiksmostevile.common.tileentity.altar;

import genelectrovise.magiksmostevile.common.main.Main;
import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import genelectrovise.magiksmostevile.common.tileentity.ICustomContainer;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.client.gui.screen.EnchantmentScreen;
import net.minecraft.client.particle.EnchantmentTableParticle.EnchantmentTable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.EnchantmentContainer;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.EnchantingTableTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.INameable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
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
	protected ItemStackHandler slot_0;
	protected ItemStackHandler slot_1;
	protected ItemStackHandler slot_2;
	protected ItemStackHandler slot_3;

	private final LazyOptional<IItemHandler> slot_0_holder = LazyOptional.of(() -> slot_0);
	private final LazyOptional<IItemHandler> slot_1_holder = LazyOptional.of(() -> slot_1);
	private final LazyOptional<IItemHandler> slot_2_holder = LazyOptional.of(() -> slot_2);
	private final LazyOptional<IItemHandler> slot_3_holder = LazyOptional.of(() -> slot_3);

	private final LazyOptional<IItemHandler> allSlots = LazyOptional.of(() -> new CombinedInvWrapper(slot_0, slot_1, slot_2, slot_3));

	private ITextComponent customName;

	public AltarTileEntity() {
		super(EvileDeferredRegistry.TILE_ENTITY_ALTAR.get());
		Main.LOGGER.debug("Constructing class : AltarTileEntity");

		slot_0 = new ItemStackHandler();
		slot_1 = new ItemStackHandler();
		slot_2 = new ItemStackHandler();
		slot_3 = new ItemStackHandler();
	}

	// IItemHandler
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			this.markDirty();
			if (world != null && world.getBlockState(pos).getBlock() != this.getBlockState().getBlock()) {// if the block at myself isn't myself, allow full access (Block Broken)
				return allSlots.cast();
			}
			if (facing == null) {
				return allSlots.cast();
			}

			/*
			 * if (world == null) { if (facing == Direction.UP) { return
			 * slot_0_holder.cast(); } if (facing == Direction.DOWN) { return
			 * slot_1_holder.cast(); } return super.getCapability(capability, facing); } if
			 * (facing == Direction.UP) { return slot_2_holder.cast(); } if (facing ==
			 * Direction.DOWN) { return slot_3_holder.cast(); }
			 */
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
	}

	@Override
	public void read(CompoundNBT tag) {
		super.read(tag);
		slot_0.deserializeNBT(tag.getCompound(Main.MODID + ":slot_0"));
		slot_1.deserializeNBT(tag.getCompound(Main.MODID + ":slot_1"));
		slot_2.deserializeNBT(tag.getCompound(Main.MODID + ":slot_2"));
		slot_3.deserializeNBT(tag.getCompound(Main.MODID + ":slot_3"));
	}

	@Override
	public CompoundNBT write(CompoundNBT tag) {
		tag = super.write(tag);
		tag.put(Main.MODID + ":slot_0", slot_0.serializeNBT());
		tag.put(Main.MODID + ":slot_1", slot_1.serializeNBT());
		tag.put(Main.MODID + ":slot_2", slot_2.serializeNBT());
		tag.put(Main.MODID + ":slot_3", slot_3.serializeNBT());
		return tag;
	}

	@Override
	public void onLoad() {
		super.onLoad();
	}

	// ITickableTileEntity
	@Override
	public void tick() {

	}

	public void setCustomName(ITextComponent customName) {
		this.customName = customName;
	}

	@Override
	public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
		return new AltarContainer(p_createMenu_1_, p_createMenu_2_, new CombinedInvWrapper(slot_0, slot_1, slot_2, slot_3), this);
	}

	@Override
	public void openGUI(ServerPlayerEntity player) {
		if (!world.isRemote) {
			NetworkHooks.openGui(player, this, getPos());
		}
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent(Main.MODID + ":container.altar");
	}

}
