package com.clatix.modules.modules.misc;

import com.clatix.event.Event;
import com.clatix.event.events.PacketEvent;
import com.clatix.modules.Module;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class JumpReset extends Module {
    public JumpReset() {
        super("JumpReset", "Resets fall distance when jumping", Category.MISC);
    }

    @Override public void onEnable() {}
    @Override public void onDisable() {}

    @Override
    public void onEvent(Event event) {
        if (!(event instanceof PacketEvent)) return;
        if (mc.player != null && mc.player.jump()) {
            mc.player.fallDistance = 0;
        }
    }
}
