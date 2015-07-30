package rpgs.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import rpgs.RPGSkills;
import rpgs.inventory.ContainerEmpty;
import rpgs.inventory.ContainerSkills;
import rpgs.packet.ButtonPacket;
import rpgs.packet.PacketHandler;

@SideOnly(Side.CLIENT)
public class GuiWarning2 extends GuiContainer
{
	private int textureWidth;
	private int textureHeight;

	public GuiWarning2()
	{
		super(new ContainerEmpty());
		this.xSize = 200;
		this.ySize = 100;
		this.textureWidth = 200;
		this.textureHeight = 100;
	}

	@Override
	public void initGui()
	{
		super.initGui();
		this.buttonList.clear();

	}

	@Override
	protected void actionPerformed(GuiButton button)
	{

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(RPGSkills.MOD_ID, "/textures/gui/container.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.textureWidth, this.textureHeight);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		fontRendererObj.drawString(StatCollector.translateToLocal("Are you sure you want to reset your skills?"), 4, 20, 0x000000);
	}
}