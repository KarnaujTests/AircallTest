package com.aircall.es.testscucumber.AircallPhone;

import com.aircall.es.testscucumber.BasicPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OnboardingLoginPage extends BasicPage {

    private final By imageDotOnboardingStep = By.xpath("//span[contains(@class,'dotActive')]");
    private final By buttonContinue = By.xpath("//*[@data-test='next-button']");

    public OnboardingLoginPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return this.driver.getTitle();
    }

    public void continueInOnboarding() {
        for (int i = 0; i<3; i++) {
            WebElement onboardingStep = driver.findElement(imageDotOnboardingStep);
            int relativePosition = onboardingStep.findElements(By.xpath(".//following-sibling::*")).size();
            this.wait.until(ExpectedConditions.elementToBeClickable(buttonContinue)).click();
            /*Unexpectedly this doesn't work due to the element been reactive despite it has changed which should
            thigger staleness*/
//          this.wait.until(ExpectedConditions.stalenessOf(onboardingStep));
            try {
                Thread.sleep(200);
            } catch (Exception e) {}
            onboardingStep = driver.findElement(imageDotOnboardingStep);
            int relativePositionAfter = onboardingStep.findElements(By.xpath(".//following-sibling::*")).size();
            if (relativePosition == relativePositionAfter) {
                Assert.fail("Continue button didn't trigger a page change");
            }
        }

        twoStepClick(buttonContinue);
    }



}
