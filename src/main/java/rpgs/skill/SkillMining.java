package rpgs.skill;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockOre;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import rpgs.entity.ExtendedPlayer;

public class SkillMining extends Skill
{
	/**
	 * Constructor for a new Skill
	 *
	 * @param name the name of the skill
	 */
	public SkillMining(String name)
	{
		super(name);
	}

	@SubscribeEvent
	public void onBlockBreak(BlockEvent.BreakEvent event)
	{
		EntityPlayer player = event.getPlayer();
		ExtendedPlayer ePlayer = ExtendedPlayer.get(player);
		World world = player.worldObj;
		if (!world.isRemote && event.block instanceof BlockOre)
		{
			this.setXP(this.getXP() + 1);
			if (this.canLevel())
			{
				player.addChatComponentMessage(new ChatComponentText(this.getName() + " Level Up!"));
				player.addChatComponentMessage(new ChatComponentText("Level " + this.getLevel()));
			}
			ePlayer.syncWithClient();
		}
	}
}