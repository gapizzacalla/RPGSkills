package rpgs.skill;

import net.minecraftforge.common.MinecraftForge;

public class SkillCrafting extends Skill
{
    /**
     * Constructor for a new Skill
     *
     * @param name the name of the skill
     */
    public SkillCrafting(String name)
    {
        super(name);
		MinecraftForge.EVENT_BUS.register(this);
    }
}