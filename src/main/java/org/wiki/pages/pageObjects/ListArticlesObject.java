package org.wiki.pages.pageObjects;

import org.openqa.selenium.By;

public abstract class ListArticlesObject extends BasePageObject {
    protected By LIST_NAME_INPUT;
    protected By OK_BUTTON;
    protected By ADD_TO_LIST;
    protected By SAVE_ICON;
    protected By LIST_ICON;
    protected By ARTICLE;
    protected By EMPTY_LIST;
    protected By CREATE_A_NEW_LIST;
    protected By CLOSE;
    protected By DELETE;
    protected By VIEW_LIST;

    public void enterListName(String name) {
        waitForElementAndSendKeys(LIST_NAME_INPUT, name);
    }

    public void clickOkButton() {
        waitForElementAndClick(OK_BUTTON);
    }
    public void clickSaveIcon() {
        waitForElementAndClick(SAVE_ICON);
    }

    public void clickOnListIcon() {
        waitForElementAndClick(LIST_ICON);
    }

    public boolean isArticlePresent() {
        return waitForElementPresent(ARTICLE).isDisplayed();
    }

    public boolean isListEmpty() {
        return waitForElementPresent(EMPTY_LIST).isDisplayed();
    }

    public abstract void clickAddToList();

    public abstract void deleteArticleBySwipe();
}
