package genelectrovise.magiksmostevile.network.pixiecourier;

import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.network.inscription_table.button_pressed.ButtonPressedMessageToServer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ClientCourierConfirmationPacket {

    public static ClientCourierConfirmationPacket decode(PacketBuffer buffer) {

        return new ClientCourierConfirmationPacket();
    }

    public void encode(PacketBuffer buffer) {

    }

    public static void processMessage(ClientCourierConfirmationPacket message, Supplier<NetworkEvent.Context> ctxSupplier) {

    }
