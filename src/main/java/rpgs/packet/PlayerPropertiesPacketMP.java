package rpgs.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import rpgs.entity.ExtendedPlayer;

public class PlayerPropertiesPacketMP implements IMessage
{
	private NBTTagCompound playerData;

	public PlayerPropertiesPacketMP()
	{
	}

	public PlayerPropertiesPacketMP(EntityPlayer player)
	{
		this.playerData = new NBTTagCompound();
		ExtendedPlayer.get(player).saveNBTData(this.playerData);
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.playerData = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeTag(buf, this.playerData);
	}

	public static class Handler extends AbstractClientPacketHandler<PlayerPropertiesPacketMP>
	{
		@Override
		public IMessage handleClient(EntityPlayer player, PlayerPropertiesPacketMP message, MessageContext context)
		{
			ExtendedPlayer.get(player).loadNBTData(message.playerData);
			return null;
		}

		@Override
		public IMessage handleServer(EntityPlayer player, PlayerPropertiesPacketMP message, MessageContext context)
		{
			return null;
		}
	}
}