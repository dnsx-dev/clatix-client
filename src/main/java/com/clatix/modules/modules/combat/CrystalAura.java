package com.clatix.modules.modules.combat;

import com.clatix.event.Event;
import com.clatix.event.events.TickEvent;
import com.clatix.modules.Module;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class CrystalAura extends Module {
    public CrystalAura() {
        super("Crystal Aura", "Automatically places and breaks end crystals", Category.COMBAT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onEvent(Event event) {
        if (!(event instanceof TickEvent)) return;
        if (mc.player == null || mc.world == null) return;

        BlockPos pos = mc.player.getBlockPos().up();
        if (mc.world.getBlockState(pos).isAir()) {
            int slot = findCrystal();
            if (slot != -1) {
                int oldSlot = mc.player.getInventory().selectedSlot;
                mc.player.getInventory().selectedSlot = slot;
                BlockHitResult hit = new BlockHitResult(
                    Vec3d.ofCenter(pos),
                    Direction.UP,
                    pos,
                    false
                );
                mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
                mc.player.getInventory().selectedSlot = oldSlot;
            }
        }

        for (EndCrystalEntity crystal : mc.world.getEntitiesByClass(EndCrystalEntity.class,
            new net.minecraft.util.math.Box(mc.player.getPos().add(-5, -5, -5), mc.player.getPos().add(5, 5, 5)),
            e -> mc.player.distanceTo(e) < 5)) {
            mc.interactionManager.attackEntity(mc.player, crystal);
        }
    }

    private int findCrystal() {
        for (int i = 0; i < 9; i++) {
            if (mc.player.getInventory().getStack(i).getItem() == Items.END_CRYSTAL) {
                return i;
            }
        }
        return -1;
    }
}