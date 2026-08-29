package com.clatix.modules.modules.player;

import com.clatix.event.Event;
import com.clatix.event.events.PacketEvent;
import com.clatix.modules.Module;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class AntiHunger extends Module {
    public AntiHunger() {
        super("Anti Hunger", "Prevents hunger loss", Category.PLAYER);
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
        if (mc.player.age % 2 == 0) {
            packet.setOnGround(true);
        }
    }
}