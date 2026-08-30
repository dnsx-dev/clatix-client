package com.clatix.gui;

import com.clatix.modules.Module;
import com.clatix.utils.RenderUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.InputUtil;

import java.awt.*;

public class ModuleButton {
    private final MinecraftClient mc = MinecraftClient.getInstance();
    private final Module module;
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
        Color bg = module.isEnabled() ? new Color(0, 212, 255, 60) :
                (hovered ? new Color(255, 255, 255, 20) : new Color(20, 20, 20, 200));
        RenderUtils.drawRect(context, x, y, x + width, y + height, bg.getRGB());
        RenderUtils.drawBorder(context, x, y, x + width, y + height, 1, new Color(40, 40, 40).getRGB());

        String display = module.getName();
        if (module.getKey() != 0) {
            display += " [" + InputUtil.getKeyName(module.getKey()) + "]";
        }
        int textColor = module.isEnabled() ? 0x00D4FF : 0xAAAAAA;
        context.drawText(mc.textRenderer, display, x + 4, y + height / 2 - 4, textColor, false);
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isMouseOver(mouseX, mouseY)) {
            if (button == 0) {
                module.toggle();
            } else if (button == 1) {
                // TODO: open settings panel
            }
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {}

    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
