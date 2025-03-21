package MobileAppPages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AlertPage {
    private final AndroidDriver driver;

    public AlertPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(accessibility = "App")
    private WebElement appPage;

    @AndroidFindBy(accessibility = "Alert Dialogs")
    private WebElement alertDialogsButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"OK Cancel dialog with traditional theme\"]")
    private WebElement traditionalThemeAlert;

    @AndroidFindBy(accessibility = "android:id/button1")
    private WebElement acceptButton;

    @AndroidFindBy(accessibility = "android:id/button2")
    private WebElement dismissButton;

    @AndroidFindBy(accessibility = "OK Cancel dialog with Holo Light theme")
    private WebElement holoThemeAlert;

    @AndroidFindBy(accessibility = "OK Cancel dialog with a message")
    private WebElement normalAlert;

    @AndroidFindBy(accessibility = "Text Entry dialog")
    private WebElement textEntryAlert;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"io.appium.android.apis:id/username_edit\"]")
    private WebElement usernameField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"io.appium.android.apis:id/password_edit\"]")
    private WebElement passwordField;

    @AndroidFindBy(id = "io.appium.android.apis:id/screen")
    private WebElement alertListingPage;


    public void navigateToAlertPage(){
        appPage.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(alertDialogsButton));
        alertDialogsButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickTraditionalThemeAlert(){
        traditionalThemeAlert.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void acceptAlert(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void isAlertListingPageDisplayed(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(alertListingPage.isDisplayed());
    }
}