package genelectrovise.magiksmostevile.common.tileentity.altar;

import java.util.Random;

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

/**
 * @see EnchantmentTable
 * @see EnchantmentContainer
 * @see EnchantmentScreen
 * @see EnchantingTableBlock
 * @see EnchantingTableTileEntity
 * @author GenElectrovise
 * 14 May 2020
 */
public class AltarTileEntity extends TileEntity implements ITickableTileEntity {
	private Random random;

	public AltarTileEntity() {
		super(EvileDeferredRegistry.TILE_ENTITY_ALTAR.get());
		Main.LOGGER.debug("Constructing class : AltarTileEntity");
	}

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
		this.random = world.getRandom();
		super.onLoad();
	}

	@Override
	public void tick() {

	}
}
