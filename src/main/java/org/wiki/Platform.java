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
    private final static String SAUCE_LABS_URL = "https://ondemand.us-west-1.saucelabs.com:443/wd/hub";

    private static final String PLATFORM_SAUCE_IOS = "IOS_SAUCE_LABS";
    private static final String PLATFORM_SAUCE_ANDROID = "ANDROID_SAUCE_LABS";
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
            } else if (isIOSSauceLabs()) {
                driver = new IOSDriver(new URL(SAUCE_LABS_URL), getIOSOptionsSauceLabs());
            }
            else if (isAndroidSauceLabs()) {
                driver = new AndroidDriver(new URL(SAUCE_LABS_URL), getAndroidOptionsSauceLabs());
            }
            else {
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

    private static MutableCapabilities getIOSOptionsSauceLabs() {
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("appium:app", "storage:filename=wiki.ipa");
        caps.setCapability("appium:deviceName", "Android GoogleAPI Emulator");
        caps.setCapability("appium:platformVersion", "12.0");
        caps.setCapability("appium:automationName", "XCUITest");
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", "oauth-silcheva.anna-b61fa");
        sauceOptions.setCapability("accessKey", "13e497e9-0c21-4a44-8028-c1dece48931c");
        sauceOptions.setCapability("build", "appium-build-3JP99");
        sauceOptions.setCapability("name", "<your test name>");
        sauceOptions.setCapability("deviceOrientation", "PORTRAIT");
        caps.setCapability("sauce:options", sauceOptions);
        return caps;
    }
    private static MutableCapabilities getAndroidOptionsSauceLabs() {
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:app", "storage:filename=org.wikipedia_v2.7.50437-r-2023-04-12-50437_Android-5.0.apk");
        caps.setCapability("appium:deviceName", "Android GoogleAPI Emulator");
        caps.setCapability("appium:platformVersion", "12.0");
        caps.setCapability("appium:automationName", "UiAutomator2");
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", "oauth-silcheva.anna-b61fa");
        sauceOptions.setCapability("accessKey", "13e497e9-0c21-4a44-8028-c1dece48931c");
        sauceOptions.setCapability("build", "appium-build-3JP99");
        sauceOptions.setCapability("name", "<your test name>");
        sauceOptions.setCapability("deviceOrientation", "PORTRAIT");
        caps.setCapability("sauce:options", sauceOptions);
        return caps;
    }

    public static boolean isAndroid() {
        return getPlatform().equals(PLATFORM_ANDROID);
    }

    public static boolean isIOS() {
        return getPlatform().equals(PLATFORM_IOS);
    }

    private static boolean isIOSSauceLabs() {
        return getPlatform().equals(PLATFORM_SAUCE_IOS);
    }
    private static boolean isAndroidSauceLabs() {
        return getPlatform().equals(PLATFORM_SAUCE_ANDROID);
    }
    private static String getPlatform() {
        return System.getenv("PLATFORM").toUpperCase();
    }
}
