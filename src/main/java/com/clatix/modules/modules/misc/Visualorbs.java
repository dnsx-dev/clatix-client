package com.clatix.modules.modules.misc;

import com.clatix.event.Event;
import com.clatix.event.events.RenderEvent;
import com.clatix.modules.Module;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;
import java.awt.Color;

public class VisualOrbs extends Module {
    public VisualOrbs() {
        super("VisualOrbs", "Renders health orbs around players", Category.MISC);
    }

    @Override public void onEnable() {}
    @Override public void onDisable() {}

    @Override
    public void onEvent(Event event) {
        if (!(event instanceof RenderEvent)) return;
        if (mc.world == null || mc.player == null) return;
        for (PlayerEntity player : mc.world.getPlayers()) {
            if (player == mc.player) continue;
            float health = player.getHealth() / player.getMaxHealth();
            Color color = Color.getHSBColor(health * 0.3f, 0.8f, 1.0f);
            Vec3d pos = player.getPos();
            mc.world.addParticle(
                new DustParticleEffect(new Vector3f(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f), 1.0f),
                pos.x, pos.y + 1.5, pos.z, 0, 0, 0
            );
        }
    }
}
