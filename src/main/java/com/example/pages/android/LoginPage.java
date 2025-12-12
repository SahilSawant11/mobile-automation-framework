package com.example.pages.android;

import com.example.core.BasePage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]") private WebElement mobilenoField;
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]") private WebElement passwordField;
    @FindBy(xpath = "//android.widget.Button[@content-desc='साइन इन']") private WebElement loginButton;
    @FindBy(xpath = "com.android.permissioncontroller:id/permission_allow_button") private WebElement allowBtn;
    @FindBy(xpath = "//android.view.View[@content-desc='SKIP']") private WebElement skipButton;
    @FindBy(xpath = "//android.view.View[@content-desc='Get Started']") private WebElement getStartedButton;
    @FindBy(xpath = "//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_foreground_only_button']") private WebElement foregroundOnlyButton;
    @FindBy(xpath = "//android.view.View[@content-desc='sahil sawant स्वागत आहे']") private WebElement userNameWelcome;

    public void tapAllow() {
        try {
            allowBtn.click();
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }
    }

    public void tapSkip() {
        try {
            skipButton.click();
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }
    }

    public void tapGetStarted() {
        try {
            getStartedButton.click();
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }
    }

        public LoginPage enterMobileno(String mobileno) throws InterruptedException {
        wait(1000);
        mobilenoField.clear();
        mobilenoField.sendKeys(mobileno);
        return this;
    }

    public LoginPage enterPassword(String password) throws InterruptedException {
        wait(1000);
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    public void tapLogin() {
        loginButton.click();
    }

    public void tapForegroundOnly() {
        try {
            foregroundOnlyButton.click();
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }
    }

    public boolean isLoginButtonEnabled() {
        return loginButton.isEnabled();
    }

    public String getUsernameText() {
        return mobilenoField.getText();
    }
}