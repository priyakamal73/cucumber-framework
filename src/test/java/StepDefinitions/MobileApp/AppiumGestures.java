package StepDefinitions.MobileApp;

import Hooks.Hooks;
import MobileAppPages.GesturesPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AppiumGestures {

    GesturesPage gesturesPage = new GesturesPage(Hooks.getAndroidDriver());

    @Given("I am on the Drag and Drop page")
    public void iAmOnTheDragAndDropPage() {
        gesturesPage.clickDragAndDropPage();
    }

    @When("I long click any dot element")
    public void i_long_click_any_dot_element() {
        gesturesPage.longClick();
    }

    @When("drag and drop the element")
    public void drag_and_drop_the_element() {
        gesturesPage.dragAndDrop();
    }

    @Then("the message dropped must be seen")
    public void theMessageDroppedMustBeSeen() {
        gesturesPage.isMessageDisplayed();
    }

    @Given("I am on the photo gallery page")
    public void iAmOnThePhotoGalleryPage() {
        gesturesPage.galleryPage();
        gesturesPage.photoGalleryPage();
    }

    @When("I swipe to the right")
    public void iSwipeToTheRight() {
        gesturesPage.swipePhotos();
    }

    @Then("the photos must be swiped to the right end")
    public void thePhotosMustBeSwipedToTheRightEnd() {
       gesturesPage.isLastPhotoDisplayed();
    }
}