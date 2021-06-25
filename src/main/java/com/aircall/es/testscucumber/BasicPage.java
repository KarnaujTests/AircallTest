package com.aircall.es.testscucumber;

import com.aircall.es.testscucumber.Utils.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BasicPage {

    protected WebDriver driver;
    protected WebDriverWait wait;

//    private final By COOKIES_BUTTON = By.cssSelector("[id='aceptar']");
//    private final By COOKIES_BANNER = By.cssSelector("[id='cookies']");

    public BasicPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,Utils.timeOut());
    }

    protected void scrollToElement(By elementToBeVisible){
        WebElement targetElement = driver.findElement(elementToBeVisible);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",targetElement);
    }

    protected void dragAndDropScrollBar(By scrollBar,By cancelButton){
        WebElement from = driver.findElement(scrollBar);
        WebElement to = driver.findElement(cancelButton);
        Actions builder = new Actions(driver);
        builder.dragAndDrop(from, to).perform();
    }

    protected void scrollToBottom(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    protected void scrollToTop(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
    }

    /**
     * Deprecated due to the verification of the element change taking too long. Should implement specifically
     * for it to be reactive to the change and not passive upon element waiting
     * @param clickableLocator
     * @param referenceLocator
     */
    @Deprecated
    protected void clickAndChangeElementVisibility(By clickableLocator, By referenceLocator) {
        WebElement clickableElement = this.wait.until(ExpectedConditions.elementToBeClickable(clickableLocator));

        boolean referenceVisibility = false;
        try {
            referenceVisibility = driver.findElement(referenceLocator).isDisplayed();
        } catch (Exception e) {}

        clickableElement.click();

        if (referenceVisibility) {
            WebDriverWait specificWait = new WebDriverWait(this.driver,3);
            specificWait.until(ExpectedConditions.or(
                    ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(referenceLocator)),
                    ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(referenceLocator))));
        } else {
            this.wait.until(ExpectedConditions.visibilityOfElementLocated(referenceLocator));
        }
    }

    protected void clickAndRefresh(By selector) {
        WebElement elementBasePage = this.wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("html")));
        try{
            this.wait.until(ExpectedConditions.elementToBeClickable(selector)).click();
        }catch (Exception e){
            //In case of ElementClickInterceptedException,
            //force the click with JavascriptExecutor to prevent other element from receiving the click
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", driver.findElement(selector));
        }
        this.wait.ignoring(NoSuchElementException.class);
        this.wait.until(ExpectedConditions.stalenessOf(elementBasePage));

    }

    //TODO: remove references to two step clicks to 3 steps verifications when those methods are fixed
    public void twoStepClick(By selector) {
        try{
            this.wait.until(ExpectedConditions.elementToBeClickable(selector)).click();
        }catch (Exception e){
            //In case of ElementClickInterceptedException,
            //force the click with JavascriptExecutor to prevent other element from receiving the click
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", driver.findElement(selector));
        }
    }

    protected void genericClickVisibility(By selector){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(selector)).click();
    }

    protected String genericGetText(By selector){
        return this.wait.until(ExpectedConditions.visibilityOfElementLocated(selector)).getText();
    }

    public boolean genericIsDisplayed(By ELEMENT){
        try {
            return this.wait.until(ExpectedConditions.visibilityOfElementLocated(ELEMENT)).isDisplayed();
        }
        catch (Exception e) {
            return false;
        }
    }

    protected WebElement extractWebElementFromList(By ELEMENT, int index){
        List<WebElement> list;
        list = this.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ELEMENT));
        return list.get(index);
    }

    protected void writer(By ELEMENT, String textBeingSent){
        WebElement target;
        target = this.wait.until(ExpectedConditions.elementToBeClickable(ELEMENT));
        target.clear();
        target.sendKeys(textBeingSent);
    }

    protected int RNG(int max){
        Random rand = new Random();
        return rand.nextInt(max);
    }

}
