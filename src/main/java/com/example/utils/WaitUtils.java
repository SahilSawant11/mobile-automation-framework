package com.example.utils;

import com.example.core.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.appium.java_client.AppiumDriver;

import java.time.Duration;

public class WaitUtils {
    public static void waitForVisibility(WebElement element, int seconds) {
        AppiumDriver driver = DriverManager.getDriver();
        new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.visibilityOf(element));
    }
}
