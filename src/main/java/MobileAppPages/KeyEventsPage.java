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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    @AndroidFindBy(id = "io.appium.android.apis:id/edit")
    private WebElement textAreaLine;

    public void clickViewsPage() {
        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) viewsPage).getId()
        ));
    }

    public void clicktextFieldsPage(){

        driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) scrollArea).getId(),
                "direction", "down",
                "percent", 2.5
        ));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(textFieldsPage));

        try {
            driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                    "elementId", ((RemoteWebElement) textFieldsPage).getId()
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    public String returnText(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return textAreaLine.getText();
    }
}