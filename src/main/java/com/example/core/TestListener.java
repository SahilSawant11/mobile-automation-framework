package com.example.core;

import io.qameta.allure.Attachment;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        // attach screenshot
        try {
            byte[] bytes = com.example.utils.ScreenshotUtils.takeScreenshotBytes();
            saveScreenshot(bytes);
        } catch (Exception ignored) {}
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] data) {
        return data;
    }
}
