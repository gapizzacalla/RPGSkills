package rpgs.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
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
            PacketHandler.sendToServer(new OpenGuiPacket((byte)0));
        }
        if(Keyboard.isKeyDown(KeyBindings.lvl.getKeyCode()))
        {
            ExtendedPlayer.skills[0].setXP(ExtendedPlayer.skills[0].getXP() + 1);
            ExtendedPlayer.skills[0].setLevel(ExtendedPlayer.skills[0].getLevel() + 1);
        }
    }
}