package rpgs.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;
import rpgs.entity.ExtendedPlayer;
import rpgs.packet.OpenGuiPacket;
import rpgs.packet.PacketHandler;

public class KeyHandler
{
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
        if (Keyboard.isKeyDown(KeyBindings.menu.getKeyCode()))
        {
            PacketHandler.sendToServer(new OpenGuiPacket((byte) 0));
        }
        if (Keyboard.isKeyDown(KeyBindings.reset.getKeyCode()))
        {
            ExtendedPlayer eplayer = ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer);
            eplayer.resetSkills();
            eplayer.sync();
        }
    }
}