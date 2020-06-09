package genelectrovise.magiksmostevile.common.network.altar;

import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

/**
 * @author GenElectrovise 24 May 2020
 */
public class AltarCastButtonPressedMessageToServer {

	private static boolean messageIsValid;

	private ResourceLocation ritualResourceLocation;
	private BlockPos pos;

	public AltarCastButtonPressedMessageToServer(ResourceLocation ritualResourceLocation, BlockPos pos) {
		messageIsValid = true;

		this.ritualResourceLocation = ritualResourceLocation;
		this.pos = pos;
	}

	public void encode(PacketBuffer buffer) {
		MagiksMostEvile.LOGGER.dev("Encoding AltarCastButtonPressedMessageToServer.");

		buffer.writeResourceLocation(ritualResourceLocation);
		buffer.writeBlockPos(pos);
	}

	public static AltarCastButtonPressedMessageToServer decode(PacketBuffer buffer) {
		MagiksMostEvile.LOGGER.dev("Decoding AltarCastButtonPressedMessageToServer");

		ResourceLocation ritualResourceLocation = buffer.readResourceLocation();
		BlockPos pos = buffer.readBlockPos();

		return new AltarCastButtonPressedMessageToServer(ritualResourceLocation, pos);
	}

	public boolean isValid() {
		return messageIsValid;
	}

	/**
	 * @return the ritualName
	 */
	public ResourceLocation getRitualResourceLocation() {
		return ritualResourceLocation;
	}

	/**
	 * @return the pos
	 */
	public BlockPos getPos() {
		return pos;
	}
}
