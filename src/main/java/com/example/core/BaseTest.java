package com.example.core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.*;

import java.net.URL;
import java.time.Duration;

public class BaseTest {

    private static final String APP_PACKAGE = "panvelmunicipalcorporation.cmspanvel";
    private static final String APP_ACTIVITY = "panvelmunicipalcorporation.cmspanvel.MainActivity";

    @BeforeMethod(alwaysRun = true)
    @Parameters({"remoteUrl"})
    public void setUp(@Optional("http://127.0.0.1:4723") String remoteUrl) throws Exception {

        UiAutomator2Options options = new UiAutomator2Options();

        // Basic platform settings
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");

        // CRITICAL: Use UDID instead of deviceName for real devices
        options.setUdid("R9ZWA0CYFWH");

        // Platform version - MUST MATCH YOUR DEVICE
        options.setPlatformVersion("15");

        // App to launch
        options.setAppPackage(APP_PACKAGE);
        options.setAppActivity(APP_ACTIVITY);

        // Don't reinstall the app
        options.setNoReset(true);
        options.setFullReset(false);

        // Additional settings
        options.setAutoGrantPermissions(true);
        options.setNewCommandTimeout(Duration.ofSeconds(300));

        // IMPORTANT: Add longer device ready timeout
        options.setAdbExecTimeout(Duration.ofSeconds(60));
        options.setAndroidInstallTimeout(Duration.ofSeconds(90));

        System.out.println("Attempting to connect to device: R9ZWA0CYFWH");
        System.out.println("App Package: " + APP_PACKAGE);
        System.out.println("App Activity: " + APP_ACTIVITY);

        DriverManager.createAndroidDriver(remoteUrl, options);

        System.out.println("Driver created successfully!");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // DriverManager.quitDriver();
    }
}