package rpgs.handler;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import org.lwjgl.input.Keyboard;
import rpgs.RPGSkills;
import rpgs.client.gui.GuiWarning;
import rpgs.entity.ExtendedPlayer;
import rpgs.packet.OpenGuiPacket;
import rpgs.packet.PacketHandler;

@SideOnly(Side.CLIENT)
public class KeyHandler
{
	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event)
	{
		EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
		if (Keyboard.isKeyDown(KeyBindings.menu.getKeyCode()))
		{
			player.openGui(RPGSkills.instance(), 0, player.worldObj, 0, 0, 0);
		}
		if (Keyboard.isKeyDown(KeyBindings.reset.getKeyCode()))
		{
			player.openGui(RPGSkills.instance(), 1, player.worldObj, 0, 0, 0);
		}
	}
}