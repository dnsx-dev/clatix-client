package com.clatix.utils;

public class TimeUtils {
    private long lastTime = 0;

    public boolean passed(long ms) {
        return System.currentTimeMillis() - lastTime >= ms;
    }

    public void reset() {
        lastTime = System.currentTimeMillis();
    }

    public void setLastTime(long time) {
        this.lastTime = time;
    }

    public long getLastTime() {
        return lastTime;
    }

    public long getElapsed() {
        return System.currentTimeMillis() - lastTime;
    }

    public static String formatTime(long ms) {
        long seconds = ms / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        seconds %= 60;
        minutes %= 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}