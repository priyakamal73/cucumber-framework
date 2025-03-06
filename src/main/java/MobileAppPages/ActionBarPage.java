package MobileAppPages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertTrue;

public class ActionBarPage {

    public ActionBarPage(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(accessibility = "App")
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
        appPage.click();
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