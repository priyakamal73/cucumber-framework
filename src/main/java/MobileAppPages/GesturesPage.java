package MobileAppPages;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GesturesPage {
    private final AndroidDriver driver;

    public GesturesPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Views\"]")
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(viewsPage));

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

    public String returnMessage() {
        return successMessage.getText();

    }
}