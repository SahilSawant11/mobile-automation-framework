package com.example.utils;

import com.example.core.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtils {
    public static byte[] takeScreenshotBytes() throws Exception {
        AppiumDriver driver = DriverManager.getDriver();
        if (driver == null) return new byte[0];
        byte[] data = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        // save local copy
        Files.write(Paths.get("screenshots/screenshot-" + System.currentTimeMillis() + ".png"), data);
        return data;
    }
}
