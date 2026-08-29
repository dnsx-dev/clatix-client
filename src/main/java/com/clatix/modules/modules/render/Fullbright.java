package com.clatix.modules.modules.render;

import com.clatix.event.Event;
import com.clatix.modules.Module;

public class Fullbright extends Module {
    public Fullbright() {
        super("Fullbright", "Increases brightness", Category.RENDER);
    }

    @Override
    public void onEnable() {
        if (mc.world != null) {
            mc.options.getGamma().setValue(10.0);
        }
    }

    @Override
    public void onDisable() {
        mc.options.getGamma().setValue(0.5);
    }

    @Override
    public void onEvent(Event event) {}
}