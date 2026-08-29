package com.clatix.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.awt.*;

public class RenderUtils {
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static void drawRect(DrawContext context, int x1, int y1, int x2, int y2, int color) {
        context.fill(RenderLayer.getGui(), x1, y1, x2, y2, color);
    }

    public static void drawBorder(DrawContext context, int x1, int y1, int x2, int y2, int thickness, int color) {
        drawRect(context, x1, y1, x2, y1 + thickness, color);
        drawRect(context, x1, y2 - thickness, x2, y2, color);
        drawRect(context, x1, y1 + thickness, x1 + thickness, y2 - thickness, color);
        drawRect(context, x2 - thickness, y1 + thickness, x2, y2 - thickness, color);
    }

    public static void drawString(String text, int x, int y, int color) {
        mc.textRenderer.draw(text, x, y, color, false);
    }

    public static void drawESPBox(Entity entity, Color color) {
        // Box rendering would use world rendering context
        // Implementation would use BufferBuilder
    }

    public static void drawTracer(Entity entity, Color color) {
        // Tracer rendering implementation
    }

    public static void drawNametag(Entity entity, String text, Color color) {
        // Nametag rendering implementation
    }
}