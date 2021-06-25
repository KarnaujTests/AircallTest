package com.aircall.es.testscucumber.AircallPhone;

import com.aircall.es.testscucumber.BasicPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasicPage {

    private final By inputUser = By.xpath("//*[contains(@data-test,'email')]//input");
    private final By inputPassword = By.xpath("//*[contains(@data-test,'password')]//input");
    public final By buttonLogin = By.xpath("//button[@data-test='signin-button']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return this.driver.getTitle();
    }

    public void logIn(String user, String password) {
        writer(inputUser, user);
        writer(inputPassword, password);
        twoStepClick(buttonLogin);
    }

}
