package com.clatix.modules.modules.render;

import com.clatix.event.Event;
import com.clatix.event.events.RenderEvent;
import com.clatix.modules.Module;
import com.clatix.utils.RenderUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import java.awt.*;

public class ESP extends Module {
    public ESP() {
        super("ESP", "Highlights entities through walls", Category.RENDER);
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
                RenderUtils.drawESPBox(entity, new Color(0, 150, 255, 150));
            }
        }
    }
}