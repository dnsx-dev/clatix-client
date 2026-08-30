package com.clatix.modules.modules.misc;

import com.clatix.event.Event;
import com.clatix.modules.Module;

public class OreSim extends Module {
    public OreSim() {
        super("OreSim", "Predicts ore locations using seed", Category.MISC);
    }

    @Override public void onEnable() {}
    @Override public void onDisable() {}
    @Override public void onEvent(Event event) {}
}
