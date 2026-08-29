package com.clatix.client;

import com.clatix.utils.FontUtils;

public class Client {
    private Config config;
    private FontUtils fontUtils;
    private String prefix = ".";
    private boolean initialized = false;
    private long startTime;

    public Client() {
        this.config = new Config();
        this.fontUtils = new FontUtils();
        this.startTime = System.currentTimeMillis();
    }

    public void start() {
        initialized = true;
        config.load();
        fontUtils.init();
    }

    public void stop() {
        config.save();
        initialized = false;
    }

    public Config getConfig() { return config; }
    public FontUtils getFontUtils() { return fontUtils; }
    public String getPrefix() { return prefix; }
    public void setPrefix(String prefix) { this.prefix = prefix; }
    public boolean isInitialized() { return initialized; }
    public long getStartTime() { return startTime; }
    public long getUptime() { return System.currentTimeMillis() - startTime; }
}