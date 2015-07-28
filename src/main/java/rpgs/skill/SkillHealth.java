package rpgs.skill;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.ChatComponentText;
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
                ePlayer.setMaxHealth(2.0D);
                break;
            case 2:
                ePlayer.setMaxHealth(4.0D);
                break;
            case 3:
                ePlayer.setMaxHealth(6.0D);
                break;
            case 4:
                ePlayer.setMaxHealth(8.0D);
                break;
            case 5:
                ePlayer.setMaxHealth(10.0D);
                break;
            case 6:
                ePlayer.setMaxHealth(12.0D);
                break;
            case 7:
                ePlayer.setMaxHealth(14.0D);
                break;
            case 8:
                ePlayer.setMaxHealth(16.0D);
                break;
            case 9:
                ePlayer.setMaxHealth(18.0D);
                break;
            case 10:
                ePlayer.setMaxHealth(20.0D);
                break;
        }
    }

	@SubscribeEvent
	public void onLivingHeal(LivingHealEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			ExtendedPlayer ePlayer = ExtendedPlayer.get(player);
			if (!player.isPotionActive(Potion.heal) && !player.isPotionActive(Potion.regeneration))
			{
				this.setXP(this.getXP() + 1);
				if (this.canLevel())
				{
					player.addChatComponentMessage(new ChatComponentText(this.getName() + " Level Up!"));
					player.addChatComponentMessage(new ChatComponentText("Level " + this.getLevel()));
					this.setBuffs(this.getLevel(), player);
				}
				ePlayer.sync();
			}
		}
	}
}