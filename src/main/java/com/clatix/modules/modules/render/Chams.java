package com.clatix.modules.modules.render;

import com.clatix.event.Event;
import com.clatix.modules.Module;

public class Chams extends Module {
    public Chams() {
        super("Chams", "Makes entities visible through walls", Category.RENDER);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onEvent(Event event) {}
}