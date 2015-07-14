package rpgs.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import rpgs.RPGSkills;
import rpgs.entity.ExtendedPlayer;
import rpgs.inventory.ContainerSkills;
import rpgs.skill.Skill;
import rpgs.skill.SkillRegistry;

import java.util.Iterator;
import java.util.Map;

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
        this.drawTexturedModalRect(this.guiLeft + 17, this.guiTop + 4, 176, 0, 16, 16);

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        fontRendererObj.drawString(StatCollector.translateToLocal("Skills"), 34, 13, 4210752);
        int j = 0;
        for (int i = 0; i < 4; i++)
        {
            fontRendererObj.drawString(StatCollector.translateToLocal(ExtendedPlayer.skills[i].getName()), 36, 25 + j, 4210752);
            fontRendererObj.drawString(StatCollector.translateToLocal(ExtendedPlayer.skills[i].getXP() + "/" + ExtendedPlayer.skills[i].getTotalXp()), 36, 34 + j, 4210752);
            fontRendererObj.drawString(StatCollector.translateToLocal(String.valueOf(ExtendedPlayer.skills[i].getLevel())), 74, 34 + j, 4210752);
            j += 20;
        }
    }
}