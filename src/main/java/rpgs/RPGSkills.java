package rpgs;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import rpgs.entity.ExtendedPlayer;
import rpgs.event.EventsHandler;
import rpgs.handler.GuiHandler;
import rpgs.handler.KeyBindings;
import rpgs.handler.KeyHandler;
import rpgs.packet.PacketHandler;
import rpgs.proxy.ClientProxy;
import rpgs.proxy.CommonProxy;
import rpgs.proxy.IProxy;
import rpgs.skill.*;

@Mod(modid = RPGSkills.MOD_ID, name = RPGSkills.MOD_NAME, version = RPGSkills.VERSION/*, guiFactory = RPGSkills.GUI_FACTORY_CLASS*/)
public class RPGSkills
{
    public static final String MOD_ID = "rpgs";
    public static final String MOD_NAME = "RPG Skills";
    public static final String VERSION = "1.7.10-1.0";
    public static final String CONFIG_NAME = MOD_NAME;
    public static final String CLIENT_PROXY_CLASS = "rpgs.proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "rpgs.proxy.ServerProxy";
    public static final String GUI_FACTORY_CLASS = "rpgs.client.GuiFactory";

    @Instance(MOD_ID)
    public static RPGSkills instance;

    @SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = SERVER_PROXY_CLASS, modId = MOD_ID)
    public static CommonProxy commonProxy;
    public static IProxy proxy;
    public static final PacketHandler packetHandler = new PacketHandler();

    /**
     * Put Network Handling, Mod Configurations, Register Blocks/Items
     */
    @EventHandler
    public void preInitialization(FMLPreInitializationEvent event)
    {
        new GuiHandler();
        FMLCommonHandler.instance().bus().register(new KeyHandler());
        KeyBindings.init();
    }

    /**
     * Put GUI, TileEntity, Crafting, Events
     */
    @EventHandler
    public void initialization(FMLInitializationEvent event)
    {
        PacketHandler.registerPackets();
        ClientProxy.registerProxies();
        MinecraftForge.EVENT_BUS.register(new EventsHandler());
    }

    /**
     * Interact with other mods
     */
    @EventHandler
    public void postInitialization(FMLPostInitializationEvent event) { }
}