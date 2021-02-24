package genelectrovise.magiksmostevile.network.inscription_table.button_pressed;

import java.util.function.Supplier;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.network.altar.AltarNetworkingManager;
import genelectrovise.magiksmostevile.tileentity.inscription_table.InscriptionTableContainer;
import genelectrovise.magiksmostevile.tileentity.inscription_table.InscriptionTableTileEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

public class ButtonPressedMessageHandlerOnServer {
  public static void onMessageReceived(final ButtonPressedMessageToServer message, Supplier<NetworkEvent.Context> ctxSupplier) {

    ctxSupplier.get().setPacketHandled(true);

    if (!message.isValid()) {
      MagiksMostEvile.LOGGER.warn("Invalid ButtonPressedMessageToServer received on server.");
      return;
    }

    if (ctxSupplier.get().getDirection().getReceptionSide() != LogicalSide.SERVER) {
      MagiksMostEvile.LOGGER.warn("Message recieved on incorrect side. (server) ");
    }

    ctxSupplier.get().enqueueWork(() -> processMessage(message, ctxSupplier));
  }

  private static void processMessage(ButtonPressedMessageToServer message, Supplier<NetworkEvent.Context> ctxSupplier) {
    MagiksMostEvile.LOGGER.debug("processing message!");

    // Get and check container
    Container container = ctxSupplier.get().getSender().openContainer;

    if (!(container instanceof InscriptionTableContainer)) {
      return;
    }

    InscriptionTableContainer inscriptionTable = (InscriptionTableContainer) container;
    BlockPos pos = inscriptionTable.getInscriptionTable().getPos();

    // Get and check tile entity
    TileEntity tileEntity = ctxSupplier.get().getSender().world.getTileEntity(pos);

    if (!(tileEntity instanceof InscriptionTableTileEntity)) {
      return;
    }

    InscriptionTableTileEntity inscriptionTableTileEntity = (InscriptionTableTileEntity) tileEntity;
    inscriptionTableTileEntity.recipe(message.getSignumName());
  }

  public static boolean isProtocolAccepted(String protocolVersion) {
    return AltarNetworkingManager.CAST_BUTTON_PRESSED_MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
  }

}
