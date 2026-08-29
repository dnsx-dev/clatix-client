package com.clatix.mixins;

import com.clatix.event.events.KeyEvent;
import com.clatix.modules.Module;
import com.clatix.modules.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    @Inject(method = "handleKeyEvents", at = @At("HEAD"))
    private void onKeyPress(Window window, int keyCode, int scanCode, int action, int modifiers, CallbackInfo ci) {
        KeyEvent event = new KeyEvent(keyCode, action == 1);
        for (Module module : ModuleManager.getInstance().getModules()) {
            if (module.getKey() == keyCode && action == 1) {
                module.toggle();
            }
            module.onEvent(event);
        }
    }
}