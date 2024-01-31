package cn.ksmcbrigade.QFM;

import cn.ksmcbrigade.QFM.events.TimerEvent;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

@Mod("qfm")
@Mod.EventBusSubscriber
public class QuickFeatureMenu {

    public static final Logger LOGGER = LogManager.getLogger();
    public static Hack[] hacks = Utils.getHacks();
    public static Player player = Minecraft.getInstance().player;

    public static float BF = 0.3F;
    public static float timer = 1.0F;

    public static KeyMapping keyMapping = new KeyMapping("Menu", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_ALT,"Quick Menu");

    public QuickFeatureMenu() {
        MinecraftForge.EVENT_BUS.register(TimerEvent.class);
        MinecraftForge.EVENT_BUS.register(this);
        System.setProperty("java.awt.headless", "false");
        LOGGER.info("Quick Feature Menu Initializing...");
        ClientRegistry.registerKeyBinding(keyMapping);
        LOGGER.info("Creating and reading configuration files in the registry.");
        initRegistry();
        LOGGER.info("Starting menu.");
        Utils.StartMenu();
        LOGGER.info("Started menu.");
        LOGGER.info("Quick Feature Menu Initialized.");
    }

    public static void initRegistry(){
        //Registry path:HKEY_CURRENT_USER\SOFTWARE\JavaSoft\Prefs\qfm
        JsonObject JSON;
        JsonObject JSON2;
        LOGGER.info("Initializing Registry...");
        if(!Utils.existsRegistry("qfm","enabled")){
            Utils.writeRegistry("qfm","enabled",new JsonObject().toString());
        }
        if(!Utils.existsRegistry("qfm","config")){
            JsonObject JSon = new JsonObject();
            JSon.addProperty("boatFly",0.3F);
            JSon.addProperty("timer",1F);
            Utils.writeRegistry("qfm","config",JSon.toString());
        }
        JSON = JsonParser.parseString(Utils.readRegistry("qfm","enabled")).getAsJsonObject();
        JSON2 = JsonParser.parseString(Utils.readRegistry("qfm","config")).getAsJsonObject();
        for(String enabled:JSON.keySet()){
            Utils.SetHackEnabled(Utils.unicodeToString(enabled),true);
        }
        BF = JSON2.get("boatFly").getAsFloat();
        timer = JSON2.get("timer").getAsFloat();
    }

    @SubscribeEvent
    public static void getPlayer(TickEvent.PlayerTickEvent event){
        player = event.player;
        for(Hack hack:hacks){
            if(hack.isEnabled()){
                hack.Up();
            }
        }
    }

    @SubscribeEvent
    public static void OnInputKey(InputEvent.KeyInputEvent event){
        if(keyMapping.isDown()){
            Utils.StartMenu();
        }
    }

    @SubscribeEvent
    public static void OnRenderTick(TimerEvent event){
        if(Utils.getEnabled("运算变速")){
            event.time*=timer;
        }
    }

    @SubscribeEvent
    public static void OnRenderBackground(ScreenEvent.BackgroundDrawnEvent event){
        if(Utils.getEnabled("无背景")){
            LOGGER.info(event.isCancelable());
            if(event.isCancelable()){
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void OnRenderOverlay(RenderGameOverlayEvent event){
        if(Utils.getEnabled("无覆盖")){
            if(event.isCancelable()){
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void OnRenderOverlay(FOVModifierEvent event){
        if(Utils.getEnabled("高视距")){
            event.setNewfov(300F);
        }
    }

    @SubscribeEvent
    public static void OnRenderHand(RenderHandEvent event){
        if(Utils.getEnabled("无手渲染")){
            if(event.isCancelable()){
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void OnRegisterCommands(RegisterClientCommandsEvent event){
        event.getDispatcher().register(Commands.literal("config").then(Commands.argument("type",StringArgumentType.string()).then(Commands.argument("value", FloatArgumentType.floatArg()).executes(context -> {
            String type = StringArgumentType.getString(context,"type");
            float value = FloatArgumentType.getFloat(context,"value");
            if(type.equalsIgnoreCase("boatfly") || type.equalsIgnoreCase("bf")){
                BF = value;
            }
            else{
                timer = value;
            }
            Utils.saveConfig();
            return 0;
        }))));
    }
}
