package rpgs.skill;

import net.minecraft.entity.player.EntityPlayer;
import rpgs.entity.ExtendedPlayer;

public class SkillHealth extends Skill
{
    /**
     * Constructor for a new Skill
     *
     * @param name the name of the skill
     */
    public SkillHealth(String name)
    {
        super(name);
    }

    @Override
    public void setBuffs(int level, EntityPlayer player)
    {
        ExtendedPlayer ePlayer = ExtendedPlayer.get(player);
        switch (level)
        {
            case 1:
                ePlayer.setMaxHealth(2.0D);
                break;
            case 2:
                ePlayer.setMaxHealth(4.0D);
                break;
            case 3:
                ePlayer.setMaxHealth(6.0D);
                break;
            case 4:
                ePlayer.setMaxHealth(8.0D);
                break;
            case 5:
                ePlayer.setMaxHealth(10.0D);
                break;
            case 6:
                ePlayer.setMaxHealth(12.0D);
                break;
            case 7:
                ePlayer.setMaxHealth(14.0D);
                break;
            case 8:
                ePlayer.setMaxHealth(16.0D);
                break;
            case 9:
                ePlayer.setMaxHealth(18.0D);
                break;
            case 10:
                ePlayer.setMaxHealth(20.0D);
                break;
        }
    }
}