package org.wikipedia.qa.stepDefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.net.MalformedURLException;

import static org.wikipedia.qa.stepDefs.BaseTest.listPage;
import static org.wikipedia.qa.stepDefs.BaseTest.searchPage;

public class ArticleListStep {

    @When("I open first article")
    public void iOpenFirstArticle() throws MalformedURLException {
        searchPage.openFirstArticle();
    }

    @When("I click save button")
    public void iClickSaveButton() throws MalformedURLException {
        listPage.clickSaveIcon();
    }

    @When("I add article to new {string} list")
    public void iAddArticleToNewList(String text) {
        listPage.clickAddToList();
        listPage.enterListName(text);
        listPage.clickOkButton();
    }

    @When("I open newly created list")
    public void iOpenNewlyCreatedList() {
        listPage.clickOnListIcon();
    }

    @Then("article should be present in the list")
    public void articleShouldBePresentInTheList() {
        Assertions.assertTrue(listPage.isArticlePresent());
    }

    @When("I swipe to delete article from list")
    public void iSwipeToDeleteArticleFromList() {
        listPage.deleteArticleBySwipe();
    }

    @Then("list should be empty")
    public void listShouldBeEmpty() {
        Assertions.assertTrue(listPage.isListEmpty());
    }
}
