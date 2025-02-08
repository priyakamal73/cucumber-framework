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

    @FindBy(id = "aswift_9")
    private WebElement iframe;

    @FindBy(xpath = "//*[@id=\"dismiss-button\"]")
    private WebElement adDismissButton;

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
    }
    public void dismissTheGoogleAd() {
        try {
            if (iframe.isDisplayed()) {
                driver.switchTo().frame(iframe);
                adDismissButton.click();
                driver.switchTo().defaultContent();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Ad or dismiss button not found. Continuing with the script");
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
    public void isOutputDisplayedAndAccurate(String number, String text, String password, String date) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date newDate = inputFormat.parse(date);
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = outputFormat.format(newDate);
            Assert.assertEquals(numberOutputField.getText(), number, "The number entered is not the same");
            Assert.assertEquals(textOutputField.getText(), text, "The text entered is not the same");
            Assert.assertEquals(passwordOutputField.getText(), password, "The password entered is not the same");
            Assert.assertEquals(dateOutputField.getText(), formattedDate, "The date entered is not the same");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void clearInputs(){
         clearInputsButton.click();
    }
}