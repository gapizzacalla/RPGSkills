package rpgs.proxy;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class ClientProxy extends CommonProxy
{
    public static void registerProxies()
    {

    }

    @Override
    public EntityPlayer getPlayerEntity(MessageContext context)
    {
        return (context.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(context));
    }
}