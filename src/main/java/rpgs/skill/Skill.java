package rpgs.skill;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;
import rpgs.entity.ExtendedPlayer;

public class Skill
{
	Minecraft mc;
    private String name;
    private int[] stats = new int[4];
    private int currentXp;
    private int totalXp;
    private int neededXp;
    private int level;

    /**
     * Constructor for a new Skill
     * @param name the name of the skill
     */
    public Skill(String name)
    {
		this.mc = Minecraft.getMinecraft();
        this.name = name;
        this.currentXp = 0;
        this.level = 0;
		this.setTotalXp();
		this.neededXp = this.totalXp - this.currentXp;
		MinecraftForge.EVENT_BUS.register(this);
    }

    /**Getters*/
    public int[] get()
    {
        this.stats[0] = this.getXP();
		this.stats[1] = this.getNeededXP();
		this.stats[2] = this.getTotalXp();
		this.stats[3] = this.getLevel();
        return this.stats;
    }

    public String getName()
    {
        return this.name;
    }

    public int getXP() { return this.currentXp; }

    public int getNeededXP()
    {
        this.setNeededXp();
        return this.neededXp;
    }

    public int getTotalXp() { return this.totalXp; }

    public int getLevel()
    {
        return this.level;
    }

    /**Setters*/
    public void set(int[] array)
    {
		this.stats[0] = array[0];
		this.stats[1] = array[1];
		this.stats[2] = array[2];
		this.stats[3] = array[3];
        this.setXP(this.stats[0]);
        this.setNeededXp(this.stats[1]);
        this.setTotalXp(this.stats[2]);
        this.setLevel(this.stats[3]);
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setXP(int xp)
    {
        this.currentXp = xp;
    }

    public void setNeededXp(int xp)
    {
        this.neededXp = xp;
    }

    public void setNeededXp()
    {
        this.neededXp = this.totalXp - this.currentXp;
    }

    public void setTotalXp()
    {
        switch(this.level)
        {
            case 1:
                this.setTotalXp(100);
                break;
            case 2:
                this.setTotalXp(200);
                break;
            case 3:
                this.setTotalXp(300);
                break;
            case 4:
                this.setTotalXp(400);
                break;
            case 5:
                this.setTotalXp(500);
                break;
            case 6:
                this.setTotalXp(600);
                break;
            case 7:
                this.setTotalXp(700);
                break;
            case 8:
                this.setTotalXp(800);
                break;
            case 9:
                this.setTotalXp(900);
                break;
            case 10:
                this.setTotalXp(1000);
                break;
			default:
				this.setTotalXp(100);
        }
    }

    public void setTotalXp(int xp)
    {
        this.totalXp = xp;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public boolean canLevel()
    {
        if (this.getXP() >= this.getNeededXP())
        {
            if (this.getLevel() != 10)
            {
                this.setLevel(this.level + 1);
                this.setXP(0);
                this.setTotalXp();
                this.setNeededXp();
				this.mc.thePlayer.addChatComponentMessage(new ChatComponentText(this.getName() + " Level Up!"));
				this.mc.thePlayer.addChatComponentMessage(new ChatComponentText("Level " + this.getLevel()));
                return true;
            }
        }
        return false;
    }

    public void reset()
    {
        this.set(new int[]{0, 0, 100, 1});
    }

    public void setBuffs(int level, EntityPlayer player) {}
}