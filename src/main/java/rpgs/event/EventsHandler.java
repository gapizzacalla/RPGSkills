package rpgs.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockOre;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;
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
            entity.registerExtendedProperties(ExtendedPlayer.PROP_NAME, new ExtendedPlayer((EntityPlayer) event.entity));
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
                (event.entity.getExtendedProperties(ExtendedPlayer.PROP_NAME)).loadNBTData(playerData);
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
                NBTTagCompound playerData = new NBTTagCompound();
                entity.getExtendedProperties(ExtendedPlayer.PROP_NAME).saveNBTData(playerData);
                CommonProxy.save(((EntityPlayer) entity).getDisplayName(), playerData);
            }
            if (source.getSourceOfDamage() instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) source.getEntity();
                ExtendedPlayer.get(ExtendedPlayer.ATTACK).setXP(ExtendedPlayer.get(ExtendedPlayer.ATTACK).getXP() + 1);
                if (ExtendedPlayer.get(ExtendedPlayer.ATTACK).canLevel())
                {
                    player.addChatComponentMessage(new ChatComponentText("Attack Level Up!"));
                    player.addChatComponentMessage(new ChatComponentText("Level " + String.valueOf(ExtendedPlayer.get(ExtendedPlayer.ATTACK).getLevel())));
                }
                ExtendedPlayer.get((EntityPlayer) player).sync();
            }
        }
    }

    @SubscribeEvent
    public void onLivingAttack(LivingAttackEvent event)
    {
        float amount = event.ammount;
        DamageSource source = event.source;
        if (source.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) source.getEntity();
            if (source.isProjectile())
            {
                ExtendedPlayer.get(ExtendedPlayer.RANGED).setXP(ExtendedPlayer.get(ExtendedPlayer.RANGED).getXP() + ((int) amount / 5));
                if (ExtendedPlayer.get(ExtendedPlayer.RANGED).canLevel())
                {
                    player.addChatComponentMessage(new ChatComponentText("Ranged Level Up!"));
                    player.addChatComponentMessage(new ChatComponentText("Level " + String.valueOf(ExtendedPlayer.get(ExtendedPlayer.RANGED).getLevel())));
                    ExtendedPlayer.get(player).sync();
                }
            } else
            {
                ExtendedPlayer.get(ExtendedPlayer.STRENGTH).setXP(ExtendedPlayer.get(ExtendedPlayer.STRENGTH).getXP() + ((int) amount / 5));
                if (ExtendedPlayer.get(ExtendedPlayer.STRENGTH).canLevel())
                {
                    player.addChatComponentMessage(new ChatComponentText("Strength Level Up!"));
                    player.addChatComponentMessage(new ChatComponentText("Level " + String.valueOf(ExtendedPlayer.get(ExtendedPlayer.STRENGTH).getLevel())));
                    ExtendedPlayer.get(player).sync();
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event)
    {
        DamageSource source = event.source;
        if (event.entity instanceof EntityPlayer && source.getEntity() instanceof EntityLivingBase)
        {
            EntityPlayer player = (EntityPlayer) event.entity;
            if (player.isBlocking() || player.getTotalArmorValue() != 0)
            {
                ExtendedPlayer.get(ExtendedPlayer.DEFENCE).setXP(ExtendedPlayer.get(ExtendedPlayer.DEFENCE).getXP() + ((int) event.ammount / 2));
                if (ExtendedPlayer.get(ExtendedPlayer.DEFENCE).canLevel())
                {
                    player.addChatComponentMessage(new ChatComponentText("Defence Level Up!"));
                    player.addChatComponentMessage(new ChatComponentText("Level " + String.valueOf(ExtendedPlayer.get(ExtendedPlayer.DEFENCE).getLevel())));
                }
                ExtendedPlayer.get(player).sync();
            }
        }
    }

    @SubscribeEvent
    public void onLivingHeal(LivingHealEvent event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.entity;
            if (!player.isPotionActive(Potion.heal) && !player.isPotionActive(Potion.regeneration))
            {
                ExtendedPlayer.get(ExtendedPlayer.HEALTH).setXP(ExtendedPlayer.get(ExtendedPlayer.HEALTH).getXP() + 1);
                if (ExtendedPlayer.get(ExtendedPlayer.HEALTH).canLevel())
                {
                    player.addChatComponentMessage(new ChatComponentText("Health Level Up!"));
                    player.addChatComponentMessage(new ChatComponentText("Level " + String.valueOf(ExtendedPlayer.get(ExtendedPlayer.HEALTH).getLevel())));
                }
                ExtendedPlayer.get(player).sync();
            }
        }
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event)
    {
        EntityPlayer player = event.getPlayer();
        if (event.block instanceof BlockOre)
        {
            ExtendedPlayer.get(ExtendedPlayer.MINING).setXP(ExtendedPlayer.get(ExtendedPlayer.MINING).getXP() + 1);
            if (ExtendedPlayer.get(ExtendedPlayer.MINING).canLevel())
            {
                player.addChatComponentMessage(new ChatComponentText("Mining Level Up!"));
                player.addChatComponentMessage(new ChatComponentText("Level " + String.valueOf(ExtendedPlayer.get(ExtendedPlayer.MINING).getLevel())));
            }
            ExtendedPlayer.get(player).sync();
        } else if (event.block instanceof BlockLog)
        {
            ExtendedPlayer.get(ExtendedPlayer.WOODCUTTING).setXP(ExtendedPlayer.get(ExtendedPlayer.WOODCUTTING).getXP() + 1);
            if (ExtendedPlayer.get(ExtendedPlayer.WOODCUTTING).canLevel())
            {
                player.addChatComponentMessage(new ChatComponentText("Woodcutting Level Up!"));
                player.addChatComponentMessage(new ChatComponentText("Level " + String.valueOf(ExtendedPlayer.get(ExtendedPlayer.WOODCUTTING).getLevel())));
            }
            ExtendedPlayer.get(player).sync();
        }
    }
}