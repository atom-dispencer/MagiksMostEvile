/**
 * 
 */
package genelectrovise.magiksmostevile.common.tileentity.altar;

import org.lwjgl.opengl.GLUtil;
import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.core.registry.orbital.registries.ContainerOrbitalRegistry;
import genelectrovise.magiksmostevile.common.core.support.TrackableIntegerHolder;
import genelectrovise.magiksmostevile.common.tileentity.CommonContainer;
import genelectrovise.magiksmostevile.common.tileentity.IchorFluidStorage;
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

  private RitualSelector selector;

  public AltarContainer(int windowId, PlayerInventory inv, PacketBuffer data) {
    this(windowId, inv, new ItemStackHandler(4), (AltarTileEntity) Minecraft.getInstance().world.getTileEntity(data.readBlockPos()));
  }

  public AltarContainer(int windowId, PlayerInventory inv, IItemHandler handler, AltarTileEntity altar) {
    super(ContainerOrbitalRegistry.ALTAR_CONTAINER.get(), windowId, 4);

    this.setAltar(altar);
    this.maxIchor = altar.ichorStorage.maxIchor;
    this.currentIchor = altar.ichorStorage.currentIchor;
    this.inv = inv;

    trackInt(maxIchor);
    trackInt(currentIchor);
    trackInt(isCasting);

    isCasting.set(altar.isCasting ? 1 : 0);

    this.selector = new RitualSelector();

    addSlots(inv, handler);
  }

  @Override
  public void detectAndSendChanges() {
    super.detectAndSendChanges();

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
        AltarTileEntity.LOGGER.error("AltarTileEntity at " + altar.getPos() + " does not have an IFluidHandler capability?!");
        AltarTileEntity.LOGGER.error("This can either mean that the Altar has been removed, or it is errored. Try reloading the area. If that does not work, try replacing the Altar.");
        return;
      }

      if (fluidHandler instanceof IchorFluidStorage) {
        this.currentIchor.set(((IchorFluidStorage) fluidHandler).currentIchor.get());
        this.maxIchor.set(((IchorFluidStorage) fluidHandler).maxIchor.get());
      } else {
        AltarTileEntity.LOGGER.warn("Who's been tampering with my Altars!!? The IFluidHandler capability of the Altar at " + altar.getPos() + " is not an instance of IchorFluidStorage! (Will not update Ichor values, though will not stop the method)");
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

  /**
   * @return the selector
   */
  public RitualSelector getSelector() {
    return selector;
  }

  /**
   * @return the currentAmethystFlux
   */
  public TrackableIntegerHolder getCurrentAmethystFlux() {
    return currentIchor;
  }

  /**
   * @param currentAmethystFlux the currentAmethystFlux to set
   */
  public void setCurrentIchor(int currentAmethystFlux) {
    this.currentIchor.set(currentAmethystFlux);
  }

  /**
   * @return the maxAmethystFlux
   */
  public TrackableIntegerHolder getMaxAmethystFlux() {
    return maxIchor;
  }

  /**
   * @param maxAmethystFlux the maxAmethystFlux to set
   */
  public void setIchorCapacity(int maxAmethystFlux) {
    this.maxIchor.set(maxAmethystFlux);
  }

  /**
   * @return the altar
   */
  public AltarTileEntity getAltar() {
    return altar;
  }

  /**
   * @param altar the altar to set
   */
  public void setAltar(AltarTileEntity altar) {
    this.altar = altar;
  }

  @Override
  public boolean canInteractWith(PlayerEntity playerIn) {

    if (altar.isCasting()) {
      return false;
    }

    return super.canInteractWith(playerIn);
  }

}
