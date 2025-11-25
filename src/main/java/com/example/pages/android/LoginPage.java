package com.example.pages.android;

import com.example.core.BasePage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "com.example.app:id/username")
    private WebElement usernameField;

    @FindBy(id = "com.example.app:id/password")
    private WebElement passwordField;

    @FindBy(id = "com.example.app:id/login")
    private WebElement loginButton;

    public LoginPage enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    public void tapLogin() {
        loginButton.click();
    }


    public boolean isUsernameFieldDisplayed() {
        return usernameField.isDisplayed();
    }

    public boolean isLoginButtonEnabled() {
        return loginButton.isEnabled();
    }

    public String getUsernameText() {
        return usernameField.getText();
    }
}