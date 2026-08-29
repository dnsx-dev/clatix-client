package com.clatix.event.events;

import com.clatix.event.Event;
import net.minecraft.client.gui.DrawContext;

public class RenderEvent implements Event {
    private DrawContext context;

    public RenderEvent(DrawContext context) {
        this.context = context;
    }

    public DrawContext getContext() { return context; }
}