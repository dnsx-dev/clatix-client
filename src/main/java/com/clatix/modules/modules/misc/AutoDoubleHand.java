package com.clatix.modules.modules.misc;

import com.clatix.event.Event;
import com.clatix.event.events.TickEvent;
import com.clatix.modules.Module;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class AutoDoubleHand extends Module {
    public AutoDoubleHand() {
        super("AutoDoubleHand", "Smart main/offhand swap", Category.MISC);
    }

    @Override public void onEnable() {}
    @Override public void onDisable() {}

    @Override
    public void onEvent(Event event) {
        if (!(event instanceof TickEvent)) return;
        if (mc.player == null) return;
        ItemStack main = mc.player.getMainHandStack();
        ItemStack off = mc.player.getOffHandStack();
        if (main.isEmpty() && !off.isEmpty()) {
            mc.player.setStackInHand(Hand.MAIN_HAND, off);
            mc.player.setStackInHand(Hand.OFF_HAND, ItemStack.EMPTY);
        }
    }
}
