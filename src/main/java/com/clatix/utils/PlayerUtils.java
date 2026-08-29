package com.clatix.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class PlayerUtils {
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static float[] getRotations(Vec3d target) {
        Vec3d eyes = mc.player.getEyePos();
        Vec3d diff = target.subtract(eyes);
        
        double diffX = diff.x;
        double diffY = diff.y;
        double diffZ = diff.z;
        
        double dist = MathHelper.sqrt((float)(diffX * diffX + diffZ * diffZ));
        float yaw = (float)(MathHelper.atan2(diffZ, diffX) * 180.0 / Math.PI) - 90.0f;
        float pitch = (float)(-MathHelper.atan2(diffY, dist) * 180.0 / Math.PI);
        
        return new float[]{yaw, pitch};
    }

    public static float[] getRotations(Entity target) {
        Vec3d pos = target.getPos().add(0, target.getHeight() / 2, 0);
        return getRotations(pos);
    }

    public static void rotateToEntity(Entity target, boolean instant) {
        float[] rotations = getRotations(target);
        if (instant) {
            mc.player.setYaw(rotations[0]);
            mc.player.setPitch(rotations[1]);
        } else {
            mc.player.setYaw(mc.player.getYaw() + MathHelper.wrapDegrees(rotations[0] - mc.player.getYaw()) / 10);
            mc.player.setPitch(mc.player.getPitch() + MathHelper.wrapDegrees(rotations[1] - mc.player.getPitch()) / 10);
        }
    }

    public static double getDistanceToEntity(Entity entity) {
        return mc.player.distanceTo(entity);
    }

    public static boolean isInRange(Entity entity, double range) {
        return getDistanceToEntity(entity) <= range;
    }
}