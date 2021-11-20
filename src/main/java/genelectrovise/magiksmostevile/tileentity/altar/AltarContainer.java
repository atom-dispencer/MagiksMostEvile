/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise
 *
 * This file is part of Magiks Most Evile. Magiks Most Evile is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile.
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
/**
 * 
 */
package genelectrovise.magiksmostevile.tileentity.altar;

import genelectrovise.magiksmostevile.core.support.TrackableIntegerHolder;
import genelectrovise.magiksmostevile.registry.orbital.registries.ContainerOrbitalRegistry;
import genelectrovise.magiksmostevile.tileentity.CommonContainer;
import genelectrovise.magiksmostevile.tileentity.IchorFluidStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

/**
 * @author GenElectrovise 14 May 2020
 */
public class AltarContainer extends CommonContainer {

  private AltarTileEntity altar;
  protected TrackableIntegerHolder currentIchor;
  protected TrackableIntegerHolder maxIchor;
  public TrackableIntegerHolder isCasting = new TrackableIntegerHolder(0);

  protected PlayerInventory inv;

  private AltarRitualSelector selector;

  public AltarContainer(int windowId, PlayerInventory inv, PacketBuffer data) {
    this(windowId, inv, new ItemStackHandler(4), (AltarTileEntity) Minecraft.getInstance().level.getBlockEntity(data.readBlockPos()));
  }

  public AltarContainer(int windowId, PlayerInventory inv, IItemHandler handler, AltarTileEntity altar) {
    super(ContainerOrbitalRegistry.ALTAR_CONTAINER.get(), windowId, 4);

    this.setAltar(altar);
    this.maxIchor = altar.ichorStorage.maxIchor;
    this.currentIchor = altar.ichorStorage.currentIchor;
    this.inv = inv;

    addDataSlot(maxIchor);
    addDataSlot(currentIchor);
    addDataSlot(isCasting);

    isCasting.set(altar.isCasting ? 1 : 0);

    this.selector = new AltarRitualSelector();

    addSlots(inv, handler);
  }

  @Override
  public void broadcastChanges() {
    super.broadcastChanges();

    if (altar == null) {
      AltarTileEntity.LOGGER.error("TileEntityAltar at unknown BlockPos is null! This message was triggered by player " + inv.player.getName() + " opening an AltarContainer!");
      AltarTileEntity.LOGGER.error("Detection and sending of changes for this cycle will halt. This may lead to desync issues, but it's better than crashing!");
      return;
    }

    // Update casting
    if ((getAltar().isCasting ? 1 : 0) != isCasting.get()) {
      this.isCasting.set(getAltar().isCasting ? 1 : 0);
    }

    // If IFluidHandler present, update
    if (altar.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).isPresent()) {
      IFluidHandler fluidHandler = altar.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null);

      if (fluidHandler == null) {
        AltarTileEntity.LOGGER.error("AltarTileEntity at " + altar.getBlockPos() + " does not have an IFluidHandler capability?!");
        AltarTileEntity.LOGGER.error("This can either mean that the Altar has been removed, or it is errored. Try reloading the area. If that does not work, try replacing the Altar.");
        return;
      }

      if (fluidHandler instanceof IchorFluidStorage) {
        this.currentIchor.set(((IchorFluidStorage) fluidHandler).currentIchor.get());
        this.maxIchor.set(((IchorFluidStorage) fluidHandler).maxIchor.get());
      } else {
        AltarTileEntity.LOGGER.warn("Who's been tampering with my Altars!!? The IFluidHandler capability of the Altar at " + altar.getBlockPos()
            + " is not an instance of IchorFluidStorage! (Will not update Ichor values, though will not stop the method)");
      }
    }
  }

  private void addSlots(PlayerInventory playerInventory, IItemHandler handler) {
    int x1 = 69;
    int y1 = 76;
    int xDiff = 22;
    int yDiff = 22;

    // Altar
    addSlot(new AltarSlot(handler, 0, x1, y1));
    addSlot(new AltarSlot(handler, 1, x1 + xDiff, y1));
    addSlot(new AltarSlot(handler, 2, x1, y1 + yDiff));
    addSlot(new AltarSlot(handler, 3, x1 + xDiff, y1 + yDiff));

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
   * @return the selector
   */
  public AltarRitualSelector getSelector() { return selector; }

  /**
   * @return the currentAmethystFlux
   */
  public TrackableIntegerHolder getCurrentAmethystFlux() { return currentIchor; }

  /**
   * @param currentAmethystFlux the currentAmethystFlux to set
   */
  public void setCurrentIchor(int currentAmethystFlux) {
    this.currentIchor.set(currentAmethystFlux);
  }

  /**
   * @return the maxAmethystFlux
   */
  public TrackableIntegerHolder getMaxAmethystFlux() { return maxIchor; }

  /**
   * @param maxAmethystFlux the maxAmethystFlux to set
   */
  public void setIchorCapacity(int maxAmethystFlux) {
    this.maxIchor.set(maxAmethystFlux);
  }

  /**
   * @return the altar
   */
  public AltarTileEntity getAltar() { return altar; }

  /**
   * @param altar the altar to set
   */
  public void setAltar(AltarTileEntity altar) { this.altar = altar; }

  @Override
  public boolean stillValid(PlayerEntity playerIn) {

    if (altar.isCasting()) {
      return false;
    }

    return super.stillValid(playerIn);
  }

}
