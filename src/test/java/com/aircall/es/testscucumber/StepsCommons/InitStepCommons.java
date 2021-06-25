package com.aircall.es.testscucumber.StepsCommons;

import com.aircall.es.testscucumber.AircallPhone.LanguagePage;
import com.aircall.es.testscucumber.AircallPhone.LoginPage;
import com.aircall.es.testscucumber.AircallPhone.OnboardingLoginPage;
import com.aircall.es.testscucumber.Utils.LoggerHandler;
import com.aircall.es.testscucumber.Utils.PropertiesHandler;
import com.aircall.es.testscucumber.BasicPage;
import com.aircall.es.testscucumber.Utils.Utils;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class InitStepCommons {
    private static final Logger LOGGER = LoggerHandler.getCustomLogger(InitStepCommons.class);

    @Given("A calling user logins to Aircall phone")
    public static void i_want_a_user_to_go_to_Aircall_phone_url() {
        loginToAircallPhone(Utils.getCallingDriver(),
                PropertiesHandler.getProgramProperties().getProperty("username1"),
                PropertiesHandler.getProgramProperties().getProperty("password1"));
    }

    @Given("A receiving user logins to Aircall phone")
    public static void i_want_a_receiving_user_to_connect_to_Aircall_phone_url() {
        loginToAircallPhone(Utils.getReceiverDriver(),
                PropertiesHandler.getProgramProperties().getProperty("username2"),
                PropertiesHandler.getProgramProperties().getProperty("password2"));
    }

    private static void loginToAircallPhone(WebDriver driver, String username, String password) {
        driver.get(PropertiesHandler.getProgramProperties().getProperty("phoneUrl"));

        //Verify page loading with a reference item
        LoginPage loginPage = new LoginPage(driver);
        loginPage.genericIsDisplayed(loginPage.buttonLogin);

        loginPage.logIn(username, password);

        LanguagePage languagePage = new LanguagePage(driver);
        languagePage.selectLanguage();

        OnboardingLoginPage onboardingLoginPage = new OnboardingLoginPage(driver);
        onboardingLoginPage.continueInOnboarding();
    }

}
