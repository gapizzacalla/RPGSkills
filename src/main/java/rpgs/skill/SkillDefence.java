package rpgs.skill;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import rpgs.entity.ExtendedPlayer;

public class SkillDefence extends Skill
{
    /**
     * Constructor for a new Skill
     *
     * @param name the name of the skill
     */
    public SkillDefence(String name)
    {
        super(name);
		MinecraftForge.EVENT_BUS.register(this);
    }

	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		float ammount = event.ammount;
		Entity entity = event.entity;
		DamageSource source = event.source;
		if (entity instanceof EntityPlayer && source.getEntity() instanceof EntityLivingBase)
		{
			EntityPlayer player = (EntityPlayer) entity;
			ExtendedPlayer ePlayer = ExtendedPlayer.get(player);
			World world = player.worldObj;
			if (!world.isRemote && player.isBlocking() || player.getTotalArmorValue() != 0)
			{
				this.setXP(this.getXP() + ((int) ammount / 2));
				if (this.canLevel())
				{
					player.addChatComponentMessage(new ChatComponentText(this.getName() + " Level Up!"));
					player.addChatComponentMessage(new ChatComponentText("Level " + this.getLevel()));
				}
				ePlayer.sync();
			}
		}
	}
}