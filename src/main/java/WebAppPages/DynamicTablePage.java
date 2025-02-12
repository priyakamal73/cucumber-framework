package WebAppPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class DynamicTablePage {

    private final WebDriver driver;
    int headerIndex;
    int rowIndex;
    String chromeCPU;

    public DynamicTablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Dynamic Table")
    private WebElement dynamicTablePage;

    @FindBy(xpath = "//table[@class='table table-striped']/thead/tr/th")
    private List<WebElement> tableHeaders;

    @FindBy(xpath = "//table//tr/td[1]")
    private List<WebElement> tableRows;

    @FindBy(id = "chrome-cpu")
    private WebElement yellowLabel;

    public void clickDynamicTablePageButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,650)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        dynamicTablePage.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        js.executeScript("document.querySelectorAll('ins.adsbygoogle, iframe').forEach(el => el.remove());");

        dynamicTablePage.click();
    }

    public void getCPULoadFromTable() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,420)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        headerIndex = 0;

        //finding table header
        List<WebElement> headers = tableHeaders;
        for (int i = 0; i < headers.size(); i++) {
            String headerText = headers.get(i).getText();
            if (headerText.equals("CPU")) {
                headerIndex = i;
                break;
            }
        }

        rowIndex = 0;

        //finding table row value
        List<WebElement> rows = tableRows;
        for (int i = 0; i < rows.size(); i++) {
            String rowText = rows.get(i).getText();
            if (rowText.equals("Chrome")) {
                rowIndex = i;
                break;
            }
        }
        chromeCPU = "Chrome CPU: " + driver.findElement(By.xpath("//table//tr[" + (rowIndex + 1) + "]/td[" + (headerIndex + 1) + "]")).getText();
    }

    public void compareValues() {
        Assert.assertEquals(chromeCPU, yellowLabel.getText(), "The Chrome CPU values are different");
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }
}