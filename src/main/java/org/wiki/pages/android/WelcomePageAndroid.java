package org.wiki.pages.android;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.wiki.pages.pageObjects.WelcomePageObject;

public class WelcomePageAndroid extends WelcomePageObject {
    public WelcomePageAndroid() {
        SKIP_BUTTON = By.xpath("//*[@text='SKIP']");
    }
}
