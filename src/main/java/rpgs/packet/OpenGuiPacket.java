package rpgs.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import rpgs.RPGSkills;

public class OpenGuiPacket implements IMessage
{
	private byte id;

	public OpenGuiPacket() {}

	public OpenGuiPacket(byte id)
	{
		this.id = id;
	}

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

	public static class Handler extends AbstractClientPacketHandler<OpenGuiPacket>
	{

		@Override
		public IMessage handleClient(EntityPlayer player, OpenGuiPacket message, MessageContext context)
		{
			player.openGui(RPGSkills.instance(), message.id, player.worldObj, 0, 0, 0);
			return null;
		}

		@Override
		public IMessage handleServer(EntityPlayer player, OpenGuiPacket message, MessageContext context)
		{
			return null;
		}
	}
}

