package com.clatix.gui;

import com.clatix.modules.Module;
import com.clatix.modules.ModuleManager;
import com.clatix.utils.RenderUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Panel {
    private final MinecraftClient mc = MinecraftClient.getInstance();
    private final Module.Category category;
    private int x, y, width, height;
    private boolean dragging = false;
    private double dragX, dragY;
    private boolean expanded = true;
    private final List<ModuleButton> buttons = new ArrayList<>();

    public Panel(Module.Category category, int x, int y, int width, int height) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        int buttonY = y + 22;
        for (Module module : ModuleManager.getInstance().getModules()) {
            if (module.getCategory() == category) {
                buttons.add(new ModuleButton(module, x + 5, buttonY, width - 10, 20));
                buttonY += 22;
            }
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (dragging) {
            x = (int)(mouseX - dragX);
            y = (int)(mouseY - dragY);
            x = Math.max(0, Math.min(x, context.getScaledWindowWidth() - width));
            y = Math.max(0, Math.min(y, context.getScaledWindowHeight() - height));
        }

        // Background and border (sharp corners)
        RenderUtils.drawRect(context, x, y, x + width, y + 20, new Color(13, 13, 13, 240).getRGB());
        RenderUtils.drawRect(context, x, y + 20, x + width, y + height, new Color(13, 13, 13, 230).getRGB());
        RenderUtils.drawBorder(context, x, y, x + width, y + height, 1, new Color(30, 30, 30).getRGB());

        // Category name
        context.drawText(mc.textRenderer, category.getName(), x + 6, y + 5, 0x00D4FF, false);

        // Expand/collapse arrow
        context.drawText(mc.textRenderer, expanded ? "▼" : "▶", x + width - 16, y + 4, 0x7B2FBE, false);

        if (expanded) {
            for (ModuleButton btn : buttons) {
                btn.render(context, mouseX, mouseY, delta);
            }
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + 20) {
            if (mouseX >= x + width - 20 && mouseX <= x + width) {
                expanded = !expanded;
            } else {
                dragging = true;
                dragX = mouseX - x;
                dragY = mouseY - y;
            }
            return;
        }

        if (expanded) {
            for (ModuleButton btn : buttons) {
                if (btn.isMouseOver(mouseX, mouseY)) {
                    btn.mouseClicked(mouseX, mouseY, button);
                    return;
                }
            }
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        dragging = false;
        for (ModuleButton btn : buttons) {
            btn.mouseReleased(mouseX, mouseY, button);
        }
    }

    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
