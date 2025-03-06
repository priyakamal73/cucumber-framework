package MobileAppPages;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotificationPage {
    private final AndroidDriver driver;

    public NotificationPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.android.systemui:id/keyguard_message_area_container")
    private WebElement notificationArea;

    public void openNotificationContainer() {
        driver.openNotifications();
        driver.executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) notificationArea).getId(),
                "direction", "down",
                "percent", 0.5
        ));
    }

    public boolean toggleAirplaneMode() {
        driver.toggleAirplaneMode();
        return true;
    }
}