package com.clatix.modules.modules.player;

import com.clatix.event.Event;
import com.clatix.modules.Module;

public class AutoReconnect extends Module {
    public AutoReconnect() {
        super("Auto Reconnect", "Automatically reconnects on disconnect", Category.PLAYER);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onEvent(Event event) {}
}