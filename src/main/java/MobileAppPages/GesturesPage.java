package MobileAppPages;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class GesturesPage {
    private final AndroidDriver driver;

    public GesturesPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(accessibility = "Views")
    private WebElement viewsPage;

    @AndroidFindBy(accessibility = "Drag and Drop")
    private WebElement dragAndDropPage;

    @AndroidFindBy(id = "io.appium.android.apis:id/drag_dot_3")
    private WebElement dotElement;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"io.appium.android.apis:id/drag_result_text\")")
    private WebElement successMessage;

    @AndroidFindBy(accessibility = "Gallery")
    private WebElement galleryPage;

    @AndroidFindBy(accessibility = "1. Photos")
    private WebElement photosPage;

    @AndroidFindBy(id = "io.appium.android.apis:id/gallery")
    private WebElement swipeArea;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").instance(2)")
    private WebElement lastPhoto;

    public void clickDragAndDropPage() {
        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) viewsPage).getId()
        ));

        dragAndDropPage.click();
    }

    public void longClick() {
        driver.executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) dotElement).getId(),
                "x", 210,
                "y", 660
        ));
    }

    public void dragAndDrop() {
        driver.executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) dotElement).getId(),
                "endX", 735,
                "endY", 1135
        ));
    }

    public void isMessageDisplayed() {
        Assert.assertEquals(successMessage.getText(), "Dropped!", "Drag and drop gesture not done");
    }

    public void galleryPage() {
        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) viewsPage).getId()
        ));

        galleryPage.click();
    }

    public void photoGalleryPage() {
        photosPage.click();
    }

    public void swipePhotos(){
        driver.executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId",((RemoteWebElement)swipeArea).getId(),
                "direction","left",
                "percent",0.5
        ));
    }

    public void isLastPhotoDisplayed(){
        Assert.assertTrue(lastPhoto.isDisplayed());
    }
}