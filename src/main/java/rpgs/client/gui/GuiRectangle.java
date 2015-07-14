package rpgs.client.gui;

/**
 * @author vswe
 */
public class GuiRectangle {

    private int xCoord;
    private int yCoord;
    private int width;
    private int height;

    public GuiRectangle(int x, int y, int w, int h) {
        this.xCoord = x;
        this.yCoord = y;
        this.width = w;
        this.height = h;
    }

    public boolean inRect(GuiSkills gui, int mouseX, int mouseY) {
        mouseX -= gui.guiLeft();
        mouseY -= gui.guiTop();

        return xCoord <= mouseX && mouseX <= xCoord + width && yCoord <= mouseY && mouseY <= yCoord + height;
    }

    public void setX(int x) {
        this.xCoord = x;
    }

    public void setY(int y) {
        this.yCoord = y;
    }
}