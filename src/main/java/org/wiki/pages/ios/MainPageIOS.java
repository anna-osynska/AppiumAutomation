package org.wiki.pages.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.wiki.pages.pageObjects.MainPageObject;

import java.time.Duration;

public class MainPageIOS extends MainPageObject {
    public MainPageIOS() {
        FEATURE_ARTICLE = By.id("wikipedia");
        FOOTER = By.id("Random article");
    }

    public String getFirstFeaturedArticle() {
        return waitForElementPresent(FEATURE_ARTICLE).getAttribute("name");
    }

    public void runInBackground() {
        ((IOSDriver) driver).runAppInBackground(Duration.ofMillis(10));
    }

    public boolean isFooterPresent() {
        return driver.findElement(FOOTER).isDisplayed();
    }

    public void swipeUntilFooter() {
        swipeUntilElementFound(FOOTER, driver);
    }
}
