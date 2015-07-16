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
        int textureHeight = 200;
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
        this.drawTexturedModalRect(this.guiLeft + 18, this.guiTop + 4, 176, 0, 16, 16);
        /**Attack Icon*/
        this.drawTexturedModalRect(this.guiLeft + 18, this.guiTop + 25, 176, 16, 16, 16);
        /**Strength Icon*/
        this.drawTexturedModalRect(this.guiLeft + 18, this.guiTop + 45, 176, 32, 16, 16);
        /**Defence Icon*/
        this.drawTexturedModalRect(this.guiLeft + 18, this.guiTop + 65, 176, 48, 16, 16);
        /**Ranged Icon*/
        this.drawTexturedModalRect(this.guiLeft + 18, this.guiTop + 85, 176, 64, 16, 16);
        /**Health Icon*/
        this.drawTexturedModalRect(this.guiLeft + 18, this.guiTop + 105, 176, 80, 16, 16);
        /**Crafting Icon*/
        this.drawTexturedModalRect(this.guiLeft + 90, this.guiTop + 25, 176, 96, 16, 16);
        /**Mining Icon*/
        this.drawTexturedModalRect(this.guiLeft + 90, this.guiTop + 45, 176, 112, 16, 16);
        /**Smithing Icon*/
        this.drawTexturedModalRect(this.guiLeft + 90, this.guiTop + 65, 176, 128, 16, 16);
        /**Fishing Icon*/
        this.drawTexturedModalRect(this.guiLeft + 90, this.guiTop + 85, 176, 144, 16, 16);
        /**Woodcutting Icon*/
        this.drawTexturedModalRect(this.guiLeft + 90, this.guiTop + 105, 176, 160, 16, 16);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        fontRendererObj.drawString(StatCollector.translateToLocal("Skills"), 34, 13, 0x000000);
        int l = 0;
        int m = 0;
        for (int i = 0; i < 2; i++)
        {
            int k = 0;
            for (int j = 0; j < 5; j++)
            {
                fontRendererObj.drawString(StatCollector.translateToLocal(ExtendedPlayer.skills.get(j + m).getName()), 36 + l, 25 + k, 4210752);
                fontRendererObj.drawString(StatCollector.translateToLocal(ExtendedPlayer.skills.get(j + m).getXP() + "/" + ExtendedPlayer.skills.get(i).getTotalXp()), 36 + l, 34 + k, 4210752);
                fontRendererObj.drawString(StatCollector.translateToLocal(String.valueOf(ExtendedPlayer.skills.get(j + m).getLevel())), 74 + l, 34 + k, 4210752);
                k += 20;
            }
            l += 72;
            m += 5;
        }
    }
}