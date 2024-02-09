package org.wiki;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.ScreenOrientation;

import java.net.MalformedURLException;
import java.net.URL;

public class Platform {
    private final static String PLATFORM_ANDROID = "ANDROID";
    private final static String PLATFORM_IOS = "IOS";
    private final static String APPIUM_URL = "http://127.0.0.1:4723";
    private final static String BROWSERSTACK_URL = "https://annaosynska_hPi9a1:URAjTJqUSwb3iknGPwZd@hub.browserstack.com/wd/hub";

    private static final String PLATFORM_BROWSERSTACK_IOS = "IOS_BROWSERSTACK";
    private static final String PLATFORM_BROWSERSTACK_ANDROID = "ANDROID_BROWSERSTACK";
    private static AppiumDriver driver;

    private Platform() {
    }

    public static AppiumDriver getDriver() {
        if (driver != null && driver.getSessionId() != null) {
            return driver;
        }
        try {
            if (isAndroid()) {
                driver = new AndroidDriver(new URL(APPIUM_URL), getAndroidOptions());
            } else if (isIOS()) {
                driver = new IOSDriver(new URL(APPIUM_URL), getIOSoptions());
            } else if (isIOSBrowserstack()) {
                driver = new IOSDriver(new URL(BROWSERSTACK_URL), getIOSBrowserstackOptions());
            } else if (isAndroidBrowserstack()) {
                driver = new AndroidDriver(new URL(BROWSERSTACK_URL), getAndroidBrowserstackOptions());
            } else {
                throw new RuntimeException("incorrect driver");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("incorrect url to appium driver");
        }
        return driver;
    }

    private static UiAutomator2Options getIOSoptions() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("iOS");
        options.setPlatformVersion("17.0");
        options.setApp(System.getProperty("user.dir") + "/apps/Wikipedia.app");
        options.setDeviceName("iPhone 15");
        options.setOrientation(ScreenOrientation.PORTRAIT);
        options.setAutomationName(AutomationName.IOS_XCUI_TEST);
        return options;
    }

    private static UiAutomator2Options getAndroidOptions() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setPlatformVersion("13.0");
        options.setAutoGrantPermissions(true);
        options.setApp(System.getProperty("user.dir") + "/apps/org.wikipedia_v2.7.50437-r-2023-04-12-50437_Android-5.0.apk");
        options.setDeviceName("Pixel XL");
        options.setAppPackage("org.wikipedia");
        options.setAppActivity("org.wikipedia.main.MainActivity");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        return options;
    }

    private static UiAutomator2Options getIOSBrowserstackOptions() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setCapability("browserstack.user", getBrowserstackUser());
        options.setCapability("browserstack.key", getBrowserstackKey());
        options.setOrientation(ScreenOrientation.PORTRAIT);
        options.setAutomationName(AutomationName.IOS_XCUI_TEST);
        return options;
    }

    private static MutableCapabilities getAndroidBrowserstackOptions() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setCapability("browserstack.user", getBrowserstackUser());
        options.setCapability("browserstack.key", getBrowserstackKey());
        options.setOrientation(ScreenOrientation.PORTRAIT);
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        return options;
    }

    public static boolean isAndroid() {
        return getPlatform().equals(PLATFORM_ANDROID);
    }

    public static boolean isIOS() {
        return getPlatform().equals(PLATFORM_IOS);
    }

    public static boolean isIOSBrowserstack() {
        return getPlatform().equals(PLATFORM_BROWSERSTACK_IOS);
    }

    public static boolean isAndroidBrowserstack() {
        return getPlatform().equals(PLATFORM_BROWSERSTACK_ANDROID);
    }

    private static String getPlatform() {
        return System.getenv("PLATFORM").toUpperCase();
    }

    private static String getBrowserstackUser() {
        return System.getenv("userName");
    }

    private static String getBrowserstackKey() {
        return System.getenv("accessKey");
    }
}
