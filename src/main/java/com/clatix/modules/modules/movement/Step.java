package com.clatix.modules.modules.movement;

import com.clatix.event.Event;
import com.clatix.event.events.TickEvent;
import com.clatix.modules.Module;

public class Step extends Module {
    private float height = 1.0f;

    public Step() {
        super("Step", "Steps up blocks automatically", Category.MOVEMENT);
    }

    @Override
    public void onEnable() {
        if (mc.player != null) {
            mc.player.stepHeight = height;
        }
    }

    @Override
    public void onDisable() {
        if (mc.player != null) {
            mc.player.stepHeight = 0.6f;
        }
    }

    @Override
    public void onEvent(Event event) {
        if (!(event instanceof TickEvent)) return;
        if (mc.player == null) return;
        mc.player.stepHeight = height;
    }
}