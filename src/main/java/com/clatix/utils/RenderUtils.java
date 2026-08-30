package com.clatix.utils;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;

public class RenderUtils {
    public static void drawRect(DrawContext context, int x1, int y1, int x2, int y2, int color) {
        if (x2 < x1) { int tmp = x1; x1 = x2; x2 = tmp; }
        if (y2 < y1) { int tmp = y1; y1 = y2; y2 = tmp; }
        context.fill(RenderLayer.getGui(), x1, y1, x2, y2, color);
    }

    public static void drawBorder(DrawContext context, int x1, int y1, int x2, int y2, int thickness, int color) {
        drawRect(context, x1, y1, x2, y1 + thickness, color);
        drawRect(context, x1, y2 - thickness, x2, y2, color);
        drawRect(context, x1, y1 + thickness, x1 + thickness, y2 - thickness, color);
        drawRect(context, x2 - thickness, y1 + thickness, x2, y2 - thickness, color);
    }
}
