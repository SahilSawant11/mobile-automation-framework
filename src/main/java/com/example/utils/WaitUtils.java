package com.example.utils;

import com.example.core.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class WaitUtils {

    private static final int DEFAULT_TIMEOUT = 10;
    private static final int POLLING_INTERVAL = 500;

    /**
     * Get a WebDriverWait instance with default timeout
     */
    private static WebDriverWait getWait() {
        return getWait(DEFAULT_TIMEOUT);
    }

    /**
     * Get a WebDriverWait instance with custom timeout
     */
    private static WebDriverWait getWait(int timeoutSeconds) {
        AppiumDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.pollingEvery(Duration.ofMillis(POLLING_INTERVAL));
        wait.ignoring(NoSuchElementException.class);
        return wait;
    }

    /**
     * Wait for element to be visible
     */
    public static void waitForVisibility(WebElement element) {
        waitForVisibility(element, DEFAULT_TIMEOUT);
    }

    /**
     * Wait for element to be visible with custom timeout
     */
    public static void waitForVisibility(WebElement element, int timeoutSeconds) {
        try {
            getWait(timeoutSeconds).until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element not visible after " + timeoutSeconds + " seconds: " + element.toString());
        }
    }

    /**
     * Wait for element located by locator to be visible
     */
    public static void waitForVisibility(By locator, int timeoutSeconds) {
        try {
            getWait(timeoutSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element not visible after " + timeoutSeconds + " seconds: " + locator.toString());
        }
    }

    /**
     * Wait for element to be clickable
     */
    public static void waitForClickable(WebElement element) {
        waitForClickable(element, DEFAULT_TIMEOUT);
    }

    /**
     * Wait for element to be clickable with custom timeout
     */
    public static void waitForClickable(WebElement element, int timeoutSeconds) {
        try {
            getWait(timeoutSeconds).until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element not clickable after " + timeoutSeconds + " seconds: " + element.toString());
        }
    }

    /**
     * Wait for element to be present in DOM
     */
    public static void waitForPresence(By locator, int timeoutSeconds) {
        try {
            getWait(timeoutSeconds).until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element not present after " + timeoutSeconds + " seconds: " + locator.toString());
        }
    }

    /**
     * Wait for element to become invisible
     */
    public static void waitForInvisibility(WebElement element) {
        waitForInvisibility(element, DEFAULT_TIMEOUT);
    }

    /**
     * Wait for element to become invisible with custom timeout
     */
    public static void waitForInvisibility(WebElement element, int timeoutSeconds) {
        try {
            getWait(timeoutSeconds).until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException e) {
            // Invisibility timeout is not always an error
            throw new TimeoutException("Element still visible after " + timeoutSeconds + " seconds");
        }
    }

    /**
     * Wait for element text to contain expected text
     */
    public static void waitForTextToBe(WebElement element, String expectedText, int timeoutSeconds) {
        try {
            getWait(timeoutSeconds).until(ExpectedConditions.textToBePresentInElement(element, expectedText));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element text does not contain '" + expectedText + "' after " + timeoutSeconds + " seconds");
        }
    }

    /**
     * Wait for element attribute to contain expected value
     */
    public static void waitForAttributeToBe(WebElement element, String attribute, String expectedValue, int timeoutSeconds) {
        try {
            getWait(timeoutSeconds).until(ExpectedConditions.attributeContains(element, attribute, expectedValue));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element attribute '" + attribute + "' does not contain '" + expectedValue + "' after " + timeoutSeconds + " seconds");
        }
    }

    /**
     * Wait for element to be selected
     */
    public static void waitForElementToBeSelected(WebElement element, int timeoutSeconds) {
        try {
            getWait(timeoutSeconds).until(ExpectedConditions.elementToBeSelected(element));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element not selected after " + timeoutSeconds + " seconds");
        }
    }

    /**
     * Wait with custom condition
     */
    public static <T> T waitForCondition(Function<WebDriver, T> condition, int timeoutSeconds) { // <-- CHANGE 1: Use WebDriver
        try {
            // Assuming getWait() returns a FluentWait/WebDriverWait parameterized with WebDriver.
            // If your getWait() is simple, it should look like this:
            // new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds));

            return getWait(timeoutSeconds).until((Function<? super org.openqa.selenium.WebDriver, T>) condition);
        } catch (TimeoutException e) {
            throw new TimeoutException("Custom condition not met after " + timeoutSeconds + " seconds", e);
        }
    }

    /**
     * Wait for element to be stable (not moving or changing)
     */
    public static void waitForElementToBeStable(WebElement element, int timeoutSeconds) {
        try {
            getWait(timeoutSeconds).until(new ExpectedCondition<Boolean>() {
                private org.openqa.selenium.Point previousLocation = null;
                private org.openqa.selenium.Dimension previousSize = null;
                private int stableCount = 0;
                private final int requiredStableChecks = 3;

                @Override
                public Boolean apply(org.openqa.selenium.WebDriver driver) {
                    try {
                        org.openqa.selenium.Point currentLocation = element.getLocation();
                        org.openqa.selenium.Dimension currentSize = element.getSize();

                        if (previousLocation != null && previousSize != null) {
                            if (currentLocation.equals(previousLocation) && currentSize.equals(previousSize)) {
                                stableCount++;
                                if (stableCount >= requiredStableChecks) {
                                    return true;
                                }
                            } else {
                                stableCount = 0;
                            }
                        }

                        previousLocation = currentLocation;
                        previousSize = currentSize;
                        return false;
                    } catch (Exception e) {
                        return false;
                    }
                }
            });
        } catch (TimeoutException e) {
            throw new TimeoutException("Element not stable after " + timeoutSeconds + " seconds");
        }
    }

    /**
     * Fluent wait - keeps trying until element is visible or timeout
     */
    public static boolean fluentWaitForVisibility(WebElement element, int timeoutSeconds) {
        try {
            waitForVisibility(element, timeoutSeconds);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Fluent wait - keeps trying until element is clickable or timeout
     */
    public static boolean fluentWaitForClickable(WebElement element, int timeoutSeconds) {
        try {
            waitForClickable(element, timeoutSeconds);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Wait for page to load (checks if page is in ready state)
     */
    public static void waitForPageLoad(int timeoutSeconds) {
        AppiumDriver driver = DriverManager.getDriver();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                    .until(webDriver -> ((io.appium.java_client.ExecutesMethod) driver)
                            .execute("mobile: getCurrentActivity") != null);
        } catch (TimeoutException e) {
            throw new TimeoutException("Page not loaded after " + timeoutSeconds + " seconds");
        }
    }

    /**
     * Smart wait - tries multiple strategies to wait for element
     */
    public static void smartWait(WebElement element, int timeoutSeconds) {
        // First try: wait for presence
        try {
            if (element != null) {
                // Second try: wait for visibility
                waitForVisibility(element, timeoutSeconds / 2);
                // Third try: wait for stability
                waitForElementToBeStable(element, timeoutSeconds / 2);
                return;
            }
        } catch (Exception e) {
            // If all strategies fail, throw the last exception
            throw new TimeoutException("Smart wait failed after trying multiple strategies: " + e.getMessage());
        }
    }
}