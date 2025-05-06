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

    @FindBy(linkText = "Forgot Password Form")
    private WebElement forgotPasswordPageButton;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement retrievePasswordButton;

    @FindBy(id = "confirmation-alert")
    private WebElement message;

    public void scrollPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",forgotPasswordPageButton);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickForgotPasswordPageButton() {
        forgotPasswordPageButton.click();
        if (!driver.getCurrentUrl().contains("forgot-password")) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.querySelectorAll('ins.adsbygoogle, iframe').forEach(el => el.remove());");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            js.executeScript("window.scrollBy(0,-400)");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            forgotPasswordPageButton.click();
        }
    }


    public void enterEmailAddress(String email) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0,500)");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        emailField.sendKeys(email);
    }

    public void clickRetrievePasswordButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        js.executeScript("arguments[0].click()", retrievePasswordButton);
    }

    public String returnMessage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,150)");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return message.getText();
    }
}