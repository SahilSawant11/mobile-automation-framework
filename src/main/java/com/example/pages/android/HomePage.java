package com.example.pages.android;

import com.example.core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {
    @FindBy(xpath = "//android.view.View[@content-desc='आपले स्थान']") private static WebElement aapleSthanElement;
    @FindBy(xpath = "//android.view.View[@content-desc=\"नागरिक सेवा\n"+"मालमत्ता कर\n"+"पाणी पट्टी\n"+"मालमत्तेची नोंदणी करा\"]/android.widget.ImageView[1]") private  WebElement homeMaalmattakarBtn;
    @FindBy(xpath = "//android.widget.ImageView[@content-desc='मालमत्ता कर भरा']") private  WebElement maalmattakarBtn;
    @FindBy(xpath = "//android.widget.Button[@content-desc='कर भरा']") private WebElement karBharaBtn;
    @FindBy(xpath = "//android.widget.CheckBox[@content-desc='EASEBUZZ']") private WebElement easebuzzBtn;
    @FindBy(xpath = "//android.view.View[@content-desc='PAY']") private WebElement payBtn;

    public static boolean waitForAapleSthanVisible(int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.visibilityOf(aapleSthanElement));
            log.info("'आपले स्थान' element is visible");
            return true;
        } catch (Exception e) {
            log.error("'आपले स्थान' element not visible within timeout");
            return false;
        }
    }

    public void tapHomeMaalmattakar() {
        log.info("Tapping Home Maalmattakar button");
        safeClick(homeMaalmattakarBtn, 10);
    }

    public void tapMaalmattakar() {
        log.info("Tapping Maalmattakar button");
        safeClick(maalmattakarBtn, 10);
    }

    public void tapKarbhara() {
        log.info("Tapping Karbhara button");
        safeClick(karBharaBtn, 10);
    }

    public void tapEasebuzz() {
        log.info("Tapping Easebuzz button");
        safeClick(easebuzzBtn, 10);
    }

    public void tapPay() {
        log.info("Tapping Pay button");
        safeClick(payBtn, 10);
    }

}
