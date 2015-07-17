package rpgs.handler;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;


public class KeyBindings
{
    public static KeyBinding menu;
    public static KeyBinding reset;

    public static void init()
    {
        menu = new KeyBinding("rpgs.key.M", Keyboard.KEY_R, "rpgs.key.categories.RPGSkills");
        reset = new KeyBinding("rpgs.key.R", Keyboard.KEY_GRAVE, "rpgs.key.categories.RPGSkills");
        ClientRegistry.registerKeyBinding(menu);
        ClientRegistry.registerKeyBinding(reset);
    }
}