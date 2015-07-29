package rpgs.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;
import rpgs.RPGSkills;
import rpgs.entity.ExtendedPlayer;
import rpgs.packet.OpenGuiPacket;
import rpgs.packet.PacketHandler;

@SideOnly(Side.CLIENT)
public class KeyHandler
{
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        if (Keyboard.isKeyDown(KeyBindings.menu.getKeyCode()))
        {
            PacketHandler.sendToServer(new OpenGuiPacket((byte) 0));
        }
        if (Keyboard.isKeyDown(KeyBindings.reset.getKeyCode()))
        {
			PacketHandler.sendToServer(new OpenGuiPacket((byte) 1));
		}
    }
}