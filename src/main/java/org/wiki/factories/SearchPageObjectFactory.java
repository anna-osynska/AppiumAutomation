package org.wiki.factories;


import org.wiki.Platform;
import org.wiki.pages.android.SearchPageAndroid;
import org.wiki.pages.ios.SearchPageIOS;
import org.wiki.pages.pageObjects.SearchPageObject;

public class SearchPageObjectFactory {

    public static SearchPageObject get() {
        if (Platform.isAndroid() || Platform.isAndroidBrowserstack()) {
            return new SearchPageAndroid();
        } else {
            return new SearchPageIOS();
        }
    }
}
