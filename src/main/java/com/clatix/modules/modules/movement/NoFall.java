package com.clatix.modules.modules.movement;

import com.clatix.event.Event;
import com.clatix.event.events.PacketEvent;
import com.clatix.modules.Module;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class NoFall extends Module {
    public NoFall() {
        super("No Fall", "Prevents fall damage", Category.MOVEMENT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onEvent(Event event) {
        if (!(event instanceof PacketEvent)) return;
        PacketEvent pe = (PacketEvent) event;
        if (!(pe.getPacket() instanceof PlayerMoveC2SPacket)) return;

        PlayerMoveC2SPacket packet = (PlayerMoveC2SPacket) pe.getPacket();
        if (mc.player == null) return;
        if (mc.player.fallDistance > 3.0f) {
            packet.setOnGround(true);
        }
    }
}