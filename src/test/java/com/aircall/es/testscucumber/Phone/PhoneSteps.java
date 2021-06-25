package com.aircall.es.testscucumber.Phone;

import com.aircall.es.testscucumber.AircallPhone.CallPage;
import com.aircall.es.testscucumber.AircallPhone.KeypadPage;
import com.aircall.es.testscucumber.AircallPhone.NavigationBarPage;
import com.aircall.es.testscucumber.Utils.LoggerHandler;
import com.aircall.es.testscucumber.Utils.PropertiesHandler;
import com.aircall.es.testscucumber.Utils.Utils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

public class PhoneSteps {
    private static final Logger LOGGER = LoggerHandler.getCustomLogger(PhoneSteps.class);

    @When("The calling user calls the receiver")
    public static void i_want_a_user_to_go_to_Aircall_phone_url() {
        NavigationBarPage navigationBarPage = new NavigationBarPage(Utils.getCallingDriver());
        navigationBarPage.goToArea(NavigationBarPage.TelephoneSections.KEYPAD);

        KeypadPage keypadPage = new KeypadPage(Utils.getCallingDriver());
        keypadPage.callNumber(PropertiesHandler.getProgramProperties().getProperty("telephone2"));
    }

    @Then("The receiving user is capable of accepting the call")
    public static void the_receiving_user_can_accept_the_call() {
        CallPage callPage = new CallPage(Utils.getReceiverDriver());
        callPage.acceptCall();
        callPage.verifyOtherNumberInCall(PropertiesHandler.getProgramProperties().getProperty("telephone1"));
    }

    @When("The receiving user hangs up the call")
    public static void the_receiving_user_hangsup_the_call() {
        CallPage callPage = new CallPage(Utils.getReceiverDriver());
        callPage.hangupCall();
    }

    @Then("The calling user call is terminated and the wrapup call is shown")
    public static void the_calling_user_can_see_wrapup_on_finalization() {
        CallPage callPage = new CallPage(Utils.getCallingDriver());
        callPage.wrapUpMenuAppears();
    }

}
