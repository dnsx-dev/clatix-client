package com.clatix;

import com.clatix.gui.ClickGui;
import com.clatix.gui.HudRenderer;
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

    private KeyBinding clickGuiKey;

    @Override
    public void onInitializeClient() {
        INSTANCE = this;
        LOGGER.info("clatix loaded.");

        clickGuiKey = new KeyBinding(
            "key.clatix.clickgui",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_CONTROL,
            "category.clatix"
        );
        KeyBindingHelper.registerKeyBinding(clickGuiKey);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (clickGuiKey.wasPressed()) {
                if (client.currentScreen instanceof ClickGui) {
                    client.setScreen(null);
                } else {
                    client.setScreen(new ClickGui());
                }
            }
        });

        HudRenderCallback.EVENT.register(new HudRenderer());
    }
}
