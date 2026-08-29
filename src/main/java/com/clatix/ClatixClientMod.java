package com.clatix;

import com.clatix.client.Client;
import com.clatix.modules.ModuleManager;
import com.clatix.event.EventManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClatixClientMod implements ClientModInitializer {
    public static final String MOD_ID = "clatix";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static ClatixClientMod INSTANCE;
    private Client client;
    private ModuleManager moduleManager;
    private EventManager eventManager;
    private KeyBinding clickGuiKey;

    @Override
    public void onInitializeClient() {
        INSTANCE = this;
        LOGGER.info("Initializing Clatix Client for Fabric 1.21.1");

        this.client = new Client();
        this.moduleManager = new ModuleManager();
        this.eventManager = new EventManager();

        moduleManager.init();
        eventManager.init();

        clickGuiKey = new KeyBinding(
            "key.clatix.clickgui",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_CONTROL,
            "category.clatix"
        );
        KeyBindingHelper.registerKeyBinding(clickGuiKey);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (clickGuiKey.wasPressed()) {
                client.setScreen(new com.clatix.gui.ClickGui());
            }
            eventManager.onTick();
        });

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            eventManager.onRender(drawContext);
        });

        LOGGER.info("Clatix Client initialized successfully");
    }

    public Client getClient() { return client; }
    public ModuleManager getModuleManager() { return moduleManager; }
    public EventManager getEventManager() { return eventManager; }
    public KeyBinding getClickGuiKey() { return clickGuiKey; }
}