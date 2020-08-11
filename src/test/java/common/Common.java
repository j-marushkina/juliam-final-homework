package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Julia Marushkina
 */
public class Common {

    public static final int TIME_OUT_IN_SECONDS = 15;

    private static WebDriver DRIVER;

    public static WebDriver getDriver() {
        return DRIVER;
    }

    public void startBrowser(String url) {
        WebDriverManager.chromedriver().setup();
        DRIVER = new ChromeDriver();
        JavascriptExecutor jse = (JavascriptExecutor)getDriver();
        jse.executeScript("window.scrollBy(0,500)");
        DRIVER.get(url);
        DRIVER.manage().window().maximize();
    }

    public void stopBrowser() {
        DRIVER.quit();
    }

    protected void waitUntilClickable(By by) {
        WebDriverWait wait = new WebDriverWait(DRIVER, TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    protected void waitUntilDisplayed(By by) {
        WebDriverWait wait = new WebDriverWait(DRIVER, TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected void moveAndClick(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).click().perform();
    }

//    protected void pressKey(CharSequence... keys) {
//     Actions actions = new Actions(getDriver());
//  actions.sendKeys(keys).perform();
//    }


    protected boolean exists(By by) {
        return DRIVER.findElements(by).size() > 0;
    }
}
