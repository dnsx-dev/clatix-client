package com.clatix.modules.modules.movement;

import com.clatix.event.Event;
import com.clatix.event.events.TickEvent;
import com.clatix.modules.Module;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", "Automatically sprints", Category.MOVEMENT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onEvent(Event event) {
        if (!(event instanceof TickEvent)) return;
        if (mc.player == null) return;
        if (mc.player.input.movementForward > 0 && !mc.player.isSneaking()) {
            mc.player.setSprinting(true);
        }
    }
}