package com.magiksmostevile;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

/**
 * <a href="https://mcforge.readthedocs.io/en/latest/networking/simpleimpl/">Forge networking help</a>
 */
public class EvilePacketHandler {
	/**
	 * The instance of the SimpleNetworkWrapper with the channel name "magiksmostevile"
	 */
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Main.MODID);
	
	// How to register the message
	// INSTANCE.registerMessage(EvileMessageHandler.class, EvileMessage.class, 0, Side.Server);

	public static class EvileMessage implements IMessage {
		
		int toSend;
		
		public EvileMessage() {
		}

		public EvileMessage(int toSend) {
			this.toSend = toSend;
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			toSend = buf.readInt();
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(toSend);
		}

		/**
		 * Handles packets of the type (class) EvileMessage
		 * @author adam_
		 *
		 */
		public static class EvileMessageHandler implements IMessageHandler<EvileMessage, IMessage> {

			/**
			 * @param message An INCOMING instance of the MessageHandler's message type.
			 * @param ctx The context of the INCOMING message. Eg. So that things can happen to a player via <code> EntityPlayerMP serverPlayer = ctx.getServerHandler().player; </code> then using serverPlayer to do stuff!
			 */
			@Override
			public IMessage onMessage(EvileMessage message, MessageContext ctx) {
				
				// This is the player the packet was sent to the server from
				EntityPlayerMP serverPlayer = ctx.getServerHandler().player;

				// The value that was sent
				int amount = message.toSend;

				// Execute the action on the main server thread by adding it as a scheduled task
				serverPlayer.getServerWorld().addScheduledTask(() -> {
					serverPlayer.sendMessage(new TextComponentString("Congrats! You recieved a packet! The packet said: " + amount));
				});
				return null;

			}

		}

	}

}
