package com.clatix.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

public class WorldUtils {
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static BlockState getBlockState(BlockPos pos) {
        return mc.world.getBlockState(pos);
    }

    public static Block getBlock(BlockPos pos) {
        return getBlockState(pos).getBlock();
    }

    public static boolean isAir(BlockPos pos) {
        return mc.world.getBlockState(pos).isAir();
    }

    public static boolean isReplaceable(BlockPos pos) {
        return mc.world.getBlockState(pos).getBlock().getDefaultState().isReplaceable();
    }

    public static double getDistanceToBlock(BlockPos pos) {
        return mc.player.distanceTo(pos.toCenterPos());
    }
}