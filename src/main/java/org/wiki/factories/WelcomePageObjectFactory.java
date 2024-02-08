package org.wiki.factories;

import org.wiki.Platform;
import org.wiki.pages.android.WelcomePageAndroid;
import org.wiki.pages.ios.WelcomePageIOS;
import org.wiki.pages.pageObjects.WelcomePageObject;

public class WelcomePageObjectFactory {
    public static WelcomePageObject get() {
        if(Platform.isAndroid() || Platform.isAndroidBrowserstack()) {
            return new WelcomePageAndroid();
        }
        else return new WelcomePageIOS();
    }
}
