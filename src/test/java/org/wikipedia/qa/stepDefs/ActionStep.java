package org.wikipedia.qa.stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static org.wikipedia.qa.stepDefs.BaseTest.mainPage;
import static org.wikipedia.qa.stepDefs.BaseTest.searchPage;

public class ActionStep {

    @When("I tap on first article")
    public void iTapOnFirstArticle() {
        searchPage.tap(searchPage.getFirstSearchResult());
    }
    @Step
    @Then("share option should be present")
    public void shareOptionShouldBePresent() {
        Assertions.assertTrue(searchPage.isShareLinkPresent());
    }

    @When("I swipe to footer")
    public void iSwipeToFooter() {
        mainPage.swipeUntilFooter();
    }

    @Then("footer should be shown")
    public void footerShouldBeShown() {
        Assertions.assertTrue(mainPage.isFooterPresent());
    }

    @Then("app runs in the background")
    public void appRunsInTheBackground() {
        mainPage.runInBackground();
    }
    @Step
    @And("I rotate devise")
    public void iRotateDevise() {
        mainPage.runInBackground();
    }
}
