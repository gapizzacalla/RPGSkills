package rpgs.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
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
    }
}