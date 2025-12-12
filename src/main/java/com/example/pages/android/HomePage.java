package com.example.pages.android;

import com.example.core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(xpath = "//android.view.View[@content-desc='sahil sawant स्वागत आहे']") private WebElement welcomeLabel;

    public String getWelcomeText(){
        return welcomeLabel.getText();
    }
}
