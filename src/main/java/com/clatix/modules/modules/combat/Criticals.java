package com.clatix.modules.modules.combat;

import com.clatix.event.Event;
import com.clatix.event.events.PacketEvent;
import com.clatix.modules.Module;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class Criticals extends Module {
    public Criticals() {
        super("Criticals", "Makes all hits critical hits", Category.COMBAT);
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
        if (mc.player == null || !mc.player.isOnGround()) return;

        mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(
            mc.player.getX(), mc.player.getY() + 0.1, mc.player.getZ(), false));
        mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(
            mc.player.getX(), mc.player.getY(), mc.player.getZ(), false));
        mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(
            mc.player.getX(), mc.player.getY() + 0.001, mc.player.getZ(), false));
    }
}