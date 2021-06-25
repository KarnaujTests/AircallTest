package com.aircall.es.testscucumber.AircallPhone;

import com.aircall.es.testscucumber.BasicPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationBarPage extends BasicPage {

    public enum TelephoneSections {
        TODO(By.xpath("//li[@data-test='tab-bar-todo']/a")),
        HISTORY(By.xpath("//li[@data-test='tab-bar-history']/a")),
        KEYPAD(By.xpath("//li[@data-test='tab-bar-keypad']/a")),
        PEOPLE(By.xpath("//li[@data-test='tab-bar-people']/a")),
        PROFILE(By.xpath("//li[@data-test='tab-bar-settings']/a"))
        ;

        private By sectionElement;
        private TelephoneSections(By locationBy) {
            this.sectionElement = locationBy;
        }
        public By getByLocator() { return this.sectionElement; }
    }

    public NavigationBarPage(WebDriver driver) {
        super(driver);
    }

    public void goToArea(TelephoneSections section) {
        twoStepClick(section.getByLocator());
    }

}
