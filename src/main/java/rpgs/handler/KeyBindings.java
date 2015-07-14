package rpgs.handler;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;


public class KeyBindings
{
    public static KeyBinding menu;
    public static KeyBinding lvl;

    public static void init()
    {
        menu = new KeyBinding("rpgs.key.R", Keyboard.KEY_R, "rpgs.key.categories.RPGSkills");
        lvl = new KeyBinding("rpgs.key.L", Keyboard.KEY_P, "tpgs.key.categories.RPGSkills");
        ClientRegistry.registerKeyBinding(menu);
        ClientRegistry.registerKeyBinding(lvl);
    }
}