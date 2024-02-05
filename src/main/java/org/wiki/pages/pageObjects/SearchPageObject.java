package org.wiki.pages.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class SearchPageObject extends BasePageObject {
    protected By SEARCH_INPUT;
    protected By INIT_SEARCH;
    protected String PATTERN;
    protected By SEARCH_RESULT;
    protected By SEARCH_VIEW;
    protected By CANCEL_BUTTON;
    protected By SHARE;

    public void initSearchInput() {
        waitForElementAndClick(INIT_SEARCH);
    }

    public void typeSearchLine(String text) {
        waitForElementAndSendKeys(SEARCH_INPUT, text);
    }

    public void clearSearchInput() {
        waitForElementAndClear(SEARCH_INPUT);
    }

    public boolean isSearchViewEmpty() {
        return waitForElementPresent(SEARCH_VIEW).isDisplayed();
    }

    public WebElement getFirstSearchResult() {
        return waitForElementPresent(SEARCH_RESULT);
    }

    public void openFirstArticle() {
        waitForElementAndClick(SEARCH_RESULT);
    }

    public boolean isArticleWithTextPresent(String text) {
        return waitForElementPresent(By.xpath(String.format(PATTERN, text))).isDisplayed();
    }

    public boolean isCancelButtonAbsent() {
        return driver.findElements(CANCEL_BUTTON).isEmpty();
    }

    public boolean isShareLinkPresent() {
        return waitForElementPresent(SHARE).isDisplayed();
    }

    public abstract String getArticleTitle(String text);

    public abstract void doubleTap(WebElement el);
}
