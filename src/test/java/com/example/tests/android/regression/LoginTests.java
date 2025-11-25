package com.example.tests.android.regression;

import com.example.core.BaseTest;
import com.example.pages.android.HomePage;
import com.example.pages.android.LoginPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(retryAnalyzer = com.example.core.TestRetry.class)
    @Description("Verify login flow")
    public void testLogin() {
        LoginPage login = new LoginPage();
        login.enterUsername("testuser").enterPassword("Password123").tapLogin();
        HomePage home = new HomePage();
        Assert.assertTrue(home.getWelcomeText().length() > 0, "Welcome text must be present");
    }
}
