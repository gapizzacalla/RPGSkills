package rpgs.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import rpgs.RPGSkills;
import rpgs.packet.PacketHandler;
import rpgs.packet.PlayerPropertiesPacket;
import rpgs.skill.*;

import java.util.ArrayList;

public class ExtendedPlayer implements IExtendedEntityProperties
{
    public static final String PROP_NAME = RPGSkills.MOD_ID + "-EP";
    private final EntityPlayer player;
    /**Skills*/
    public ArrayList<Skill> skills = new ArrayList<Skill>();
    private SkillAttack attack;
    private SkillStrength strength;
    private SkillDefence defence;
    private SkillRanged ranged;
    private SkillHealth health;
    private SkillCrafting crafting;
    private SkillMining mining;
    private SkillSmithing smithing;
    private SkillFishing fishing;
    private SkillWoodcutting woodcutting;
    private double hearts;

    public static final String ATTACK = "attack";
    public static final String STRENGTH = "strength";
    public static final String DEFENCE = "defence";
    public static final String RANGED = "ranged";
    public static final String HEALTH = "health";
    public static final String CRAFTING = "crafting";
    public static final String MINING = "mining";
    public static final String SMITHING = "smithing";
    public static final String FISHING = "fishing";
    public static final String WOODCUTTING = "woodcutting";

    public ExtendedPlayer(EntityPlayer player)
    {
        this.player = player;
        this.attack = new SkillAttack("Attack");
        this.strength = new SkillStrength("Strength");
        this.defence = new SkillDefence("Defence");
        this.ranged = new SkillRanged("Ranged");
        this.health = new SkillHealth("Health");
        this.crafting = new SkillCrafting("Crafting");
        this.mining = new SkillMining("Mining");
        this.smithing = new SkillSmithing("Smithing");
        this.fishing = new SkillFishing("Fishing");
        this.woodcutting = new SkillWoodcutting("Woodcutting");
        this.hearts = 0.0D;
        loadSkills();
    }

    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = new NBTTagCompound();
        properties.setIntArray("ATTACK", this.skills.get(0).get());
        properties.setIntArray("STRENGTH", this.skills.get(1).get());
        properties.setIntArray("DEFENCE", this.skills.get(2).get());
        properties.setIntArray("RANGED", this.skills.get(3).get());
        properties.setIntArray("HEALTH", this.skills.get(4).get());
        properties.setIntArray("CRAFTING", this.skills.get(5).get());
        properties.setIntArray("MINING", this.skills.get(6).get());
        properties.setIntArray("SMITHING", this.skills.get(7).get());
        properties.setIntArray("FISHING", this.skills.get(8).get());
        properties.setIntArray("WOODCUTTING", this.skills.get(9).get());
        properties.setDouble("HEARTS", this.hearts);
        compound.setTag(PROP_NAME, properties);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = (NBTTagCompound) compound.getTag(PROP_NAME);
        this.skills.get(0).set(properties.getIntArray("ATTACK"));
        this.skills.get(1).set(properties.getIntArray("STRENGTH"));
        this.skills.get(2).set(properties.getIntArray("DEFENCE"));
        this.skills.get(3).set(properties.getIntArray("RANGED"));
        this.skills.get(4).set(properties.getIntArray("HEALTH"));
        this.skills.get(5).set(properties.getIntArray("CRAFTING"));
        this.skills.get(6).set(properties.getIntArray("MINING"));
        this.skills.get(7).set(properties.getIntArray("SMITHING"));
        this.skills.get(8).set(properties.getIntArray("FISHING"));
        this.skills.get(9).set(properties.getIntArray("WOODCUTTING"));
        this.hearts = properties.getDouble("HEARTS");
    }

    @Override
    public void init(Entity entity, World world) { }

    public static void register(EntityPlayer player) { player.registerExtendedProperties(PROP_NAME, new ExtendedPlayer(player)); }

    public static ExtendedPlayer get(EntityPlayer player)
    {
        return (ExtendedPlayer)player.getExtendedProperties(PROP_NAME);
    }

    public void sync()
    {
        if (!this.player.worldObj.isRemote)
        {
            EntityPlayerMP playerMP = (EntityPlayerMP) player;
            PacketHandler.sendTo(new PlayerPropertiesPacket((EntityPlayer)playerMP), playerMP);
        }
    }

    public Skill get(String name)
    {
        if(name.equals(ATTACK)) { return skills.get(0); }
        else if (name.equals(STRENGTH)) { return skills.get(1); }
        else if (name.equals(DEFENCE)) { return skills.get(2); }
        else if (name.equals(RANGED)) { return skills.get(3); }
        else if (name.equals(HEALTH)) { return skills.get(4); }
        else if (name.equals(CRAFTING)) { return skills.get(5); }
        else if (name.equals(MINING)) { return skills.get(6); }
        else if (name.equals(SMITHING)) { return skills.get(7); }
        else if (name.equals(FISHING)) { return skills.get(8); }
        else if (name.equals(WOODCUTTING)) { return skills.get(9); }
        return null;
    }

    private void loadSkills()
    {
        skills.add(attack);
        skills.add(strength);
        skills.add(defence);
        skills.add(ranged);
        skills.add(health);
        skills.add(crafting);
        skills.add(mining);
        skills.add(smithing);
        skills.add(fishing);
        skills.add(woodcutting);
    }

    public void resetSkills()
    {
        for (int i = 0; i < skills.size(); i++)
        {
            skills.get(i).reset();
        }
    }

    public void setMaxHealth(double value)
    {
        this.hearts = value;
    }

    public double getHearts()
    {
        return this.hearts;
    }
}