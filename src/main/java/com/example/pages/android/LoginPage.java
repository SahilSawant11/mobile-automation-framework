package com.example.pages.android;

import com.example.core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//android.widget.EditText[@hint='मोबाईल नंबर']") private WebElement mobilenoField;
    @FindBy(xpath = "//android.widget.EditText[@hint='पासवर्ड']") private WebElement passwordField;
    @FindBy(xpath = "//android.widget.Button[@content-desc='साइन इन']") private WebElement loginButton;
    @FindBy(xpath = "//android.view.View[@content-desc='sahil sawant स्वागत आहे']") private WebElement userNameWelcome;

    public void enterMobileno(String mobileno) {
        log.info("Entering mobile number");
        enterText(mobilenoField, mobileno);
    }

    public void enterPassword(String password) {
        log.info("Entering password");
        enterText(passwordField, password);
    }

    public void tapLogin() {
        log.info("Tapping Login button");
        safeClick(loginButton, 10);
    }

    public boolean performLogin(String mobileno, String password) {
        log.info("Performing complete login flow for mobile: {}", mobileno);
        try {
            // Enter credentials
            pause(5000);
            enterMobileno(mobileno);
            pause(5000);
            enterPassword(password);
            tapLogin();
            log.info("Login flow completed successfully");
            return true;
        } catch (Exception e) {
            log.error("Login failed with exception: {}", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void setText(WebElement element, String text) {
        Map<String, Object> params = new HashMap<>();
        params.put("elementId", ((RemoteWebElement) element).getId());
        params.put("text", text);

        driver.executeScript("mobile: setText", params);
    }

}