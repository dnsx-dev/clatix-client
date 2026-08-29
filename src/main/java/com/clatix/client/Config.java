package com.clatix.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    private static final File CONFIG_FILE = new File("clatix_config.json");
    private Gson gson;
    private JsonObject config;

    public Config() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.config = new JsonObject();
    }

    public void load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                config = JsonParser.parseReader(reader).getAsJsonObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            createDefaultConfig();
        }
    }

    public void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            gson.toJson(config, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDefaultConfig() {
        config.addProperty("prefix", ".");
        config.addProperty("theme", "dark");
        config.add("modules", new JsonObject());
        save();
    }

    public JsonObject getConfig() { return config; }
    public String getString(String key, String defaultValue) {
        return config.has(key) ? config.get(key).getAsString() : defaultValue;
    }
    public int getInt(String key, int defaultValue) {
        return config.has(key) ? config.get(key).getAsInt() : defaultValue;
    }
    public double getDouble(String key, double defaultValue) {
        return config.has(key) ? config.get(key).getAsDouble() : defaultValue;
    }
    public boolean getBoolean(String key, boolean defaultValue) {
        return config.has(key) ? config.get(key).getAsBoolean() : defaultValue;
    }
    public void set(String key, Object value) {
        config.addProperty(key, String.valueOf(value));
    }
}