package rpgs.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import rpgs.RPGSkills;
import rpgs.entity.ExtendedPlayer;
import rpgs.proxy.CommonProxy;

public class EventsHandler
{
    @SubscribeEvent
    public void onEntityConstructing(EntityConstructing event)
    {
        if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity) == null)
        {
            ExtendedPlayer.register((EntityPlayer) event.entity);
        }
        if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(ExtendedPlayer.PROP_NAME) == null)
        {
            event.entity.registerExtendedProperties(ExtendedPlayer.PROP_NAME, new ExtendedPlayer((EntityPlayer) event.entity));
        }
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
        {
            ExtendedPlayer.get((EntityPlayer) event.entity).sync();
            NBTTagCompound playerData = CommonProxy.load(((EntityPlayer) event.entity).getDisplayName());
            if (playerData != null)
            {
                ((ExtendedPlayer) (event.entity.getExtendedProperties(ExtendedPlayer.PROP_NAME))).loadNBTData(playerData);
            }
        }
    }

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event)
    {
        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
        {
            NBTTagCompound playerData = new NBTTagCompound();
            ((ExtendedPlayer) (event.entity.getExtendedProperties(ExtendedPlayer.PROP_NAME))).saveNBTData(playerData);
            CommonProxy.save(((EntityPlayer) event.entity).getDisplayName(), playerData);
        }
    }
}