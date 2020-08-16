package lv.javaguru.fhw.page;

import lv.javaguru.fhw.model.ProductModel;
import org.junit.Assert;
import org.openqa.selenium.By;

/**
 * @author Julia Marushkina
 */
public class CartPage extends AbstractPage implements Page {

    private By productNameInCart = By.xpath("//a[@class=\"detailed-cart-item__name-link\"]");

    private By productPriceInCart = By.id("cart-full-total-price");

    private By continueButton = By.xpath("//div[@class=\"cart__proceed-to-checkout\"]//input[@type=\"submit\"]");

    private ProductModel productModel;

    public CartPage(ProductModel productModel) {
        this.productModel = productModel;
    }

    @Override
    public void process() {
        assertNameAndPrice(productModel);
        continueCheckout();
    }

    private void assertNameAndPrice(ProductModel productModel) {
        String nameInCart = waitForElementAndGetText(productNameInCart);
        String priceInCart = waitForElementAndGetText(productPriceInCart);

        Assert.assertEquals("Name in cart is not equal to selected product name", nameInCart, productModel.getName());
        Assert.assertEquals("Price in cart is not equal to product price", priceInCart, productModel.getPrice());
    }

    private void continueCheckout() {
        waitForElementAndClick(continueButton);
    }
}
