package com.clatix.utils;

import net.minecraft.client.MinecraftClient;

public class FontUtils {
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public void init() {}

    public int getStringWidth(String text) {
        return mc.textRenderer.getWidth(text);
    }

    public int getHeight() {
        return mc.textRenderer.fontHeight;
    }

    public void drawString(String text, float x, float y, int color) {
        mc.textRenderer.draw(text, x, y, color, false);
    }

    public void drawStringWithShadow(String text, float x, float y, int color) {
        mc.textRenderer.draw(text, x, y, color, true);
    }

    public void drawCenteredString(String text, float x, float y, int color) {
        mc.textRenderer.draw(text, x - getStringWidth(text) / 2.0f, y, color, false);
    }

    public void drawCenteredStringWithShadow(String text, float x, float y, int color) {
        mc.textRenderer.draw(text, x - getStringWidth(text) / 2.0f, y, color, true);
    }
}