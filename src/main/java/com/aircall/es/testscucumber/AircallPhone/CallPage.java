package com.aircall.es.testscucumber.AircallPhone;

import com.aircall.es.testscucumber.BasicPage;
import com.aircall.es.testscucumber.Utils.Utils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CallPage extends BasicPage {

    private final By buttonAcceptCall = By.xpath("//*[@data-test='pickup-button']");
    private final By buttonHangupCall = By.xpath("//*[@data-test='hangup-button']");
    private final By buttonTelephoneContactCall = By.xpath("//*[@data-test='title-text']");

    private final By sectionCallActions = By.xpath("//*[@data-test='in-call-actions']");
    private final By buttonMuteCall = By.xpath("//*[@data-test='action-mute']");
    private final By buttonHoldCall = By.xpath("//*[@data-test='action-hold']");
    private final By buttonKeypadCall = By.xpath("//*[@data-test='action-keypad']");
    private final By buttonStartRecordCall = By.xpath("//*[@data-test='action-start-recording']");

    private final By sectionWrapUp = By.xpath("//*[@data-test='call-details-info']");
    private final By buttonCopyCallID = By.xpath("//*[@data-test='paragraph-text']");


    public CallPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return this.driver.getTitle();
    }

    public void verifyPageLoaded() {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(sectionCallActions));
    }

    public void acceptCall() {
        twoStepClick(buttonAcceptCall);
        verifyPageLoaded();
    }

    public void verifyOtherNumberInCall(String telephoneExpected) {
        Assert.assertTrue(Utils.simplifyPhone(genericGetText(buttonTelephoneContactCall))
                .equals(Utils.simplifyPhone(telephoneExpected)));
    }

    public void hangupCall() {
        twoStepClick(buttonHangupCall);
    }

    public void wrapUpMenuAppears() {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(sectionWrapUp));
    }


}
