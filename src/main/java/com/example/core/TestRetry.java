package com.example.core;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestRetry implements IRetryAnalyzer {
    private int retryCount = 0;
    private final int maxRetryCount = Integer.parseInt(System.getProperty("test.retry","1"));

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}
