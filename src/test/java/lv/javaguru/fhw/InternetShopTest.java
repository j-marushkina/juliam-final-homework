package lv.javaguru.fhw;

import io.github.bonigarcia.wdm.WebDriverManager;
import lv.javaguru.fhw.model.ProductModel;
import lv.javaguru.fhw.page.CartPage;
import lv.javaguru.fhw.page.HomePage;
import lv.javaguru.fhw.page.ItemPage;
import lv.javaguru.fhw.page.OrderPage;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Julia Marushkina
 */
public class InternetShopTest {

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

    private ProductModel productModel = new ProductModel();

    private HomePage homePage = new HomePage();

    private ItemPage itemPage = new ItemPage(productModel);

    private CartPage cartPage = new CartPage(productModel);

    private OrderPage orderPage = new OrderPage(productModel);

    @Test
    public void testInternetShop() {
        homePage.startBrowser(TARGET_URL);
        homePage.process();

        itemPage.process();
        cartPage.process();
        orderPage.process();

        homePage.stopBrowser();
    }

}
