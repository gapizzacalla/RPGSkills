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

public class SkillStrength extends Skill
{
    /**
     * Constructor for a new Skill
     *
     * @param name the name of the skill
     */
    public SkillStrength(String name)
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
			EntityPlayer player = (EntityPlayer) source.getEntity();
			ExtendedPlayer ePlayer = ExtendedPlayer.get(player);
			World world = player.worldObj;
			if (!world.isRemote && source.getDamageType().equals(DamageSource.generic))
			{
				this.setXP(this.getXP() + ((int) amount / 5));
				if (ePlayer.get(ExtendedPlayer.STRENGTH).canLevel())
				{
					player.addChatComponentMessage(new ChatComponentText(this.getName() + " Level Up!"));
					player.addChatComponentMessage(new ChatComponentText("Level " + this.getLevel()));
				}
				ePlayer.syncWithClient();
			}
		}
	}
}