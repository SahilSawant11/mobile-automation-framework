package com.example.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;
import org.apache.logging.log4j.Logger;
import com.example.utils.LoggerUtils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public abstract class BasePage {
    public static AppiumDriver driver;
    protected WebDriverWait wait;
    protected static final Logger log = LoggerUtils.getLogger(BasePage.class);

    // Configurable timeout values
    private static final int DEFAULT_TIMEOUT = 10;
    private static final int POLLING_INTERVAL = 500; // milliseconds

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        this.wait.pollingEvery(Duration.ofMillis(POLLING_INTERVAL));
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    }

    protected void waitForVisibility(WebElement element) {
        waitForVisibility(element, DEFAULT_TIMEOUT);
    }

    protected void waitForVisibility(WebElement element, int timeoutSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            customWait.until(ExpectedConditions.visibilityOf(element));
            log.info("Element is visible");
        } catch (TimeoutException e) {
            log.error("Element not visible after {} seconds", timeoutSeconds);
            throw new TimeoutException("Element not visible: " + element.toString());
        }
    }

    protected void waitForClickable(WebElement element) {
        waitForClickable(element, DEFAULT_TIMEOUT);
    }

    protected void waitForClickable(WebElement element, int timeoutSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            customWait.until(ExpectedConditions.elementToBeClickable(element));
            log.info("Element is clickable");
        } catch (TimeoutException e) {
            log.error("Element not clickable after {} seconds", timeoutSeconds);
            throw new TimeoutException("Element not clickable: " + element.toString());
        }
    }

    protected void safeClick(WebElement element) {
        safeClick(element, DEFAULT_TIMEOUT);
    }

    protected void safeClick(WebElement element, int timeoutSeconds) {
        try {
            waitForClickable(element, timeoutSeconds);
            element.click();
            log.info("Successfully clicked element");
        } catch (Exception e) {
            log.error("Failed to click element: {}", e.getMessage());
            throw e;
        }
    }

    protected void safeSendKeys(WebElement element, String text) {
        safeSendKeys(element, text, DEFAULT_TIMEOUT);
    }

    protected void safeSendKeys(WebElement element, String text, int timeoutSeconds) {
        try {
            waitForVisibility(element, timeoutSeconds);
            element.clear();
            element.sendKeys(text);
            log.info("Successfully sent keys: {}", text);
        } catch (Exception e) {
            log.error("Failed to send keys to element: {}", e.getMessage());
            throw e;
        }
    }

    protected String safeGetText(WebElement element) {
        return safeGetText(element, DEFAULT_TIMEOUT);
    }

    protected String safeGetText(WebElement element, int timeoutSeconds) {
        try {
            waitForVisibility(element, timeoutSeconds);
            String text = element.getText();
            log.info("Retrieved text: {}", text);
            return text;
        } catch (Exception e) {
            log.error("Failed to get text from element: {}", e.getMessage());
            throw e;
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            log.warn("Element not found in DOM");
            return false;
        } catch (Exception e) {
            log.warn("Error checking element display: {}", e.getMessage());
            return false;
        }
    }

    protected boolean isElementEnabled(WebElement element) {
        try {
            waitForVisibility(element, 5);
            return element.isEnabled();
        } catch (Exception e) {
            log.warn("Element not enabled or not found: {}", e.getMessage());
            return false;
        }
    }

    protected void waitForInvisibility(WebElement element) {
        waitForInvisibility(element, DEFAULT_TIMEOUT);
    }

    protected void waitForInvisibility(WebElement element, int timeoutSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            customWait.until(ExpectedConditions.invisibilityOf(element));
            log.info("Element is no longer visible");
        } catch (TimeoutException e) {
            log.warn("Element still visible after {} seconds", timeoutSeconds);
        }
    }

    protected void scrollToElement(WebElement element) {
        try {
            driver.executeScript("mobile: scrollToElement", element);
            log.info("Scrolled to element");
        } catch (Exception e) {
            log.warn("Could not scroll to element: {}", e.getMessage());
        }
    }

    protected boolean tryClick(WebElement element) {
        return tryClick(element, 5);
    }

    protected boolean tryClick(WebElement element, int timeoutSeconds) {
        try {
            waitForClickable(element, timeoutSeconds);
            element.click();
            log.info("Successfully clicked element");
            return true;
        } catch (Exception e) {
            log.warn("Could not click element: {}", e.getMessage());
            return false;
        }
    }

    protected void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            log.error("Thread interrupted during pause");
            Thread.currentThread().interrupt();
        }
    }

    protected void enterText(WebElement element, String text) {
        log.info("Entering text: {}", text);
        element.click();
        pause(1000);

        Map<String, Object> params = new HashMap<>();
        params.put("text", text);
        driver.executeScript("mobile: type", params);
    }
}