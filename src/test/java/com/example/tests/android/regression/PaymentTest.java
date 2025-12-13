package com.example.tests.android.regression;

import com.example.core.BaseTest;
import com.example.pages.android.HomePage;
import com.example.pages.android.LoginPage;
import io.appium.java_client.remote.SupportsContextSwitching;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

import static com.example.core.BasePage.driver;

public class PaymentTest extends BaseTest {

    @Test(priority = 1 , enabled = false)
    @Description("Verify successful login with valid credentials")
    @Severity(SeverityLevel.BLOCKER)
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();
        boolean loginSuccess = loginPage.performLogin("8104155804", "112112");

        boolean isHomeLoaded = HomePage.waitForAapleSthanVisible(20);
        Assert.assertTrue(isHomeLoaded, "'आपले स्थान' not visible after login");

    }

    @Test(priority = 2)
    public void paymentTest()
    {
        HomePage homePage = new HomePage();
/*        homePage.tapHomeMaalmattakar();
        homePage.tapMaalmattakar();*/
        homePage.tapKarbhara();
        homePage.tapEasebuzz();
        homePage.tapPay();
        SupportsContextSwitching driver = null;
        Set<String> contexts = driver.getContextHandles();

        System.out.println("Available contexts:");
        for (String context : contexts) {
            System.out.println(context);
        }
    }

//    2025-12-13 19:26:57 [main] INFO  com.example.core.BasePage - Tapping Karbhara button
//2025-12-13 19:26:57 [main] INFO  com.example.core.BasePage - Element is clickable
//2025-12-13 19:26:57 [main] INFO  com.example.core.BasePage - Successfully clicked element
//2025-12-13 19:26:57 [main] INFO  com.example.core.BasePage - Tapping Easebuzz button
//2025-12-13 19:26:58 [main] INFO  com.example.core.BasePage - Element is clickable
//2025-12-13 19:26:59 [main] INFO  com.example.core.BasePage - Successfully clicked element
//2025-12-13 19:26:59 [main] INFO  com.example.core.BasePage - Tapping Pay button
//2025-12-13 19:27:00 [main] INFO  com.example.core.BasePage - Element is clickable
//2025-12-13 19:27:00 [main] INFO  com.example.core.BasePage - Successfully clicked element
//
//    java.lang.NullPointerException: Cannot invoke "io.appium.java_client.remote.SupportsContextSwitching.getContextHandles()" because "driver" is null
//
//    at com.example.tests.android.regression.PaymentTest.paymentTest(PaymentTest.java:42)
//    at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)
//    at java.base/java.lang.reflect.Method.invoke(Method.java:580)
//    at org.testng.internal.invokers.MethodInvocationHelper.invokeMethod(MethodInvocationHelper.java:139)
//    at org.testng.internal.invokers.TestInvoker.invokeMethod(TestInvoker.java:664)
//    at org.testng.internal.invokers.TestInvoker.invokeTestMethod(TestInvoker.java:227)
//    at org.testng.internal.invokers.MethodRunner.runInSequence(MethodRunner.java:50)
//    at org.testng.internal.invokers.TestInvoker$MethodInvocationAgent.invoke(TestInvoker.java:957)
}
