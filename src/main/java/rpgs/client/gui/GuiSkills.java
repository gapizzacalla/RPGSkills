package rpgs.client.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import rpgs.RPGSkills;
import rpgs.entity.ExtendedPlayer;
import rpgs.inventory.ContainerSkills;

public class GuiSkills extends GuiContainer
{
    public GuiSkills()
    {
        super(new ContainerSkills());
    }

    public int guiLeft()
    {
        return guiLeft;
    }

    public int guiTop()
    {
        return guiTop;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
    {
        int textureWidth = 176;
        int textureHeight = 148;
        ExtendedPlayer props = ExtendedPlayer.get(this.mc.thePlayer);
        if (props == null)
        {
            return;
        }
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(RPGSkills.MOD_ID, "/textures/gui/menu.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, textureWidth, textureHeight);
        /**Skill Icon*/
        this.drawTexturedModalRect(this.guiLeft + 14, this.guiTop + 4, 176, 0, 16, 16);
        /**Attack Icon*/
        this.drawTexturedModalRect(this.guiLeft + 14, this.guiTop + 26, 176, 16, 16, 16);
        /**Strength Icon*/
        this.drawTexturedModalRect(this.guiLeft + 14, this.guiTop + 46, 176, 32, 16, 16);
        /**Defence Icon*/
        this.drawTexturedModalRect(this.guiLeft + 14, this.guiTop + 66, 176, 48, 16, 16);
        /**Ranged Icon*/
        this.drawTexturedModalRect(this.guiLeft + 14, this.guiTop + 86, 176, 64, 16, 16);
        /**Health Icon*/
        this.drawTexturedModalRect(this.guiLeft + 14, this.guiTop + 106, 176, 80, 16, 16);
        /**Crafting Icon*/
        this.drawTexturedModalRect(this.guiLeft + 89, this.guiTop + 26, 176, 96, 16, 16);
        /**Mining Icon*/
        this.drawTexturedModalRect(this.guiLeft + 89, this.guiTop + 46, 176, 112, 16, 16);
        /**Smithing Icon*/
        this.drawTexturedModalRect(this.guiLeft + 89, this.guiTop + 66, 176, 128, 16, 16);
        /**Fishing Icon*/
        this.drawTexturedModalRect(this.guiLeft + 89, this.guiTop + 86, 176, 144, 16, 16);
        /**Woodcutting Icon*/
        this.drawTexturedModalRect(this.guiLeft + 89, this.guiTop + 106, 176, 160, 16, 16);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        fontRendererObj.drawString(StatCollector.translateToLocal("Skills"), 33, 13, 0x000000);
        int l = 0, m = 0, n = 0;
        for (int i = 0; i < 2; i++)
        {
            int k = 0;
            for (int j = 0; j < 5; j++)
            {
                fontRendererObj.drawString(StatCollector.translateToLocal(ExtendedPlayer.skills.get(j + m).getName()), 33 + l, 26 + k, 0x000000);
                fontRendererObj.drawString(StatCollector.translateToLocal(ExtendedPlayer.skills.get(j + m).getXP() + "/" + ExtendedPlayer.skills.get(i).getTotalXp()), 33 + l, 35 + k, 0x000000);
                fontRendererObj.drawString(StatCollector.translateToLocal(String.valueOf(ExtendedPlayer.skills.get(j + m).getLevel())), 80 + n, 35 + k, 0x000000);
                k += 20;
            }
            l += 72;
            m += 5;
            n += 76;
        }
    }
}