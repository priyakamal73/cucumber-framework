package WebAppPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;

public class DigestAuthenticationPage {
    private final WebDriver driver;

    public DigestAuthenticationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Digest Authentication (user and pass: admin)")
    private WebElement digestAuthPage;

    @FindBy(xpath = "//p[@role=\"alert\"]")
    private WebElement message;

    public void clickDigestAuthLink() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,650)");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        digestAuthPage.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (!driver.getCurrentUrl().contains("basic-auth")) {
            js.executeScript("document.querySelectorAll('ins.adsbygoogle, iframe').forEach(el => el.remove());");
            digestAuthPage.click();
        }
    }

    public void enterCredentials(String username, String password) {
        try {
            Robot robot = new Robot();

            Thread.sleep(1000);

            //enter username
            for (char c : username.toCharArray()) {
                int keyCode = KeyEvent.getExtendedKeyCodeForChar(Character.toUpperCase(c));
                if (keyCode != KeyEvent.VK_UNDEFINED) {
                    robot.keyPress(keyCode);
                    robot.keyRelease(keyCode);
                }
            }

            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);

            //enter password
            for (char c : password.toCharArray()) {
                int keyCode = KeyEvent.getExtendedKeyCodeForChar(Character.toUpperCase(c));
                if (keyCode != KeyEvent.VK_UNDEFINED) {
                    robot.keyPress(keyCode);
                    robot.keyRelease(keyCode);
                }
            }

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            Thread.sleep(2000);

        } catch (AWTException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void isSuccessAlertDisplayed() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(message.getText(), "Congratulations! You must have the proper credentials.", "Authentication failed");
    }

}
