package lv.javaguru.fhw.page;

import lv.javaguru.fhw.common.Common;
import lv.javaguru.fhw.model.ProductModel;
import org.openqa.selenium.By;

/**
 * @author Julia Marushkina
 */
public class ItemPage extends Common {

    private By productName = By.xpath("//div[@class=\"product-righter google-rich-snippet\"]/h1");

    private By productPrice = By.xpath("//span[@class=\"price\"]");

    private By addToCart = By.id("add_to_cart_btn");

    private By goToCart = By.xpath("//div[@id=\"add-to-cart\"]//a[@class=\"main-button\"]");

    public void getItemNameAndPrice(ProductModel productModel) {
        String name = waitForElementAndGetText(productName);
        productModel.setName(name);

        String price = waitForElementAndGetText(productPrice);
        productModel.setPrice(removeQuantityInfo(price));
    }

    public void addAndGoToCart() {
        waitForElementAndClick(addToCart);
        waitForElementAndClick(goToCart);
    }

    private String removeQuantityInfo(String price) {
        return price.substring(0, price.indexOf("/")).trim();
    }
}
