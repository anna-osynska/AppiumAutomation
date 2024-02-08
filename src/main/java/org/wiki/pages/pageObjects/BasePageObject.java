package org.wiki.pages.pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.wiki.Platform;

import java.time.Duration;
import java.util.Collections;

public abstract class BasePageObject {
    protected AppiumDriver driver = Platform.getDriver();

    public String getElementAttribute(By by, String attribute) {
        return waitForElementPresent(by).getAttribute(attribute);
    }

    public WebElement waitForElementAndClick(By el) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(el));
        webElement.click();
        return webElement;
    }

    public WebElement waitForElementAndSendKeys(By el, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(el));
        webElement.clear();
        webElement.sendKeys(text);
        return webElement;
    }

    public boolean waitForElementNotPresent(By el) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(el));
    }

    public WebElement waitForElementPresent(By el) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        return wait.until(ExpectedConditions.presenceOfElementLocated(el));
    }

    public WebElement waitForElementAndClear(By el) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(el));
        webElement.clear();
        return webElement;
    }

    public Point getCenterOfElement(Point location, Dimension size) {
        return new Point(location.getX() + size.getWidth() / 2, location.getY() + size.getHeight() / 2);
    }

    public void tap(WebElement el) {
        Point location = el.getLocation();
        Dimension size = el.getSize();
        Point center = getCenterOfElement(location, size);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), center))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(600)))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(sequence));
    }

    public void swipe(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int startX = size.getWidth() / 2;
        int startY = size.getHeight() / 2;
        int endX = startX;
        int endY = (int) (size.getHeight() * 0.1);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1);
        sequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), endX, endY));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(sequence));
    }

    public void swipeUntilElementFound(By el, AppiumDriver driver) {
        int counter = 15;
        int alreadyUsed = 0;
        while (driver.findElements(el).isEmpty()) {
            if (alreadyUsed > counter) {
                driver.findElement(el);
            }
            swipe(driver);
            ++alreadyUsed;
        }
    }

    public void swipeElementToLeft(By by) {
        WebElement element = waitForElementPresent(by);
        Rectangle rect = element.getRect();
        int centralX = rect.getX() + rect.getWidth() / 2;
        int centralY = rect.getY() + rect.getHeight() / 2;
        int endX = 0;
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centralX, centralY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(200)))
                .addAction(finger.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), endX, centralY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(sequence));
    }
}
