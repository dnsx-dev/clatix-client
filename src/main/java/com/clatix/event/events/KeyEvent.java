package com.clatix.event.events;

import com.clatix.event.Event;

public class KeyEvent implements Event {
    private int keyCode;
    private boolean pressed;

    public KeyEvent(int keyCode, boolean pressed) {
        this.keyCode = keyCode;
        this.pressed = pressed;
    }

    public int getKeyCode() { return keyCode; }
    public boolean isPressed() { return pressed; }
}