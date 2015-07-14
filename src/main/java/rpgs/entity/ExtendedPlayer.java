package rpgs.entity;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import ibxm.Player;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import org.lwjgl.Sys;
import rpgs.packet.PacketHandler;
import rpgs.packet.PlayerPropertiesPacket;
import rpgs.skill.Skill;

public class ExtendedPlayer implements IExtendedEntityProperties
{
    public static final String PROP_NAME = "RPGS-EP";
    private final EntityPlayer player;
    /**Skills*/
    public static int x = 0;
    private Skill attack;
    private Skill health;
    private Skill mining;
    private Skill strength;
    private Skill agility;
    private Skill smithing;
    public static Skill[] skills = new Skill[4];

    public ExtendedPlayer(EntityPlayer player)
    {
        this.player = player;
        this.attack = new Skill("Attack");
        this.health = new Skill("Health");
        this.mining = new Skill("Mining");
        this.strength = new Skill("Strength");
        loadSkills();
    }

    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = new NBTTagCompound();
        properties.setIntArray("ATTACK", skills[0].get());
        compound.setTag(PROP_NAME, properties);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = (NBTTagCompound)compound.getTag(PROP_NAME);
        skills[0].set(properties.getIntArray("ATTACK"));
        System.out.println("ATTACK XP: " + ExtendedPlayer.skills[0].getXP());
        System.out.println("ATTACK NEEDED XP: " + ExtendedPlayer.skills[0].getNeededXP());
        System.out.println("ATTACK TOTAL XP: " + ExtendedPlayer.skills[0].getTotalXp());
        System.out.println("ATTACK LEVEL: " + ExtendedPlayer.skills[0].getLevel());
    }

    @Override
    public void init(Entity entity, World world) { }

    public static void register(EntityPlayer player) { player.registerExtendedProperties(PROP_NAME, new ExtendedPlayer(player)); }

    public static ExtendedPlayer get(EntityPlayer player)
    {
        return (ExtendedPlayer)player.getExtendedProperties(PROP_NAME);
    }

    private void loadSkills()
    {
        skills[0] = attack;
        skills[1] = health;
        skills[2] = mining;
        skills[3] = strength;
    }

    public void sync()
    {
        if (!player.worldObj.isRemote)
        {
            EntityPlayerMP player1 = (EntityPlayerMP) player;
            PacketHandler.sendTo(new PlayerPropertiesPacket((EntityPlayer)player1), player1);
        }
    }
}