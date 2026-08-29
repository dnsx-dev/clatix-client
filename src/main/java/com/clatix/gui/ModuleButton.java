package com.clatix.gui;

import com.clatix.modules.Module;
import com.clatix.utils.RenderUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class ModuleButton {
    private MinecraftClient mc = MinecraftClient.getInstance();
    private Module module;
    private int x, y, width, height;
    private boolean hovered = false;

    public ModuleButton(Module module, int x, int y, int width, int height) {
        this.module = module;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        hovered = isMouseOver(mouseX, mouseY);
        Color color = module.isEnabled() ? new Color(0, 150, 255, 200) : 
                      (hovered ? new Color(60, 60, 60, 255) : new Color(40, 40, 40, 255));
        
        RenderUtils.drawRect(context, x, y, x + width, y + height, color.getRGB());
        RenderUtils.drawBorder(context, x, y, x + width, y + height, 1, new Color(60, 60, 60, 255).getRGB());
        
        String display = module.getName();
        if (module.getKey() != 0) {
            display += " [" + net.minecraft.client.util.InputUtil.getKeyName(module.getKey()) + "]";
        }
        context.drawText(mc.textRenderer, display, x + 4, y + height / 2 - 4, 0xFFFFFF, false);
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isMouseOver(mouseX, mouseY)) {
            if (button == 0) {
                module.toggle();
            }
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {}

    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}