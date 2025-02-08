package WebAppPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"examples\"]/div[1]/div[2]/div/div/h3/a")
    private WebElement loginPageButton;

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"login\"]/button")
    private WebElement loginButton;

    @FindBy(id = "flash")
    private WebElement message;

    @FindBy(xpath = "//a[@href=\"/logout\"]")
    private WebElement logoutButton;


    //Actions on the login page
    public void clickLoginPageLink() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        js.executeScript("arguments[0].click()", loginPageButton);
    }

    public void scrollPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,100)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        loginButton.click();
    }

    public void isSuccessMessageDisplayed() {
        Assert.assertEquals(message.getText(), "You logged into a secure area!", "Login Failed");
    }

    public void isSecurePageRedirected() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://practice.expandtesting.com/secure", "Login Failed");
    }

    public void clickLogoutButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logoutButton.click();
    }

    public void isErrorMessageDisplayed() {
        Assert.assertEquals(message.getText(), "Your password is invalid!", "Login Successful");
    }

    public void isLoginPageSeen() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://practice.expandtesting.com/login", "Login Successful");
    }
}