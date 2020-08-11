package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        DRIVER.get(url);
        DRIVER.manage().window().maximize();
    }

    public void stopBrowser() {
        DRIVER.quit();
    }

    protected void waitForElementAndClick(By by) {
        waitUntilClickable(by);
        getDriver().findElement(by).click();
    }

    protected void waitForElementAndSendKeys(By by, String keys) {
        waitUntilClickable(by);
        getDriver().findElement(by).sendKeys(keys);
    }

    protected String waitForElementAndGetText(By by) {
        waitUntilClickable(by);
        return getDriver().findElement(by).getText();
    }

    private void waitUntilClickable(By by) {
        WebDriverWait wait = new WebDriverWait(DRIVER, TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    protected void scrollBy1000() {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("window.scrollBy(0, 1000)");
    }

    public void closeBanners() {
        waitForElementAndClick(By.id("CybotCookiebotDialogBodyButtonAccept"));
    }
}
