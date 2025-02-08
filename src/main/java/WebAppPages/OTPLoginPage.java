package WebAppPages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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

    @FindBy(xpath = "//*[@id=\"__layout\"]/div/header/div[3]/button[1]")
    private WebElement tempMailCopyButton;

    @FindBy(xpath = "//span[@title='Your OTP Code']")
    private WebElement tempMail;

    @FindBy(xpath = "//*[@id=\"__layout\"]/div/main/div/article/div/div[3]/span")
    private WebElement tempMailText;

    @FindBy(id = "otp")
    private WebElement otpCodeField;

    @FindBy(id = "btn-send-verify")
    private WebElement verifyOtpCodeButton;

    @FindBy(id = "flash")
    private WebElement message;

    @FindBy(xpath = "/html/body/main/div[4]/div/a")
    private WebElement logoutButton;


    public void openTempEmailSiteAndGetEmail() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open('https://temp-mail.io/en', '_blank');");

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
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //js.executeScript("arguments[0].click()", tempMailCopyButton);
        tempMailCopyButton.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Switch back to the original tab
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
        js.executeScript("window.scrollBy(0, 400)");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        emailField.click();
        emailField.sendKeys(Keys.CONTROL + "V");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String email = emailField.getAttribute("value");
        return email;
    }

    public void clickSendOTPCodeButton() {
        sendOtpCodeButton.click();
    }

    public void isConfirmationMessageDisplayed(String email) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 250)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(otpMessage.getText(), "We've sent an OTP code to your email: " + email, "The success message is not displayed");
    }

    public int getOTPCodeFromEmail() {
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
        String OTP = String.valueOf(otp);
        otpCodeField.sendKeys(OTP);
    }

    public void clickVerifyOTPCodeButton() {
        verifyOtpCodeButton.click();
    }

    public void isSecurePageRedirected() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://practice.expandtesting.com/secure", "Login Failed");
    }

    public void isSuccessMessageDisplayed() {
        Assert.assertEquals(message.getText(), "You logged into a secure area!", "Login Failed");
    }

    public void logout() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 100)");
        logoutButton.click();
    }
}