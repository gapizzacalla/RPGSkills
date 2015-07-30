package rpgs.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import rpgs.entity.ExtendedPlayer;
import rpgs.proxy.CommonProxy;

public class EventsHandler
{
    @SubscribeEvent
    public void onEntityConstructing(EntityConstructing event)
    {
        Entity entity = event.entity;
        if (entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) entity) == null)
        {
            ExtendedPlayer.register((EntityPlayer) entity);
        }
        if (entity instanceof EntityPlayer && entity.getExtendedProperties(ExtendedPlayer.PROP_NAME) == null)
        {
            entity.registerExtendedProperties(ExtendedPlayer.PROP_NAME, new ExtendedPlayer((EntityPlayer) entity));
        }
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
		World world = event.entity.worldObj;
		Entity entity = event.entity;
        if (!world.isRemote && entity instanceof EntityPlayer)
		{
			ExtendedPlayer.get((EntityPlayer) entity).syncWithClient();
			NBTTagCompound playerData = CommonProxy.load(((EntityPlayer) entity).getDisplayName());
			if (playerData != null)
			{
				entity.getExtendedProperties(ExtendedPlayer.PROP_NAME).loadNBTData(playerData);
			}
		}
    }

	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{
		Entity entity = event.entity;
		DamageSource source = event.source;
		World world = event.entity.worldObj;
		if (!world.isRemote)
		{
			if (entity instanceof EntityPlayer)
			{
				NBTTagCompound playerData = new NBTTagCompound() ;
				entity.getExtendedProperties(ExtendedPlayer.PROP_NAME).saveNBTData(playerData);
				CommonProxy.save(((EntityPlayer) entity).getDisplayName(), playerData);
			}
		}
	}

	@SubscribeEvent
	public void onServerTick(ServerTickEvent event)
	{
		int ticks = 0;
		ticks++;
		if (ticks % 20 == 0)
		{
			ExtendedPlayer ePlayer = ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer);
			for (int i = 0; i < ePlayer.skills.size(); i++)
			{
				ePlayer.skills.get(i).setBuffs(ePlayer.skills.get(i).getLevel(), Minecraft.getMinecraft().thePlayer);
			}
		}
	}
}