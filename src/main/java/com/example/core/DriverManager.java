package com.example.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DriverManager {
    // Thread-safe driver map to support parallel execution
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static void setDriver(AppiumDriver appiumDriver) {
        driver.set(appiumDriver);
    }

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        AppiumDriver d = driver.get();
        if (d != null) {
            try { d.quit(); } catch (Exception ignored) {}
            driver.remove();
        }
    }

    public static AppiumDriver createAndroidDriver(String remoteUrl, DesiredCapabilities caps) throws Exception {
        AndroidDriver androidDriver = new AndroidDriver(new URL(remoteUrl), caps);
        setDriver(androidDriver);
        return androidDriver;
    }
}
