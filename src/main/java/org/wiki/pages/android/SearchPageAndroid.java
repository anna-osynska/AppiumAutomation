package org.wiki.pages.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.wiki.pages.pageObjects.SearchPageObject;

import java.time.Duration;
import java.util.Collections;

public class SearchPageAndroid extends SearchPageObject {
    {
        SEARCH_INPUT = By.id("org.wikipedia:id/search_src_text");
        INIT_SEARCH = By.xpath("//android.widget.TextView[@text='Search Wikipedia']");
        PATTERN = "//android.widget.TextView[@text=\"%s\"]";
        SEARCH_RESULT = By.xpath("//*[@resource-id=\"org.wikipedia:id/search_results_list\"]/android.view.ViewGroup");
        SEARCH_VIEW = By.xpath("//*[@text='No results']");
        CANCEL_BUTTON = AppiumBy.accessibilityId("Clear query");
        SHARE = By.xpath("//*[@text='Share link']");
    }

    public String getArticleTitle(String text) {
        return getElementAttribute(By.xpath(String.format(PATTERN, text)), "text");
    }

    public void doubleTap(WebElement el) {
        Point location = el.getLocation();
        Dimension size = el.getSize();
        Point center = getCenterOfElement(location, size);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), center))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(100)))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(sequence));
    }
}

