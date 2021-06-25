package com.aircall.es.testscucumber.AircallPhone;

import com.aircall.es.testscucumber.BasicPage;
import com.aircall.es.testscucumber.Utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class KeypadPage extends BasicPage {

    private final By inputNumberCall = By.xpath("//input[@data-test='keyboard-input-text']");
    private final By buttonMakeCall = By.xpath("//button[@data-test='keyboard-button-dial']");

    private final By buttonContact = By.xpath("//*[contains(@data-test,'contact-item')]");
    private final String buttonContactNumberToCall = "//*[@data-test='contact-number-#!NUMBER#']";

    public KeypadPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return this.driver.getTitle();
    }

    public void callNumber(String phoneNumber) {
        writer(inputNumberCall, Utils.simplifyPhone(phoneNumber));

        twoStepClick(buttonContact);
        twoStepClick(By.xpath(Utils.replaceFirstPlaceholder(buttonContactNumberToCall,
                Utils.simplifyPhone(phoneNumber))));
        twoStepClick(buttonMakeCall);

        CallPage navigationPage = new CallPage(this.driver);
        navigationPage.verifyPageLoaded();
    }

}
