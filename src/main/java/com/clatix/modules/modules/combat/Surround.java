package com.clatix.modules.modules.combat;

import com.clatix.event.Event;
import com.clatix.event.events.TickEvent;
import com.clatix.modules.Module;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class Surround extends Module {
    public Surround() {
        super("Surround", "Places obsidian around you", Category.COMBAT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onEvent(Event event) {
        if (!(event instanceof TickEvent)) return;
        if (mc.player == null || mc.world == null) return;

        BlockPos playerPos = mc.player.getBlockPos();
        BlockPos[] surroundBlocks = {
            playerPos.add(1, -1, 0), playerPos.add(-1, -1, 0),
            playerPos.add(0, -1, 1), playerPos.add(0, -1, -1)
        };

        for (BlockPos pos : surroundBlocks) {
            if (mc.world.getBlockState(pos).isAir()) {
                BlockHitResult hit = new BlockHitResult(
                    Vec3d.ofCenter(pos),
                    Direction.UP,
                    pos,
                    false
                );
                mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
                break;
            }
        }
    }
}