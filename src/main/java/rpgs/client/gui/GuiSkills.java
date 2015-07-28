package rpgs.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import rpgs.RPGSkills;
import rpgs.entity.ExtendedPlayer;
import rpgs.inventory.ContainerSkills;

@SideOnly(Side.CLIENT)
public class GuiSkills extends GuiContainer
{
    private final int textureWidth = 176;
    private final int textureHeight = 192;

    public GuiSkills()
    {
        super(new ContainerSkills());
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
    {
        int posX = (this.width - textureWidth) / 2;
        int posY = (this.height - textureHeight) / 2;
        int yDiff = guiTop - posY;
        ExtendedPlayer ePlayer = ExtendedPlayer.get(this.mc.thePlayer);
        if (ePlayer == null)
        {
            return;
        }
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(RPGSkills.MOD_ID, "/textures/gui/menu.png"));
        this.drawTexturedModalRect(posX, posY, 0, 0, textureWidth, textureHeight);
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
            l += 49;
            m += 5;
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        int posX = (this.width - textureWidth) / 2;
        int posY = (this.height - textureHeight) / 2;
        int yDiff = guiTop - posY;
        ExtendedPlayer ePlayer = ExtendedPlayer.get(this.mc.thePlayer);
        if (ePlayer == null)
        {
            return;
        }
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        fontRendererObj.drawString(StatCollector.translateToLocal("Skills"), 28, -3, 0xFFFFFF);
        int l = 0, m = 0, n = 0;
        for (int i = 0; i < 2; i++)
        {
            int k = 0;
            for (int j = 0; j < 5; j++)
            {
                fontRendererObj.drawString(StatCollector.translateToLocal(ePlayer.skills.get(j + m).getName()), 30 + l, 28 + k - yDiff, 0xFFFFFF);
                fontRendererObj.drawString(StatCollector.translateToLocal(ePlayer.skills.get(j + m).getXP() + "/" + ePlayer.skills.get(j + m).getTotalXp()), 30 + l, 28 + k, 0xFFFFFF);
                fontRendererObj.drawString(StatCollector.translateToLocal(String.valueOf(ePlayer.skills.get(j + m).getLevel())), 70 + n, 28 + k, 0xFFFFFF);
                k += 33;
            }
            l += 79;
            m += 5;
            n += 80;
        }
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