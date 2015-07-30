package rpgs.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;
import rpgs.entity.ExtendedPlayer;

public class ButtonPacket implements IMessage
{
	private byte id;

	public ButtonPacket() {}

	public ButtonPacket(byte id) { this.id = id; }

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.id = buf.readByte();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeByte(this.id);
	}

	public static class Handler extends AbstractServerPacketHandler<ButtonPacket>
	{

		@Override
		public IMessage handleClient(EntityPlayer player, ButtonPacket message, MessageContext context)
		{
			return null;
		}

		@Override
		public IMessage handleServer(EntityPlayer player, ButtonPacket message, MessageContext context)
		{
			if (message.id == 1)
			{
				ExtendedPlayer eplayer = ExtendedPlayer.get(player);
				eplayer.resetSkills();
				eplayer.syncWithClient();
			}
			return message;
		}
	}
}