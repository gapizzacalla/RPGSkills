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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExtendedPlayer implements IExtendedEntityProperties
{
    public static final String PROP_NAME = "RPGS-EP";
    private final EntityPlayer player;
    /**Skills*/
    private Skill attack;
    private Skill strength;
    private Skill defence;
    private Skill ranged;
    private Skill health;
    private Skill crafting;
    private Skill mining;
    private Skill smithing;
    private Skill fishing;
    private Skill woodcutting;
    public static ArrayList<Skill> skills = new ArrayList<Skill>();

    public ExtendedPlayer(EntityPlayer player)
    {
        this.player = player;
        this.attack = new Skill("Attack");
        this.strength = new Skill("Strength");
        this.defence = new Skill("Defence");
        this.ranged = new Skill("Ranged");
        this.health = new Skill("Health");
        this.crafting = new Skill("Crafting");
        this.mining = new Skill("Mining");
        this.smithing = new Skill("Smithing");
        this.fishing = new Skill("Fishing");
        this.woodcutting = new Skill("Woodcutting");
        loadSkills();
    }

    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = new NBTTagCompound();
        properties.setIntArray("ATTACK", skills.get(0).get());
        properties.setIntArray("STRENGTH", skills.get(1).get());
        properties.setIntArray("DEFENCE", skills.get(2).get());
        properties.setIntArray("RANGED", skills.get(3).get());
        properties.setIntArray("HEALTH", skills.get(4).get());
        properties.setIntArray("CRAFTING", skills.get(5).get());
        properties.setIntArray("MINING", skills.get(6).get());
        properties.setIntArray("SMITHING", skills.get(7).get());
        properties.setIntArray("FISHING", skills.get(8).get());
        properties.setIntArray("WOODCUTTING", skills.get(9).get());
        compound.setTag(PROP_NAME, properties);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = (NBTTagCompound) compound.getTag(PROP_NAME);
        ExtendedPlayer.skills.get(0).set(properties.getIntArray("ATTACK"));
        ExtendedPlayer.skills.get(1).set(properties.getIntArray("STRENGTH"));
        ExtendedPlayer.skills.get(2).set(properties.getIntArray("DEFENCE"));
        ExtendedPlayer.skills.get(3).set(properties.getIntArray("RANGED"));
        ExtendedPlayer.skills.get(4).set(properties.getIntArray("HEALTH"));
        ExtendedPlayer.skills.get(5).set(properties.getIntArray("CRAFTING"));
        ExtendedPlayer.skills.get(6).set(properties.getIntArray("MINING"));
        ExtendedPlayer.skills.get(7).set(properties.getIntArray("SMITHING"));
        ExtendedPlayer.skills.get(8).set(properties.getIntArray("FISHING"));
        ExtendedPlayer.skills.get(9).set(properties.getIntArray("WOODCUTTING"));
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
        ExtendedPlayer.skills.add(attack);
        ExtendedPlayer.skills.add(strength);
        ExtendedPlayer.skills.add(defence);
        ExtendedPlayer.skills.add(ranged);
        ExtendedPlayer.skills.add(health);
        ExtendedPlayer.skills.add(crafting);
        ExtendedPlayer.skills.add(mining);
        ExtendedPlayer.skills.add(smithing);
        ExtendedPlayer.skills.add(fishing);
        ExtendedPlayer.skills.add(woodcutting);
    }

    public void sync()
    {
        if (!this.player.worldObj.isRemote)
        {
            EntityPlayerMP playerMP = (EntityPlayerMP) player;
            PacketHandler.sendTo(new PlayerPropertiesPacket((EntityPlayer)playerMP), playerMP);
        }
    }
}