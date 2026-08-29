package com.clatix.event;

import com.clatix.modules.Module;
import com.clatix.modules.ModuleManager;
import com.clatix.event.events.TickEvent;
import com.clatix.event.events.RenderEvent;
import net.minecraft.client.gui.DrawContext;

public class EventManager {
    private static EventManager instance;

    private EventManager() {}

    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public void init() {}

    public void onTick() {
        TickEvent event = new TickEvent();
        for (Module module : ModuleManager.getInstance().getEnabledModules()) {
            module.onEvent(event);
        }
    }

    public void onRender(DrawContext context) {
        RenderEvent event = new RenderEvent(context);
        for (Module module : ModuleManager.getInstance().getEnabledModules()) {
            module.onEvent(event);
        }
    }
}