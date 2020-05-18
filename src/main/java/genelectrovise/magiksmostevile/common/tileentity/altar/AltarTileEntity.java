package genelectrovise.magiksmostevile.common.tileentity.altar;

import genelectrovise.magiksmostevile.common.main.Main;
import genelectrovise.magiksmostevile.common.main.registry.EvileDeferredRegistry;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.client.gui.screen.EnchantmentScreen;
import net.minecraft.client.particle.EnchantmentTableParticle.EnchantmentTable;
import net.minecraft.inventory.container.EnchantmentContainer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.EnchantingTableTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.INameable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.IItemHandler;

/**
 * @see EnchantmentTable
 * @see EnchantmentContainer
 * @see EnchantmentScreen
 * @see EnchantingTableBlock
 * @see EnchantingTableTileEntity
 * @author GenElectrovise 14 May 2020
 */
public class AltarTileEntity extends TileEntity implements ITickableTileEntity, INameable, ICapabilityProvider {

	@CapabilityInject(IItemHandler.class)
	static Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

	private ITextComponent customName;

	public AltarTileEntity() {
		super(EvileDeferredRegistry.TILE_ENTITY_ALTAR.get());
		Main.LOGGER.debug("Constructing class : AltarTileEntity");
	}

	// IItemHandler
	

	// Generic stuff for tile entities

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		return super.write(compound);
	}

	@Override
	public void onLoad() {
		super.onLoad();
	}

	// ITickableTileEntity
	@Override
	public void tick() {

	}

	// INameable
	@Override
	public boolean hasCustomName() {
		return true;
	}

	@Override
	public ITextComponent getCustomName() {
		return (ITextComponent) customName;
	}

	public void setCustomName(ITextComponent customName) {
		this.customName = customName;
	}

	@Override
	public ITextComponent getName() {
		return (ITextComponent) (this.customName != null ? this.customName : new TranslationTextComponent("container.altar"));
	}
}
