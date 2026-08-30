package com.clatix.gui;

import com.clatix.modules.Module;
import com.clatix.modules.ModuleManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ClickGui extends Screen {
    private List<Panel> panels = new ArrayList<>();

    public ClickGui() {
        super(Text.literal("clatix"));
        int x = 10;
        for (Module.Category category : Module.Category.values()) {
            panels.add(new Panel(category, x, 30, 140, 280));
            x += 150;
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        for (Panel panel : panels) {
            panel.render(context, mouseX, mouseY, delta);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (Panel panel : panels) {
            if (panel.isMouseOver(mouseX, mouseY)) {
                panel.mouseClicked(mouseX, mouseY, button);
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (Panel panel : panels) {
            panel.mouseReleased(mouseX, mouseY, button);
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
