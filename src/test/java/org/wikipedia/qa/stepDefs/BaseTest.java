package org.wikipedia.qa.stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.wiki.Platform;
import org.wiki.factories.ListArticlesObjectFactory;
import org.wiki.factories.MainPageObjectFactory;
import org.wiki.factories.SearchPageObjectFactory;
import org.wiki.factories.WelcomePageObjectFactory;
import org.wiki.pages.pageObjects.ListArticlesObject;
import org.wiki.pages.pageObjects.MainPageObject;
import org.wiki.pages.pageObjects.SearchPageObject;
import org.wiki.pages.pageObjects.WelcomePageObject;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class BaseTest {
    protected static SearchPageObject searchPage;
    protected static MainPageObject mainPage;
    protected static ListArticlesObject listPage;

    @Step("Setting up the app")
    @Before
    public void setUp() {
        WelcomePageObject welcomePage = WelcomePageObjectFactory.get();
        searchPage = SearchPageObjectFactory.get();
        mainPage = MainPageObjectFactory.get();
        listPage = ListArticlesObjectFactory.get();
        welcomePage.clickSkipButton();
    }

    @Step("Tear down")
    @After
    public void tearDown(Scenario scenario) {
        takeScreenshotIfFailed(scenario);
        Platform.getDriver().quit();
    }

    public void takeScreenshotIfFailed(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot takesScreenshot = Platform.getDriver();
            byte[] source = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failed screenshot", new ByteArrayInputStream(source));
        }
    }
}
