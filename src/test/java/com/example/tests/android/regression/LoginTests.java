package com.example.tests.android.regression;

import com.example.core.BaseTest;
import com.example.pages.android.HomePage;
import com.example.pages.android.LoginPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test
    @Description("Verify login flow")
    public void testLogin() throws InterruptedException {
        LoginPage login = new LoginPage();
        login.tapAllow();
        login.tapSkip();
        login.tapGetStarted();
        login.enterMobileno("8104155804").enterPassword("112112").tapLogin();
        login.tapForegroundOnly();
        HomePage home = new HomePage();
        Assert.assertFalse(home.getWelcomeText().isEmpty(), "Welcome text must be present");
    }
}
