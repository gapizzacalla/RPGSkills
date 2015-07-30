package rpgs.client.gui;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import rpgs.RPGSkills;
import rpgs.packet.ButtonPacket;
import rpgs.packet.PacketHandler;

public class GuiWarning extends GuiScreen
{
	private int xSize;
	private int ySize;
	private int guiLeft;
	private int guiTop;

	public GuiWarning()
	{
		this.xSize = 256;
		this.ySize = 100;
	}

	@Override
	public void initGui()
	{
		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
		this.buttonList.add(new GuiButton(0, this.guiLeft + 142, this.guiTop + 60, 50, 20, "CANCEL"));
		this.buttonList.add(new GuiButton(1, this.guiLeft + 64, this.guiTop + 60, 50, 20, "OK"));
	}

	@Override
	public void drawDefaultBackground()
	{
		super.drawDefaultBackground();
		this.mc.renderEngine.bindTexture(new ResourceLocation(RPGSkills.MOD_ID, "/textures/gui/container.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

	@Override
	public void drawScreen(int x, int y, float f)
	{
		this.drawDefaultBackground();
		this.fontRendererObj.drawString(StatCollector.translateToLocal("Are you sure you want to reset your skills?"), this.guiLeft + 15, this.guiTop + 20, 0x000000);
		super.drawScreen(x, y, f);
	}

	@Override
	protected void actionPerformed(GuiButton button)
	{
		PacketHandler.sendToServer(new ButtonPacket((byte) button.id));
		Minecraft.getMinecraft().displayGuiScreen((GuiScreen) null);
	}
}