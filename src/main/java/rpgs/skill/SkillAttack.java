package rpgs.skill;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import rpgs.entity.ExtendedPlayer;

public class SkillAttack extends Skill
{
	/**
	 * Constructor for a new Skill
	 *
	 * @param name the name of the skill
	 */
	public SkillAttack(String name)
	{
		super(name);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{
		Entity entity = event.entity;
		DamageSource source = event.source;
		World world = event.entity.worldObj;
		if (!world.isRemote && source.getSourceOfDamage() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) source.getEntity();
			ExtendedPlayer ePlayer = ExtendedPlayer.get(player);
			this.setXP(this.getXP() + 1);
			if (this.canLevel())
			{
				player.addChatComponentMessage(new net.minecraft.util.ChatComponentText(this.getName() + " Level Up!"));
				player.addChatComponentMessage(new net.minecraft.util.ChatComponentText("Level " + this.getLevel()));
			}
			ePlayer.sync();
		}
	}
}