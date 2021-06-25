package com.aircall.es.testscucumber.Phone;

import com.aircall.es.testscucumber.AircallPhone.*;
import com.aircall.es.testscucumber.Utils.LoggerHandler;
import com.aircall.es.testscucumber.Utils.PropertiesHandler;
import com.aircall.es.testscucumber.Utils.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class LoginSteps {
    private static final Logger LOGGER = LoggerHandler.getCustomLogger(LoginSteps.class);

    @Given("A calling user that navigates to Aircall phone")
    public static void i_want_a_user_to_go_to_Aircall_phone_url() {
        //Verify page loading with a reference item
        WebDriver driver = Utils.getCallingDriver();
        LoginPage loginPage = new LoginPage(driver);
        driver.get(PropertiesHandler.getProgramProperties().getProperty("phoneUrl"));
        loginPage.genericIsDisplayed(loginPage.buttonLogin);
    }

    @When("the calling user logins to the page")
    public static void login_to_aircall_phone() {
        LoginPage loginPage = new LoginPage(Utils.getCallingDriver());
        loginPage.logIn(PropertiesHandler.getProgramProperties().getProperty("username1"),
                PropertiesHandler.getProgramProperties().getProperty("password1"));
    }

    @Then("the calling user navigates through the onboarding flow")
    public static void pass_onboarding_section() {
        LanguagePage languagePage = new LanguagePage(Utils.getCallingDriver());
        languagePage.selectLanguage();

        OnboardingLoginPage onboardingLoginPage = new OnboardingLoginPage(Utils.getCallingDriver());
        onboardingLoginPage.continueInOnboarding();
    }


}
