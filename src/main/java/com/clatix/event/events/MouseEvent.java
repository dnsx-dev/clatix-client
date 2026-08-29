package com.clatix.event.events;

import com.clatix.event.Event;

public class MouseEvent implements Event {
    private int x, y;
    private int button;
    private boolean pressed;

    public MouseEvent(int x, int y, int button, boolean pressed) {
        this.x = x;
        this.y = y;
        this.button = button;
        this.pressed = pressed;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getButton() { return button; }
    public boolean isPressed() { return pressed; }
}