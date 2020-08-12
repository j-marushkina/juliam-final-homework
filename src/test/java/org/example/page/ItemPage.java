package org.example.page;

import org.example.common.Common;
import org.example.model.ProductModel;
import org.openqa.selenium.By;

/**
 * @author Julia Marushkina
 */
public class ItemPage extends Common {

    private By productName = By.xpath("//div/h1");

    private By productPrice = By.xpath("//span[contains(@class, 'price')]");

    private By addToCart = By.id("add_to_cart_btn");

    private By goToCart = By.xpath("//*[@id=\"add-to-cart\"]/div[3]/a[2]");

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
