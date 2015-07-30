package rpgs.packet;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import rpgs.RPGSkills;
import rpgs.handler.GuiHandler;

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
        id = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeByte(id);
    }

    public static class Handler extends AbstractClientPacketHandler<OpenGuiPacket>
    {
        @Override
        public IMessage handleClient(EntityPlayer player, OpenGuiPacket message, MessageContext context)
        {
			if (player instanceof EntityClientPlayerMP)
			{
				player.openGui(RPGSkills.instance(), 1, ((EntityClientPlayerMP) player).worldObj, 0, 0 , 0);
			}
			return null;
        }

        @Override
        public IMessage handleServer(EntityPlayer player, OpenGuiPacket message, MessageContext context)
		{
            return null;
        }
    }
}
