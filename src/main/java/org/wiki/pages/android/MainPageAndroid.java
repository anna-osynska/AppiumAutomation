package org.wiki.pages.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.wiki.pages.pageObjects.MainPageObject;

import java.time.Duration;

public class MainPageAndroid extends MainPageObject {
    public MainPageAndroid() {
        FEATURE_ARTICLE = By.id("org.wikipedia:id/main_toolbar_wordmark");
        FOOTER = By.xpath("//*[@text='Picture of the day']");
    }

    public String getFirstFeaturedArticle() {
        return waitForElementPresent(FEATURE_ARTICLE).getAttribute("text");
    }

    public void runInBackground() {
        ((AndroidDriver) driver).runAppInBackground(Duration.ofMillis(10));
    }

    public boolean isFooterPresent() {
        return driver.findElement(FOOTER).isDisplayed();
    }

    public void swipeUntilFooter() {
        swipeUntilElementFound(FOOTER, driver);
    }
}
