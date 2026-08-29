package com.clatix.modules.modules.combat;

import com.clatix.event.Event;
import com.clatix.event.events.TickEvent;
import com.clatix.modules.Module;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class AutoCrystal extends Module {
    public AutoCrystal() {
        super("Auto Crystal", "Places crystals on nearby obsidian", Category.COMBAT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onEvent(Event event) {
        if (!(event instanceof TickEvent)) return;
        if (mc.player == null || mc.world == null) return;

        if (mc.player.getMainHandStack().getItem() != Items.END_CRYSTAL) return;

        BlockPos pos = mc.player.getBlockPos().up();
        if (!mc.world.getBlockState(pos).isAir()) return;

        BlockHitResult hit = new BlockHitResult(
            Vec3d.ofCenter(pos),
            Direction.UP,
            pos,
            false
        );
        mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
    }
}