package rpgs.client.gui;

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
	private GuiButton CANCEL;
	private GuiButton OK;

	public GuiWarning()
	{
		this.xSize = 256;
		this.ySize = 100;
		this.guiLeft = (this.width - xSize) / 2;
		this.guiTop = (this.height - ySize) / 2;
		this.CANCEL = new GuiButton(0, this.guiLeft + 58, this.guiTop + 30, 50, 20, "CANCEL");
		this.OK = new GuiButton(1, this.guiLeft + 4, this.guiTop + 30, 50, 20, "OK");
	}

	@Override
	public void initGui()
	{
		super.initGui();
		this.buttonList.clear();
		this.buttonList.add(this.CANCEL);
		this.buttonList.add(this.OK);
	}

	@Override
	public void drawDefaultBackground()
	{
		super.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.mc.renderEngine.bindTexture(new ResourceLocation(RPGSkills.MOD_ID, "/textures/gui/container.png"));
		this.drawTexturedModalRect(0, 0, 0, 0, this.xSize, this.ySize);
	}

	@Override
	public void drawScreen(int x, int y, float f)
	{
		this.drawDefaultBackground();
		super.drawScreen(x, y, f);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("Are you sure you want to reset your skills?"), 4, 20, 0x000000);
	}

	@Override
	protected void actionPerformed(GuiButton button)
	{
		PacketHandler.sendToServer(new ButtonPacket((byte) button.id));
	}
}