package org.wiki.factories;

import org.wiki.Platform;
import org.wiki.pages.android.ListArticlesAndroid;
import org.wiki.pages.ios.ListArticlesIOS;
import org.wiki.pages.pageObjects.ListArticlesObject;

public class ListArticlesObjectFactory {

    public static ListArticlesObject get() {
        if (Platform.isAndroid() || Platform.isAndroidBrowserstack()) {
            return new ListArticlesAndroid();
        } else {
            return new ListArticlesIOS();
        }
    }
}
