package org.wikipedia.qa.stepDefs;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;


import static org.wikipedia.qa.stepDefs.BaseTest.mainPage;
import static org.wikipedia.qa.stepDefs.BaseTest.searchPage;

public class SearchStep {
    private String titleBeforeRotation;
    private String titleAfterRotation;

    @When("I search for word {string}")
    public void iSearchForWord(String word) {
        searchPage.initSearchInput();
        searchPage.typeSearchLine(word);
    }

    @Then("article with title {string} should be shown")
    public void articleWithTitleShouldBeShown(String title) {
        Assertions.assertEquals(title,
                searchPage.getArticleTitle("Java (programming language)"));
    }

    @Then("{string} text should be shown")
    public void textShouldBeShown(String text) {
        Assertions.assertTrue(searchPage.isSearchViewEmpty());
    }

    @When("I clear search line")
    public void iClearSearchLine() {
        searchPage.clearSearchInput();
    }

    @Then("Cancel button should be absent")
    public void cancelButtonShouldBeAbsent() {
        Assertions.assertTrue(searchPage.isCancelButtonAbsent());
    }

    @When("double tap on first result")
    public void doubleTapOnFirstResult() {
        searchPage.doubleTap(searchPage.getFirstSearchResult());
    }

    @Step("article '{text}'")
    @Then("article with name {string} should be present")
    public void articleWithNameShouldBePresent(String text) {
        Assertions.assertTrue(searchPage.isArticleWithTextPresent(text));
    }

    @When("I get first article title")
    public void iGetFirstArticleTitle() {
        titleBeforeRotation = mainPage.getFirstFeaturedArticle();
    }

    @When("I get first article title again")
    public void iGetFirstArticleTitleAgain() {
        titleAfterRotation = mainPage.getFirstFeaturedArticle();
    }

    @Then("the same article should be shown")
    public void theSameArticleShouldBeShown() {
        Assertions.assertEquals(titleBeforeRotation, titleAfterRotation);
    }
}
