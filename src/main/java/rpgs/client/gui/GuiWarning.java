package rpgs.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import rpgs.RPGSkills;
import rpgs.inventory.ContainerSkills;

@SideOnly(Side.CLIENT)
public class GuiWarning extends GuiContainer
{
	private final int textureWidth = 176;
	private final int textureHeight = 100;

	public GuiWarning()
	{
		super(new ContainerSkills());
	}

	@Override
	public void initGui()
	{
		super.initGui();
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, this.guiLeft + 58, this.guiTop + 4, 50, 20, "CANCEL"));
		this.buttonList.add(new GuiButton(1, this.guiLeft + 4, this.guiTop + 4, 50, 20, "OK"));
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
		this.mc.getTextureManager().bindTexture(new ResourceLocation(RPGSkills.MOD_ID, "/textures/gui/warning.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, 176, 100);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		fontRendererObj.drawString(StatCollector.translateToLocal("Are you sure you want to reset your skills?"), 28, 0, 0x000000);
	}
}