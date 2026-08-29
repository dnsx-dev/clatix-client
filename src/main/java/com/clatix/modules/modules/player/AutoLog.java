package com.clatix.modules.modules.player;

import com.clatix.event.Event;
import com.clatix.event.events.TickEvent;
import com.clatix.modules.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class AutoLog extends Module {
    private double health = 4.0;
    private double distance = 10.0;

    public AutoLog() {
        super("Auto Log", "Automatically disconnects when health is low", Category.PLAYER);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onEvent(Event event) {
        if (!(event instanceof TickEvent)) return;
        if (mc.player == null || mc.world == null) return;

        if (mc.player.getHealth() <= health) {
            mc.player.networkHandler.getConnection().disconnect(net.minecraft.text.Text.literal("AutoLog"));
            return;
        }

        for (Entity entity : mc.world.getEntities()) {
            if (entity == mc.player) continue;
            if (entity instanceof PlayerEntity) {
                if (mc.player.distanceTo(entity) < distance) {
                    mc.player.networkHandler.getConnection().disconnect(net.minecraft.text.Text.literal("AutoLog"));
                    return;
                }
            }
        }
    }
}