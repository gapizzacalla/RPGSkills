package rpgs.skill;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
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
                ePlayer.setMaxHealth(2.0F);
                break;
            case 2:
                ePlayer.setMaxHealth(4.0F);
                break;
            case 3:
                ePlayer.setMaxHealth(6.0F);
                break;
            case 4:
                ePlayer.setMaxHealth(8.0F);
                break;
            case 5:
                ePlayer.setMaxHealth(10.0F);
                break;
            case 6:
                ePlayer.setMaxHealth(12.0F);
                break;
            case 7:
                ePlayer.setMaxHealth(14.0F);
                break;
            case 8:
                ePlayer.setMaxHealth(16.0F);
                break;
            case 9:
                ePlayer.setMaxHealth(18.0F);
                break;
            case 10:
                ePlayer.setMaxHealth(20.0F);
                break;
        }
    }

	@SubscribeEvent
	public void onLivingHeal(LivingHealEvent event)
	{
		Entity entity = event.entity;
		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			ExtendedPlayer ePlayer = ExtendedPlayer.get(player);
			World world = player.worldObj;
			if (!world.isRemote && !player.isPotionActive(Potion.heal) && !player.isPotionActive(Potion.regeneration))
			{
				this.setXP(this.getXP() + 1);
				if (this.canLevel())
				{
					this.setBuffs(this.getLevel(), player);
				}
				ePlayer.syncWithClient();
			}
		}
	}

	@SubscribeEvent
	public void onLivingAttack(LivingAttackEvent event)
	{
		Entity entity = event.entity;
		if (entity instanceof EntityPlayer)
		{
			ExtendedPlayer ePlayer = ExtendedPlayer.get((EntityPlayer) entity);
			float maxHealth = 20.0F + ePlayer.getHearts();
			((EntityPlayer) entity).setHealth(maxHealth - event.ammount);
			ePlayer.syncWithClient();
		}
	}

	@SubscribeEvent
	public void onRenderGameOverlay(RenderGameOverlayEvent.Post event)
	{

	}
}