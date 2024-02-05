package org.wiki.pages.ios;

import io.appium.java_client.AppiumBy;
import org.wiki.pages.pageObjects.WelcomePageObject;

public class WelcomePageIOS extends WelcomePageObject {
    public WelcomePageIOS() {
        SKIP_BUTTON = AppiumBy.iOSNsPredicateString("name == 'Skip' AND label == 'Skip' AND value == 'Skip'");
    }
}
