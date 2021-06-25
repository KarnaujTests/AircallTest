package com.aircall.es.testscucumber.AircallPhone;

import com.aircall.es.testscucumber.BasicPage;
import com.aircall.es.testscucumber.Utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LanguagePage extends BasicPage {

    private final By textCurrentLanguage = By.xpath("//*[@data-test='slide']//div[3]");
    private final String buttonLanguageOptionChangeableString = "//div[@data-test='panel']//*[contains(@data-test,'language_#!CODE#')]";
    private final By buttonChangeLanguage = By.xpath("//button[@data-test='language-button']");
    private final By buttonContinue = By.xpath("//button[@data-test='continue-button']");

    public LanguagePage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return this.driver.getTitle();
    }

    public void selectLanguage(Language language) {
        if (!genericGetText(textCurrentLanguage).equals(language.getLiteral())) {
            By languageLocator = By.xpath(Utils.replaceFirstPlaceholder(buttonLanguageOptionChangeableString,
                    language.getCountryCode()));

            twoStepClick(buttonChangeLanguage);

            twoStepClick(languageLocator);

            Assert.assertTrue(genericGetText(textCurrentLanguage).equals(language.getLiteral()));
        }

        clickAndRefresh(buttonContinue);
    }

    public void selectLanguage() {
        twoStepClick(buttonContinue);
    }

    public enum Language {
        ENGLISH("English", "en"),
        SPANISH("Spanish", "es");

        private String languageLiteral;
        private String countryCode;
        private Language(String literal, String code) {
            this.languageLiteral = literal;
            this.countryCode = code;
        }

        public String getLiteral() {
            return this.languageLiteral;
        }
        public String getCountryCode() {
            return this.countryCode;
        }
    }
}
