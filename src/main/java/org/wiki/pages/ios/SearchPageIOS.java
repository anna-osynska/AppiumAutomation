package org.wiki.pages.ios;

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

public class SearchPageIOS extends SearchPageObject {
    {
        SEARCH_INPUT = By.id("Search Wikipedia");
        INIT_SEARCH = AppiumBy.iOSNsPredicateString("name == \"Search Wikipedia\"");
        PATTERN = "//XCUIElementTypeStaticText[@name=\"%s\"]";
        SEARCH_RESULT = By.xpath("//XCUIElementTypeCollectionView/XCUIElementTypeCell[@visible='true']");
        SEARCH_VIEW = By.id("No results found");
        CANCEL_BUTTON = AppiumBy.iOSNsPredicateString("name == 'Clear text'");
        SHARE = AppiumBy.iOSNsPredicateString("name == \"Share…\"");

    }

    public String getArticleTitle(String text) {
        return getElementAttribute(By.xpath(String.format(PATTERN, text)), "value");
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
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(100)))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(100)))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(sequence));
    }
}
