package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import models.ProductModel;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CartPage;
import pages.ItemPage;
import pages.OrderPage;
import pages.ProductPage;

/**
 * @author Julia Marushkina
 */
public class OneATest {

    private static final String TARGET_URL = "https://www.1a.lv/";
//    private static final String TARGET_URL = "https://www.1a.lv/p/lenovo-ideapad-3-17-amd-platinum-gray-81w20017pb-pl/8v7u?mtd=search&pos=regular&src=searchnode";


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
    public void testInternetShopOneA() {
        ProductPage productPage = new ProductPage();
        productPage.startBrowser(TARGET_URL);

        processHomePage(productPage);

        processItemPage();

        processCartPage();

        processOrderPage();

        productPage.stopBrowser();
    }

    private void processHomePage(ProductPage productPage) {
        productPage.searchProduct();
        productPage.searchBrandAndTopStarsProduct();
    }

    private void processItemPage() {
        ItemPage itemPage = new ItemPage();
        itemPage.getItemNameAndPrice(productModel);
        itemPage.addAndGoToCart();
    }

    private void processCartPage() {
        CartPage cartPage = new CartPage();
        cartPage.assertNameAndPrice(productModel);
    }

    private void processOrderPage() {
        OrderPage orderPage = new OrderPage();
        orderPage.submitUserEmail();
        orderPage.submitOrderData(productModel);
    }
}
