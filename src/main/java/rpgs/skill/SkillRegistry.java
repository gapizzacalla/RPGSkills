package rpgs.skill;

import rpgs.RPGSkills;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SkillRegistry
{
    /**
     * Map to dynamically store registered skills and an easy way to grab them
     */
    private static Map<String, Skill> skills = new HashMap<String, Skill>();

    /**
     * Accessor for skills HashMap
     * @return the HashMap containing the skills
     */
    public static Map get()
    {
        return skills;
    }

    /**
     * Adds a given skill to the map
     * @param name the name of a skill (key)
     * @param skill the skill object associated with name (value)
     */
    public static void registerSkill(String name, Skill skill)
    {
        skills.put(name, skill);
    }

    /**
     * Obtain skills from the map
     * @param name the name of a given skill
     * @return the Skill object associated with the name
     */
    public static Skill getSkillByName(String name)
    {
        Iterator iterator = skills.entrySet().iterator();
        Map.Entry entry;
        Skill skill = new Skill("NULL");
        if (skills != null)
        {
            while (iterator.hasNext())
            {
                entry = (Map.Entry) iterator.next();
                if (entry.getKey().equals(name))
                {
                    skill = (Skill) entry.getValue();
                    break;
                }
            }
        }
        return skill;
    }
}