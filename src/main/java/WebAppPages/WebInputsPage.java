package WebAppPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WebInputsPage {

    private final WebDriver driver;

    public WebInputsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='/inputs']")
    private WebElement webInputsPageButton;

    @FindBy(id = "input-number")
    private WebElement numberField;

    @FindBy(id = "input-text")
    private WebElement textField;

    @FindBy(id = "input-password")
    private WebElement passwordField;

    @FindBy(id = "input-date")
    private WebElement dateField;

    @FindBy(id = "btn-display-inputs")
    private WebElement displayInputsButton;

    @FindBy(id = "output-number")
    private WebElement numberOutputField;

    @FindBy(id = "output-text")
    private WebElement textOutputField;

    @FindBy(id = "output-password")
    private WebElement passwordOutputField;

    @FindBy(id = "output-date")
    private WebElement dateOutputField;

    @FindBy(id = "btn-clear-inputs")
    private WebElement clearInputsButton;


    public void scrollPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void clickInputsPageButton() {
        webInputsPageButton.click();
        if(driver.getCurrentUrl().contains("https://practice.expandtesting.com/#google_vignette")){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.querySelectorAll('ins.adsbygoogle, iframe').forEach(el => el.remove());");
            webInputsPageButton.click();
        }
    }
    public void enterNumber(String number) {
        numberField.sendKeys(number);
    }
    public void enterText(String text) {
        textField.sendKeys(text);
    }
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }
    public void enterDate(String date) {
        dateField.sendKeys(date);
    }
    public void clickDisplayInputs() {
        displayInputsButton.click();
    }

    public String returnNumber(){
        return numberOutputField.getText();
    }

    public String returnText(){
        return textOutputField.getText();
    }

    public String returnPassword(){
        return passwordOutputField.getText();
    }

    public String returnDate(){
        return dateOutputField.getText();
    }

    public void clearInputs(){
         clearInputsButton.click();
    }
}