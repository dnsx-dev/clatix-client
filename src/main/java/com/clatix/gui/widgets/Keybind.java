package com.clatix.gui.widgets;

import com.clatix.utils.RenderUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.util.function.Consumer;

public class Keybind {
    private MinecraftClient mc = MinecraftClient.getInstance();
    private String label;
    private int keyCode;
    private int x, y, width;
    private Consumer<Integer> onChange;
    private boolean listening = false;

    public Keybind(String label, int defaultKey, Consumer<Integer> onChange) {
        this.label = label;
        this.keyCode = defaultKey;
        this.onChange = onChange;
    }

    public void render(DrawContext context, int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;

        Color color = listening ? new Color(60, 60, 60, 255) : new Color(40, 40, 40, 255);
        RenderUtils.drawRect(context, x, y, x + width, y + 18, color.getRGB());
        RenderUtils.drawBorder(context, x, y, x + width, y + 18, 1, new Color(60, 60, 60, 255).getRGB());

        String display = listening ? "Press key..." : (keyCode == 0 ? "None" : InputUtil.getKeyName(keyCode));
        context.drawText(mc.textRenderer, label + ": " + display, x + 4, y + 4, 0xFFFFFF, false);
    }

    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + 18 && button == 0) {
            listening = !listening;
        }
    }

    public void keyPressed(int keyCode) {
        if (!listening) return;
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            listening = false;
            return;
        }
        this.keyCode = keyCode;
        listening = false;
        if (onChange != null) onChange.accept(keyCode);
    }

    public int getKeyCode() { return keyCode; }
    public void setKeyCode(int keyCode) { this.keyCode = keyCode; }
    public boolean isListening() { return listening; }
}