package rpgs.skill;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import rpgs.entity.ExtendedPlayer;

public class SkillRanged extends Skill
{
    /**
     * Constructor for a new Skill
     *
     * @param name the name of the skill
     */
    public SkillRanged(String name)
    {
        super(name);
    }

	@SubscribeEvent
	public void onLivingAttack(LivingAttackEvent event)
	{
		float amount = event.ammount;
		DamageSource source = event.source;
		Entity entity = source.getEntity();
		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			ExtendedPlayer ePlayer = ExtendedPlayer.get(player);
			World world = player.worldObj;
			if (!world.isRemote && source.isProjectile())
			{
				this.setXP(this.getXP() + (int) amount / 5);
				if (this.canLevel())
				{
					player.addChatComponentMessage(new ChatComponentText(this.getName() + " Level Up!"));
					player.addChatComponentMessage(new ChatComponentText("Level " + this.getLevel()));
				}
				ePlayer.syncWithClient();
			}
		}
	}
}