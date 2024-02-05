package org.wiki.pages.ios;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.wiki.pages.pageObjects.ListArticlesObject;


public class ListArticlesIOS extends ListArticlesObject {
    public ListArticlesIOS() {
        LIST_NAME_INPUT = AppiumBy.iOSNsPredicateString("value == 'reading list title'");
        OK_BUTTON = AppiumBy.iOSNsPredicateString("name == 'Create reading list'");
        ADD_TO_LIST = AppiumBy.iOSNsPredicateString("name == \"Add “Java” to a reading list?\"");
        SAVE_ICON = By.xpath("//*[@name='Save for later']");
        LIST_ICON = By.xpath("//*[contains(@label, 'Article added to')]");
        ARTICLE = By.xpath("//XCUIElementTypeCell/XCUIElementTypeOther[@visible='true']");
        EMPTY_LIST = AppiumBy.iOSNsPredicateString("name == 'No pages saved to this list'");
        CREATE_A_NEW_LIST = AppiumBy.iOSNsPredicateString("name == 'Create a new list'");
        CLOSE = By.id("Close");
        DELETE = By.id("swipe action delete");
    }

    public void deleteArticleBySwipe() {
        swipeElementToLeft(ARTICLE);
        waitForElementAndClick(DELETE);
    }

    public void clickAddToList() {
        waitForElementAndClick(ADD_TO_LIST);
        waitForElementAndClick(CREATE_A_NEW_LIST);
    }
}