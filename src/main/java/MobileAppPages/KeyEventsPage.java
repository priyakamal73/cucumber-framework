package MobileAppPages;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class KeyEventsPage {

    private final AndroidDriver driver;

    public KeyEventsPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Views\"]")
    private WebElement viewsPage;

    @AndroidFindBy(xpath = "//android.widget.ListView[@resource-id=\"android:id/list\"]")
    private WebElement scrollArea;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"TextFields\"]")
    private WebElement textFieldsPage;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"io.appium.android.apis:id/edit\"]")
    private WebElement textAreaLine;

    public void clickViewsPage() {
        viewsPage.click();

        driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) scrollArea).getId(),
                "direction", "down",
                "percent", 2.5
        ));
    }

    public void clicktextFieldsPage(){
        textFieldsPage.click();
    }

    public void clickFirstLineOfEditableArea() {
        textAreaLine.click();
    }

    public void typeText() {
        driver.pressKey(new KeyEvent(AndroidKey.H));
        driver.pressKey(new KeyEvent(AndroidKey.I));
        driver.pressKey(new KeyEvent(AndroidKey.SPACE));
        driver.pressKey(new KeyEvent(AndroidKey.T));
        driver.pressKey(new KeyEvent(AndroidKey.H));
        driver.pressKey(new KeyEvent(AndroidKey.E));
        driver.pressKey(new KeyEvent(AndroidKey.R));
        driver.pressKey(new KeyEvent(AndroidKey.E));
    }

    public void isTextSeen(){
        Assert.assertTrue(textAreaLine.getText().contains("hi"));
    }
}
