package WebAppPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ForgotPasswordPage {
    private final WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"examples\"]/div[1]/div[4]/div/div/h3/a")
    private WebElement forgotPasswordPageButton;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id=\"forgot_password\"]/button")
    private WebElement retrievePasswordButton;

    @FindBy(id = "confirmation-alert")
    private WebElement message;

    public void scrollPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickForgotPasswordPageButton() {
        forgotPasswordPageButton.click();
        if (!driver.getCurrentUrl().contains("forgot-password")) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.querySelectorAll('ins.adsbygoogle, iframe').forEach(el => el.remove());");
            forgotPasswordPageButton.click();
        }
    }


    public void enterEmailAddress(String email) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,75)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        emailField.sendKeys(email);
    }

    public void clickRetrievePasswordButton() {
        retrievePasswordButton.click();
    }

    public void isSuccessMessageDisplayed() {
        Assert.assertEquals(message.getText(), "An e-mail has been sent to you which explains how to reset your password.", "Reset failed");
    }
}