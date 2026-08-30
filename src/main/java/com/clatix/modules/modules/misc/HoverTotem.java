package com.clatix.modules.modules.misc;

import com.clatix.event.Event;
import com.clatix.event.events.TickEvent;
import com.clatix.modules.Module;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class HoverTotem extends Module {
    public HoverTotem() {
        super("HoverTotem", "Auto-holds totem when low health", Category.MISC);
    }

    @Override public void onEnable() {}
    @Override public void onDisable() {}

    @Override
    public void onEvent(Event event) {
        if (!(event instanceof TickEvent)) return;
        if (mc.player == null) return;
        if (mc.player.getHealth() < 6.0f && mc.player.getOffHandStack().getItem() != Items.TOTEM_OF_UNDYING) {
            int slot = findTotem();
            if (slot != -1) {
                mc.player.getInventory().selectedSlot = slot;
                mc.player.setStackInHand(Hand.OFF_HAND, mc.player.getInventory().getStack(slot));
                mc.player.getInventory().setStack(slot, ItemStack.EMPTY);
            }
        }
    }

    private int findTotem() {
        for (int i = 0; i < 36; i++) {
            if (mc.player.getInventory().getStack(i).getItem() == Items.TOTEM_OF_UNDYING) return i;
        }
        return -1;
    }
}
