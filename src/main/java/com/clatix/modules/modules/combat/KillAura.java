package com.clatix.modules.modules.combat;

import com.clatix.event.Event;
import com.clatix.event.events.TickEvent;
import com.clatix.modules.Module;
import com.clatix.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;

import java.util.List;

public class KillAura extends Module {
    private double range = 4.5;
    private int speed = 10;
    private boolean autoRotate = true;
    private int ticks = 0;

    public KillAura() {
        super("Kill Aura", "Automatically attacks nearby entities", Category.COMBAT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onEvent(Event event) {
        if (!(event instanceof TickEvent)) return;
        if (mc.player == null || mc.world == null) return;

        ticks++;
        if (ticks < 20 / speed) return;
        ticks = 0;

        List<Entity> targets = mc.world.getEntitiesByClass(LivingEntity.class,
            new Box(mc.player.getPos().add(-range, -range, -range),
                    mc.player.getPos().add(range, range, range)),
            e -> e != mc.player && e instanceof PlayerEntity);

        if (targets.isEmpty()) return;

        Entity target = targets.stream()
            .min((e1, e2) -> Double.compare(mc.player.distanceTo(e1), mc.player.distanceTo(e2)))
            .orElse(null);

        if (target == null) return;

        if (autoRotate) {
            PlayerUtils.rotateToEntity(target, true);
        }

        mc.interactionManager.attackEntity(mc.player, target);
        mc.player.swingHand(mc.player.getActiveHand());
    }
}