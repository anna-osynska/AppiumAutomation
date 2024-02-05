package org.wiki.pages.pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.SupportsRotation;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.wiki.pages.pageObjects.BasePageObject;

public abstract class MainPageObject extends BasePageObject {
    protected By FEATURE_ARTICLE;
    protected By FOOTER;
    public void rotateDevice() {
        ((SupportsRotation) driver).rotate(ScreenOrientation.LANDSCAPE);
    }

    public abstract String getFirstFeaturedArticle();

    public abstract void runInBackground();

    public abstract boolean isFooterPresent();

    public abstract void swipeUntilFooter();
}
