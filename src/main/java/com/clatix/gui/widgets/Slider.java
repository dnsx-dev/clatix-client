package com.clatix.gui.widgets;

import com.clatix.utils.RenderUtils;
import com.clatix.utils.MathUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.util.function.Consumer;

public class Slider {
    private MinecraftClient mc = MinecraftClient.getInstance();
    private String label;
    private double value;
    private double min, max;
    private double step;
    private int x, y, width;
    private Consumer<Double> onChange;
    private boolean dragging = false;

    public Slider(String label, double value, double min, double max, double step, Consumer<Double> onChange) {
        this.label = label;
        this.value = value;
        this.min = min;
        this.max = max;
        this.step = step;
        this.onChange = onChange;
    }

    public void render(DrawContext context, int x, int y, int width, int mouseX, int mouseY) {
        this.x = x;
        this.y = y;
        this.width = width;

        RenderUtils.drawRect(context, x, y + 6, x + width, y + 10, new Color(50, 50, 50, 255).getRGB());
        
        double percent = (value - min) / (max - min);
        int fillWidth = (int)(percent * width);
        RenderUtils.drawRect(context, x, y + 6, x + fillWidth, y + 10, new Color(0, 150, 255, 255).getRGB());
        
        int thumbX = x + fillWidth - 4;
        RenderUtils.drawRect(context, thumbX - 2, y + 2, thumbX + 6, y + 14, new Color(200, 200, 200, 255).getRGB());
        RenderUtils.drawBorder(context, thumbX - 2, y + 2, thumbX + 6, y + 14, 1, new Color(100, 100, 100, 255).getRGB());
        
        context.drawText(mc.textRenderer, label + ": " + String.format("%.1f", value), x + 2, y - 3, 0xFFFFFF, false);

        if (dragging) {
            double newValue = MathUtils.clamp(min + (mouseX - x) / (double)width * (max - min), min, max);
            if (step > 0) {
                newValue = Math.round(newValue / step) * step;
            }
            value = newValue;
            if (onChange != null) onChange.accept(value);
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + 16 && button == 0) {
            dragging = true;
        }
    }

    public void mouseReleased() {
        dragging = false;
    }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
}