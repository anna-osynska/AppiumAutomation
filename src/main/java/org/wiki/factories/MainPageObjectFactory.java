package org.wiki.factories;

import org.wiki.Platform;
import org.wiki.pages.android.MainPageAndroid;
import org.wiki.pages.ios.MainPageIOS;
import org.wiki.pages.pageObjects.MainPageObject;

public class MainPageObjectFactory {
    public static MainPageObject get(){
        if(Platform.isAndroid() || Platform.isAndroidBrowserstack()) {
            return new MainPageAndroid();
        }
        else return new MainPageIOS();
    }
}
