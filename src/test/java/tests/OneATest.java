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

    //    private static final String TARGET_URL = "https://www.1a.lv/";
    private static final String TARGET_URL = "https://www.1a.lv/p/lenovo-ideapad-3-17-amd-platinum-gray-81w20017pb-pl/8v7u?mtd=search&pos=regular&src=searchnode";


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
    public void testInternetShopOneA() {
        selectProduct();
    }

    private void selectProduct() {
        ProductModel productModel = new ProductModel();
        ProductPage productPage = new ProductPage();
        productPage.startBrowser(TARGET_URL);
//        productPage.closeBanners();
//        productPage.searchProduct();
//        productPage.searchBrandAndTopStarsProduct();

        ItemPage itemPage = new ItemPage();

        itemPage.getItemNameAndPrice(productModel);
        productPage.closeBanners();
        itemPage.addAndGoToCart();

        CartPage cartPage = new CartPage();
        cartPage.assertNameAndPrice(productModel);

        OrderPage orderPage = new OrderPage();
        orderPage.submitUserEmail();
        orderPage.submitOrderData();

        productPage.stopBrowser();
    }
}
