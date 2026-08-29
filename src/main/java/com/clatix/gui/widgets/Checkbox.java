package com.clatix.gui.widgets;

import com.clatix.utils.RenderUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.util.function.Consumer;

public class Checkbox {
    private MinecraftClient mc = MinecraftClient.getInstance();
    private String label;
    private boolean checked;
    private int x, y;
    private Consumer<Boolean> onChange;

    public Checkbox(String label, boolean defaultChecked, Consumer<Boolean> onChange) {
        this.label = label;
        this.checked = defaultChecked;
        this.onChange = onChange;
    }

    public void render(DrawContext context, int x, int y) {
        this.x = x;
        this.y = y;
        
        int color = checked ? new Color(0, 150, 255, 255).getRGB() : new Color(50, 50, 50, 255).getRGB();
        RenderUtils.drawRect(context, x, y, x + 14, y + 14, color);
        RenderUtils.drawBorder(context, x, y, x + 14, y + 14, 1, new Color(60, 60, 60, 255).getRGB());
        
        if (checked) {
            context.drawText(mc.textRenderer, "✓", x + 3, y + 1, 0xFFFFFF, false);
        }
        
        context.drawText(mc.textRenderer, label, x + 20, y + 2, 0xFFFFFF, false);
    }

    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (mouseX >= x && mouseX <= x + 14 && mouseY >= y && mouseY <= y + 14 && button == 0) {
            checked = !checked;
            if (onChange != null) onChange.accept(checked);
        }
    }

    public boolean isChecked() { return checked; }
    public void setChecked(boolean checked) { this.checked = checked; }
}