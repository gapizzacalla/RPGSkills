package rpgs.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import rpgs.RPGSkills;
import rpgs.entity.ExtendedPlayer;

@SideOnly(Side.CLIENT)
public class GuiSkills extends GuiScreen
{
	private int xSize;
	private int ySize;
	private int guiLeft;
	private int guiTop;

	public GuiSkills()
	{
		this.xSize = 176;
		this.ySize = 192;
	}

	@Override
	public void initGui()
	{
		super.initGui();
		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
	}

	@Override
	public void drawDefaultBackground()
	{
		super.drawDefaultBackground();
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		int yDiff = this.guiTop - posY;
		ExtendedPlayer ePlayer = ExtendedPlayer.get(this.mc.thePlayer);
		if (ePlayer == null)
		{
			return;
		}
		this.mc.getTextureManager().bindTexture(new ResourceLocation(RPGSkills.MOD_ID, "/textures/gui/menu.png"));
		this.drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
		/**Skill Icon*/
		this.drawTexturedModalRect(posX + 11, guiTop + 5 - yDiff, 176, 0, 16, 16);
		/**Attack Icon*/
		this.drawTexturedModalRect(posX + 11, guiTop + 33 - yDiff, 176, 16, 16, 16);
		/**Strength Icon*/
		this.drawTexturedModalRect(posX + 11, guiTop + 66 - yDiff, 176, 32, 16, 16);
		/**Defence Icon*/
		this.drawTexturedModalRect(posX + 11, guiTop + 99 - yDiff, 176, 48, 16, 16);
		/**Ranged Icon*/
		this.drawTexturedModalRect(posX + 11, guiTop + 132 - yDiff, 176, 64, 16, 16);
		/**Health Icon*/
		this.drawTexturedModalRect(posX + 11, guiTop + 165 - yDiff, 176, 80, 16, 16);
		/**Crafting Icon*/
		this.drawTexturedModalRect(posX + 92, guiTop + 33 - yDiff, 176, 96, 16, 16);
		/**Mining Icon*/
		this.drawTexturedModalRect(posX + 92, guiTop + 66 - yDiff, 176, 112, 16, 16);
		/**Smithing Icon*/
		this.drawTexturedModalRect(posX + 92, guiTop + 99 - yDiff, 176, 128, 16, 16);
		/**Fishing Icon*/
		this.drawTexturedModalRect(posX + 92, guiTop + 132 - yDiff, 176, 144, 16, 16);
		/**Woodcutting Icon*/
		this.drawTexturedModalRect(posX + 92, guiTop + 165 - yDiff, 176, 160, 16, 16);
		/**Experience*/
		int k = 11, l = 49, m = 0;
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				this.drawTexturedModalRect(posX + k, guiTop + l - yDiff, 0, 192, 74, 5);
				double srcX = ((double) ePlayer.skills.get(j + m).getXP() / (double) ePlayer.skills.get(j + m).getTotalXp()) * 74;
				if (srcX > 0.0)
				{
					this.drawTexturedModalRect(posX + k, guiTop + l - yDiff, 0, 197, (int) srcX, 5);
				}
				l += 33;
			}
			k += 81;
			l = 49;
			m += 5;
		}
	}

	@Override
	public void drawScreen(int x, int y, float f)
	{
		this.drawDefaultBackground();
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		int yDiff = guiTop - posY;
		ExtendedPlayer ePlayer = ExtendedPlayer.get(this.mc.thePlayer);
		if (ePlayer == null)
		{
			return;
		}
		this.fontRendererObj.drawString(StatCollector.translateToLocal("Skills"), this.guiLeft + 28, this.guiTop + 10, 0xFFFFFF);
		int l = 0, m = 0, n = 0;
		for (int i = 0; i < 2; i++)
		{
			int k = 0;
			for (int j = 0; j < 5; j++)
			{
				this.fontRendererObj.drawString(StatCollector.translateToLocal(ePlayer.skills.get(j + m).getName()), this.guiLeft + 30 + l, this.guiTop + 28 + k - yDiff, 0xFFFFFF);
				this.fontRendererObj.drawString(StatCollector.translateToLocal(ePlayer.skills.get(j + m).getXP() + "/" + ePlayer.skills.get(j + m).getTotalXp()), this.guiLeft + 30 + l, this.guiTop + 40 + k, 0xFFFFFF);
				this.fontRendererObj.drawString(StatCollector.translateToLocal(String.valueOf(ePlayer.skills.get(j + m).getLevel())), this.guiLeft + 70 + n, this.guiTop + 40 + k, 0xFFFFFF);
				k += 33;
			}
			l += 79;
			m += 5;
			n += 80;
		}
		super.drawScreen(x, y, f);
	}

    public int guiLeft()
    {
        return guiLeft;
    }

    public int guiTop()
    {
        return guiTop;
    }
}