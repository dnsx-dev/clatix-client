package com.clatix.gui;

import com.clatix.modules.Module;
import com.clatix.modules.ModuleManager;
import com.clatix.utils.RenderUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HudRenderer implements HudRenderCallback {
    private final MinecraftClient mc = MinecraftClient.getInstance();

    @Override
    public void onHudRender(DrawContext context, float tickDelta) {
        if (mc.player == null || mc.options == null) return;

        List<Module> enabledModules = ModuleManager.getInstance().getModules().stream()
                .filter(Module::isEnabled)
                .sorted(Comparator.comparing(Module::getName))
                .collect(Collectors.toList());

        if (enabledModules.isEmpty()) return;

        int x = mc.getWindow().getScaledWidth() - 10;
        int y = 10;
        int width = 0;
        int lineHeight = 12;

        // Measure max width
        for (Module m : enabledModules) {
            String text = "■ " + m.getName();
            int w = mc.textRenderer.getWidth(text);
            if (w > width) width = w;
        }
        width += 16; // padding

        // Background
        RenderUtils.drawRect(context, x - width, y - 2, x, y + enabledModules.size() * lineHeight + 4,
                new Color(13, 13, 13, 200).getRGB());
        RenderUtils.drawBorder(context, x - width, y - 2, x, y + enabledModules.size() * lineHeight + 4,
                1, new Color(30, 30, 30).getRGB());

        // Title
        context.drawText(mc.textRenderer, "clatix", x - width + 4, y, 0x00D4FF, false);
        y += 14;

        // Modules
        for (Module m : enabledModules) {
            String text = "■ " + m.getName();
            context.drawText(mc.textRenderer, text, x - width + 6, y, 0xFFFFFF, false);
            y += lineHeight;
        }
    }
}
