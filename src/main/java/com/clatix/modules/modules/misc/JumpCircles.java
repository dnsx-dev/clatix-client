package com.clatix.modules.modules.misc;

import com.clatix.event.Event;
import com.clatix.modules.Module;

public class JumpCircles extends Module {
    public JumpCircles() {
        super("JumpCircles", "Draws circle on jump/land", Category.MISC);
    }

    @Override public void onEnable() {}
    @Override public void onDisable() {}
    @Override public void onEvent(Event event) {}
}
