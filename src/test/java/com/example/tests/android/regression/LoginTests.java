package com.example.tests.android.regression;

import com.example.core.BaseTest;
import com.example.pages.android.HomePage;
import com.example.pages.android.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(priority = 1)
    @Description("Verify successful login with valid credentials")
    @Severity(SeverityLevel.BLOCKER)
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();
        boolean loginSuccess = loginPage.performLogin("8104155804", "112112");

        boolean isHomeLoaded = homePage.waitForAapleSthanVisible(20);
        Assert.assertTrue(isHomeLoaded, "'आपले स्थान' not visible after login");

    }
}