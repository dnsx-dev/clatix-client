package com.clatix.modules.modules.player;

import com.clatix.event.Event;
import com.clatix.event.events.TickEvent;
import com.clatix.modules.Module;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class AutoEat extends Module {
    public AutoEat() {
        super("Auto Eat", "Automatically eats food", Category.PLAYER);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onEvent(Event event) {
        if (!(event instanceof TickEvent)) return;
        if (mc.player == null) return;

        if (mc.player.getHungerManager().getFoodLevel() < 18) {
            int slot = findFood();
            if (slot != -1) {
                int oldSlot = mc.player.getInventory().selectedSlot;
                mc.player.getInventory().selectedSlot = slot;
                mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
                mc.player.getInventory().selectedSlot = oldSlot;
            }
        }
    }

    private int findFood() {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.player.getInventory().getStack(i);
            if (stack.getItem().getFoodComponent() != null) {
                return i;
            }
        }
        return -1;
    }
}