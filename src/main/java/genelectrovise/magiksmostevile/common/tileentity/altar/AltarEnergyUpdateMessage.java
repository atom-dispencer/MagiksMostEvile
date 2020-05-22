/**
 * 
 */
package genelectrovise.magiksmostevile.common.tileentity.altar;

import org.lwjgl.system.windows.MSG;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Supplier;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * @author GenElectrovise 21 May 2020
 */
public class AltarEnergyUpdateMessage {

	protected BlockPos pos;
	protected int newCurrentAmethystFlux;
	protected int newMaxAmethystFlux;

	public AltarEnergyUpdateMessage(PacketBuffer buffer) {
		this.pos = buffer.readBlockPos();
		this.newCurrentAmethystFlux = buffer.readInt();
		this.newMaxAmethystFlux = buffer.readInt();
	}

	public AltarEnergyUpdateMessage(BlockPos pos, int newCurrentAmethystFlux, int newMaxAmethystFlux) {
		this.pos = pos;
		this.newCurrentAmethystFlux = newCurrentAmethystFlux;
		this.newMaxAmethystFlux = newMaxAmethystFlux;
	}

	/**
	 * Note the params should be in the same order as decoding in contructor!!
	 * 
	 * @param buffer
	 */
	public void encode(AltarEnergyUpdateMessage msg, PacketBuffer buffer) {
		buffer.writeBlockPos(pos);
		buffer.writeInt(newCurrentAmethystFlux);
		buffer.writeInt(newMaxAmethystFlux);
	}

	void handle(AltarEnergyUpdateMessage msg, Supplier<NetworkEvent.Context> context) {
		
	}

	// Called with channel.registerMessage(id, AltarEnergyUpdateMessage.class,
	// AltarEnergyUpdateMessage::encode, AltarEnergyUpdateMessage::new,
	// AltarEnergyUpdateMessage::handle)
}
