package rpgs.proxy;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.Map;

public abstract class CommonProxy implements IProxy
{
    private static final Map<String, NBTTagCompound> playerProps = new HashMap<String, NBTTagCompound>();

    /**
     * Adds an entity's custom data to the map for temporary storage
     * @param compound An NBT Tag Compound that stores the IExtendedEntityProperties data only
     */
    public static void save(String name, NBTTagCompound compound)
    {
        playerProps.put(name, compound);
    }

    /**
     * Removes the compound from the map and returns the NBT tag stored for name or null if none exists
     */
    public static NBTTagCompound load(String name)
    {
        return playerProps.remove(name);
    }

    public EntityPlayer getPlayerEntity(MessageContext context)
    {
        return context.getServerHandler().playerEntity;
    }
}