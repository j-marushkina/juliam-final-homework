package lv.javaguru.fhw;

import io.github.bonigarcia.wdm.WebDriverManager;
import lv.javaguru.fhw.page.CartPage;
import lv.javaguru.fhw.model.ProductModel;
import lv.javaguru.fhw.page.ItemPage;
import lv.javaguru.fhw.page.OrderPage;
import lv.javaguru.fhw.page.HomePage;
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

    @Test
    public void testInternetShop() {
        HomePage homePage = new HomePage();
        homePage.startBrowser(TARGET_URL);
        homePage.searchProduct();
        homePage.searchBrandAndTopStarsProduct();

        processItemPage();
        processCartPage();
        processOrderPage();

        homePage.stopBrowser();
    }

    private void processItemPage() {
        ItemPage itemPage = new ItemPage();
        itemPage.getItemNameAndPrice(productModel);
        itemPage.addAndGoToCart();
    }

    private void processCartPage() {
        CartPage cartPage = new CartPage();
        cartPage.assertNameAndPrice(productModel);
        cartPage.continueCheckout();
    }

    private void processOrderPage() {
        OrderPage orderPage = new OrderPage();
        orderPage.submitUserEmail();
        orderPage.submitOrderData();
        orderPage.validateFinalPrice(productModel);
    }
}
