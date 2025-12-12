package com.example.core;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

public class BaseTest {
    @BeforeMethod(alwaysRun = true)
    @Parameters({"remoteUrl"})
    public void setUp(@Optional("http://127.0.0.1:4723/wd/hub") String remoteUrl) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("deviceName", "Android_Emulator");
        caps.setCapability("app", System.getProperty("app.path","src/test/resources/apps/app-debug.apk"));
        DriverManager.createAndroidDriver(remoteUrl, caps);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
//        DriverManager.quitDriver();
    }
}
