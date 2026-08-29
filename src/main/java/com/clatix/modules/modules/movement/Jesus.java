package com.clatix.modules.modules.movement;

import com.clatix.event.Event;
import com.clatix.event.events.TickEvent;
import com.clatix.modules.Module;
import net.minecraft.block.FluidBlock;
import net.minecraft.util.math.BlockPos;

public class Jesus extends Module {
    public Jesus() {
        super("Jesus", "Walk on water", Category.MOVEMENT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onEvent(Event event) {
        if (!(event instanceof TickEvent)) return;
        if (mc.player == null || mc.world == null) return;

        BlockPos pos = mc.player.getBlockPos();
        if (mc.world.getBlockState(pos).getBlock() instanceof FluidBlock) {
            mc.player.setVelocity(mc.player.getVelocity().x, 0.1, mc.player.getVelocity().z);
            mc.player.setOnGround(true);
        }
    }
}