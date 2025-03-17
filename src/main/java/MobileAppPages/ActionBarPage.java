package MobileAppPages;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class ActionBarPage {

    private final AndroidDriver driver;

    public ActionBarPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"App\"]")
    private WebElement appPage;

    @AndroidFindBy(accessibility = "Action Bar")
    private WebElement actionBarPage;

    @AndroidFindBy(accessibility = "Display Options")
    private WebElement displayOptionsPage;

    @AndroidFindBy(accessibility = "DISPLAY_HOME_AS_UP")
    private WebElement displayHomeAsUpButton;

    @AndroidFindBy(accessibility = "Navigate up")
    private WebElement backButton;

    public void navigateToDisplayOptionsPage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(appPage));

        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) appPage).getId()
        ));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        actionBarPage.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        displayOptionsPage.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickDisplayHomeAsUpButton() {
        displayHomeAsUpButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void isBackButtonDisplayed() {
        assertTrue(backButton.isDisplayed());
    }

    public boolean isBackButtonHidden() {
        try {
            return backButton.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }
}