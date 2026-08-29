package com.clatix.modules.modules.combat;

import com.clatix.event.Event;
import com.clatix.event.events.TickEvent;
import com.clatix.modules.Module;
import net.minecraft.block.Blocks;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class AutoTrap extends Module {
    public AutoTrap() {
        super("Auto Trap", "Traps enemies in obsidian", Category.COMBAT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override    public void onEvent(Event event) {
        if (!(event instanceof TickEvent)) return;
        if (mc.player == null || mc.world == null) return;

        BlockPos playerPos = mc.player.getBlockPos();
        BlockPos[] trapBlocks = {
            playerPos.add(1, 0, 0), playerPos.add(-1, 0, 0),
            playerPos.add(0, 0, 1), playerPos.add(0, 0, -1),
            playerPos.add(1, 1, 0), playerPos.add(-1, 1, 0),
            playerPos.add(0, 1, 1), playerPos.add(0, 1, -1),
            playerPos.add(0, 2, 0)
        };

        for (BlockPos pos : trapBlocks) {
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