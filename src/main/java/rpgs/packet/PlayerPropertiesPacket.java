package rpgs.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import rpgs.entity.ExtendedPlayer;

public class PlayerPropertiesPacket implements IMessage
{
    private NBTTagCompound playerData;

    public PlayerPropertiesPacket() {}

    public PlayerPropertiesPacket(EntityPlayer player)
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

    public class Handler extends AbstractClientPacketHandler<PlayerPropertiesPacket>
    {
        @Override
        public IMessage handleClient(EntityPlayer player, PlayerPropertiesPacket message, MessageContext context)
        {
            ExtendedPlayer.get(player).loadNBTData(message.playerData);
            return null;
        }

        @Override
        public IMessage handleServer(EntityPlayer player, PlayerPropertiesPacket message, MessageContext context)
        {
            return null;
        }
    }
}