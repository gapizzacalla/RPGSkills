package rpgs.skill;

public class Skill
{
    private String name;
    private int[] stats = new int[4];
    int currentXp;
    int totalXp;
    int neededXp;
    int level;

    /**
     * Constructor for a new Skill
     * @param name the name of the skill
     */
    public Skill(String name)
    {
        this.name = name;
        this.currentXp = 0;
        setTotalXp();
        this.neededXp = this.totalXp - this.currentXp;
        this.level = 1;
    }

    /**Getters*/
    public int[] get()
    {
        stats[0] = this.getXP();
        stats[1] = this.getNeededXP();
        stats[2] = this.getTotalXp();
        stats[3] = this.getLevel();
        return stats;
    }

    public String getName()
    {
        return this.name;
    }

    public int getXP() { return this.currentXp; }

    public int getNeededXP()
    {
        return this.neededXp = this.totalXp - this.currentXp;
    }

    public int getTotalXp() { return this.totalXp; }

    public int getLevel()
    {
        return this.level;
    }

    /**Setters*/
    public void set(int[] array)
    {
        stats[0] = array[0];
        stats[1] = array[1];
        stats[2] = array[2];
        stats[3] = array[3];
        this.setXP(stats[0]);
        this.setNeededXp(stats[1]);
        this.setTotalXp(stats[2]);
        this.setLevel(stats[3]);
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
                this.setLevel(this.getLevel() + 1);
                this.setXP(0);
                this.setTotalXp();
                return true;
            }
        }
        return false;
    }

    public void reset()
    {
        this.set(new int[]{0, 0, 100, 1});
    }
}