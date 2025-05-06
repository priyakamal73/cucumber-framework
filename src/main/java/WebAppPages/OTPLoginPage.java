package WebAppPages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OTPLoginPage {
    private final WebDriver driver;
    int otp;

    public OTPLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(partialLinkText = "OTP: One Time Password")
    private WebElement otpLoginPageButton;

    @FindBy(id = "aswift_9")
    private WebElement iframe;

    @FindBy(xpath = "//*[@id=\"dismiss-button\"]")
    private WebElement adDismissButton;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "btn-send-otp")
    private WebElement sendOtpCodeButton;

    @FindBy(id = "otp-message")
    private WebElement otpMessage;

    @FindBy(xpath = "//button[@data-original-title='Copy email']")
    private WebElement tempMailCopyButton;

    @FindBy(xpath = "//span[@title='Your OTP Code']")
    private WebElement tempMail;

    @FindBy(xpath = "//div[@class='with-plain-text message__content']//span")
    private WebElement tempMailText;

    @FindBy(id = "otp")
    private WebElement otpCodeField;

    @FindBy(id = "btn-send-verify")
    private WebElement verifyOtpCodeButton;

    @FindBy(id = "flash")
    private WebElement message;

    @FindBy(xpath = "//i[contains(text(), 'Logout')]")
    private WebElement logoutButton;


    public void openTempEmailSiteAndGetEmail() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open('https://temp-mail.io/en', '_blank');");


        String originalWindow = driver.getWindowHandle();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        tempMailCopyButton.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.switchTo().window(originalWindow);
    }

    public void clickOTPLoginPageButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        js.executeScript("arguments[0].click()", otpLoginPageButton);
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

    public String enterEmailAddress() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        emailField.click();

        emailField.sendKeys(Keys.CONTROL + "V");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return emailField.getAttribute("value");
    }

    public void clickSendOTPCodeButton() {
        sendOtpCodeButton.click();
    }

    public String returnOTPMessage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 250)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return otpMessage.getText();
    }

    public int getOTPCodeFromEmail()  {
        // Store the original window handle
        String originalWindow = driver.getWindowHandle();
        // Switch to the new tab (second tab)
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        tempMail.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(tempMailText));

        String text = tempMailText.getText();
        Pattern pattern = Pattern.compile("\\b\\d{6}\\b");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            otp = Integer.parseInt(matcher.group());
        }
        driver.switchTo().window(originalWindow);

        return otp;
    }

    public void enterOTP(int otp) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        if(driver.getCurrentUrl().contains("https://practice.expandtesting.com/#google_vignette")){

            js.executeScript("document.querySelectorAll('ins.adsbygoogle, iframe').forEach(el => el.remove());");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        js.executeScript("arguments[0].scrollIntoView(true);",otpCodeField);
        String OTP = String.valueOf(otp);
        otpCodeField.sendKeys(OTP);
    }

    public void clickVerifyOTPCodeButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", verifyOtpCodeButton);
        verifyOtpCodeButton.click();
    }

    public String returnCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String returnMessage() {
        return message.getText();
    }

    public void logout() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 260)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logoutButton.click();
    }
}