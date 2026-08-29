package com.clatix.modules.modules.movement;

import com.clatix.event.Event;
import com.clatix.event.events.TickEvent;
import com.clatix.modules.Module;

public class Speed extends Module {
    private double speed = 1.2;

    public Speed() {
        super("Speed", "Increases movement speed", Category.MOVEMENT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onEvent(Event event) {
        if (!(event instanceof TickEvent)) return;
        if (mc.player == null || mc.world == null) return;
        if (mc.player.isOnGround()) {
            mc.player.setVelocity(mc.player.getVelocity().x * speed,
                                 mc.player.getVelocity().y,
                                 mc.player.getVelocity().z * speed);
        }
    }
}