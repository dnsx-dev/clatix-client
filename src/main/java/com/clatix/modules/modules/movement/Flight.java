package com.clatix.modules.modules.movement;

import com.clatix.event.Event;
import com.clatix.event.events.TickEvent;
import com.clatix.modules.Module;

public class Flight extends Module {
    private float speed = 0.5f;

    public Flight() {
        super("Flight", "Allows flight", Category.MOVEMENT);
    }

    @Override
    public void onEnable() {
        if (mc.player != null) {
            mc.player.getAbilities().allowFlying = true;
            mc.player.getAbilities().flying = true;
        }
    }

    @Override
    public void onDisable() {
        if (mc.player != null) {
            mc.player.getAbilities().allowFlying = false;
            mc.player.getAbilities().flying = false;
        }
    }

    @Override
    public void onEvent(Event event) {
        if (!(event instanceof TickEvent)) return;
        if (mc.player == null) return;
        if (mc.player.getAbilities().flying) {
            mc.player.getAbilities().setFlySpeed(speed / 10.0f);
        }
    }
}