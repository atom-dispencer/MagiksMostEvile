package genelectrovise.magiksmostevile.tileentity.inscription_table;

import genelectrovise.magiksmostevile.network.inscription_table.InscriptionTableNetworkingManager;
import genelectrovise.magiksmostevile.network.inscription_table.button_pressed.ButtonPressedMessageToServer;
import genelectrovise.magiksmostevile.registry.orbital.registries.ContainerOrbitalRegistry;
import genelectrovise.magiksmostevile.tileentity.CommonContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

/**
 * {@link FurnaceContainer}
 * 
 * @author GenElectrovise
 *
 */
public class InscriptionTableContainer extends CommonContainer {

  private InscriptionTableTileEntity inscriptionTable;
  private PlayerInventory playerInventory;

  public InscriptionTableContainer(int windowId, PlayerInventory inv, PacketBuffer data) {
    this(windowId, inv, new ItemStackHandler(4), (InscriptionTableTileEntity) Minecraft.getInstance().world.getTileEntity(data.readBlockPos()));
  }

  public InscriptionTableContainer(int windowId, PlayerInventory inv, IItemHandler handler, InscriptionTableTileEntity inscriptionTable) {
    super(ContainerOrbitalRegistry.INSCRIPTION_TABLE_CONTAINER.get(), windowId, 1);

    setInscriptionTable(inscriptionTable);
    setPlayerInventory(inv);

    addSlots(inv, handler);
  }

  private void addSlots(PlayerInventory playerInventory, IItemHandler handler) {

    // Player inventory
    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 9; ++j) {
        this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
      }
    }

    for (int k = 0; k < 9; ++k) {
      this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
    }

    // Inscription table
    addSlot(new SlotItemHandler(handler, 0, 137, 33));
  }

  // Get and set

  public InscriptionTableTileEntity getInscriptionTable() {
    return inscriptionTable;
  }

  public PlayerInventory getPlayerInventory() {
    return playerInventory;
  }

  public void setInscriptionTable(InscriptionTableTileEntity inscriptionTable) {
    this.inscriptionTable = inscriptionTable;
  }

  public void setPlayerInventory(PlayerInventory playerInventory) {
    this.playerInventory = playerInventory;
  }

  public void buttonPressed(ResourceLocation signumName) {
    InscriptionTableNetworkingManager.CButtonPressed.sendToServer(new ButtonPressedMessageToServer(signumName));
  }

}
