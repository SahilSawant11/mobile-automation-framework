package com.example.pages.android;

import com.example.core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(id = "com.example.app:id/welcome")
    private WebElement welcomeLabel;

    public String getWelcomeText(){
        return welcomeLabel.getText();
    }
}
