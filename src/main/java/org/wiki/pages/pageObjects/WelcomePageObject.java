package org.wiki.pages.pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.wiki.pages.pageObjects.BasePageObject;

public abstract class WelcomePageObject extends BasePageObject {
    protected By SKIP_BUTTON;

    public void clickSkipButton() {
        waitForElementAndClick(SKIP_BUTTON);
    }
}
