package com.clatix.utils;

import java.awt.*;

public class ColorUtils {
    public static int toHex(Color color) {
        return (color.getAlpha() << 24) | (color.getRed() << 16) | (color.getGreen() << 8) | color.getBlue();
    }

    public static Color fromHex(int hex) {
        int a = (hex >> 24) & 0xFF;
        int r = (hex >> 16) & 0xFF;
        int g = (hex >> 8) & 0xFF;
        int b = hex & 0xFF;
        return new Color(r, g, b, a);
    }

    public static Color lerp(Color c1, Color c2, double t) {
        float r = (float)(c1.getRed() + (c2.getRed() - c1.getRed()) * t);
        float g = (float)(c1.getGreen() + (c2.getGreen() - c1.getGreen()) * t);
        float b = (float)(c1.getBlue() + (c2.getBlue() - c1.getBlue()) * t);
        float a = (float)(c1.getAlpha() + (c2.getAlpha() - c1.getAlpha()) * t);
        return new Color(r / 255.0f, g / 255.0f, b / 255.0f, a / 255.0f);
    }

    public static Color rainbow(float speed, float saturation, float brightness) {
        float hue = (System.currentTimeMillis() / 1000.0f * speed) % 1.0f;
        return Color.getHSBColor(hue, saturation, brightness);
    }
}