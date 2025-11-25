package com.example.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigManager {
    private static Properties props = new Properties();
    static {
        try {
            props.load(new FileInputStream("src/test/resources/config/config.properties"));
        } catch (Exception e) { /* ignore */ }
    }
    public static String get(String key, String defaultVal) {
        return props.getProperty(key, defaultVal);
    }
}
