package WebAppPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class RegistrationPage {
    private final WebDriver driver;

    // Constructor to inject WebDriver
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"examples\"]/div[1]/div[3]/div/div/h3/a")
    private WebElement registerPageButton;

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement registerButton;

    @FindBy(id = "flash")
    private WebElement message;


    // Actions on the registration page
    public void clickRegisterPageLink(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click()",registerPageButton);
    }

    public void scrollPage(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,350)");
    }
    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void clickRegisterButton() {
        registerButton.click();
    }

    public void isSuccessMessageDisplayed() {
        Assert.assertEquals(message.getText(),"Successfully registered, you can log in now.","Registration Failed");
    }
    public void isLoginPageRedirected() {
        Assert.assertEquals(driver.getCurrentUrl(),"https://practice.expandtesting.com/login","Registration Failed");
    }
    public void isErrorMessageDisplayed() {
        Assert.assertEquals(message.getText(),"An error occurred during registration. Please try again.","Registration Successful");
    }
    public void isRegisterpageRetained() {
        Assert.assertEquals(driver.getCurrentUrl(),"https://practice.expandtesting.com/register","Registration Successful");
    }
}