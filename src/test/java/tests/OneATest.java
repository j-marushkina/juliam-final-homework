package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ProductPage;

/**
 * @author Julia Marushkina
 */
public class OneATest {

    private static final String TARGET_URL = "https://www.1a.lv/";

    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testInternetShopOneA() throws InterruptedException {
        selectProduct();
    }

    private void selectProduct() throws InterruptedException {
        ProductPage productPage = new ProductPage();
        productPage.startBrowser(TARGET_URL);
        productPage.searchProduct();
        productPage.searchBrandAndTopStarsProduct();
        Thread.sleep(8000l);
        productPage.stopBrowser();
    }
}
