package lv.javaguru.fhw.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
        waitAndScroll(by);
        getDriver().findElement(by).click();
    }

    protected void waitForElementAndSendKeys(By by, String keys) {
        waitAndScroll(by);
        getDriver().findElement(by).sendKeys(keys);
    }

    protected String waitForElementAndGetText(By by) {
        waitAndScroll(by);
        return getDriver().findElement(by).getText();
    }

    protected void waitAndScroll(By by) {
        waitUntilClickable(by);
        scrollElementToCenter(by);
    }

    protected void waitUntilClickable(By by) {
        WebDriverWait wait = new WebDriverWait(DRIVER, TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    private void scrollElementToCenter(By by) {
        WebElement element = getDriver().findElement(by);
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) getDriver()).executeScript(scrollElementIntoMiddle, element);

        new Actions(getDriver()).pause(Duration.ofSeconds(2)).perform();
    }

}
