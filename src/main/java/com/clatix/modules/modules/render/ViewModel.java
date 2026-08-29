package com.clatix.modules.modules.render;

import com.clatix.event.Event;
import com.clatix.modules.Module;

public class ViewModel extends Module {
    public ViewModel() {
        super("View Model", "Changes item view", Category.RENDER);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onEvent(Event event) {}
}