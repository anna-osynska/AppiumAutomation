package org.wiki.pages.android;

import org.openqa.selenium.By;
import org.wiki.pages.pageObjects.ListArticlesObject;


public class ListArticlesAndroid extends ListArticlesObject {
    public ListArticlesAndroid() {
        LIST_NAME_INPUT = By.id("org.wikipedia:id/text_input");
        OK_BUTTON = By.id(("android:id/button1"));
        ADD_TO_LIST = By.id("org.wikipedia:id/snackbar_action");
        SAVE_ICON = By.id("org.wikipedia:id/page_save");
        LIST_ICON = By.xpath("//*[@text='VIEW LIST']");
        ARTICLE = By.xpath("//*[@resource-id=\"org.wikipedia:id/reading_list_recycler_view\"]/android.view.ViewGroup[2]");
        EMPTY_LIST = By.id("org.wikipedia:id/reading_list_empty_text");
        VIEW_LIST = By.id("VIEW LIST");
    }

    public void deleteArticleBySwipe() {
        swipeElementToLeft(ARTICLE);
    }

    public void clickAddToList() {
        waitForElementAndClick(ADD_TO_LIST);
    }
}
