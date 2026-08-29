package com.clatix.modules.modules.render;

import com.clatix.event.Event;
import com.clatix.event.events.RenderEvent;
import com.clatix.modules.Module;
import com.clatix.utils.RenderUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import java.awt.*;

public class Tracers extends Module {
    public Tracers() {
        super("Tracers", "Draws lines to entities", Category.RENDER);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onEvent(Event event) {
        if (!(event instanceof RenderEvent)) return;
        if (mc.world == null || mc.player == null) return;

        for (Entity entity : mc.world.getEntities()) {
            if (entity == mc.player) continue;
            if (entity instanceof PlayerEntity) {
                RenderUtils.drawTracer(entity, new Color(255, 0, 0, 200));
            }
        }
    }
}