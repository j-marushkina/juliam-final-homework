package org.example.page;

import org.example.common.Common;
import org.example.model.ProductModel;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

/**
 * @author Julia Marushkina
 */
public class HomePage extends Common {

    private By searchTextBox = By.id("q");
    private By searchButton = By.className("main-search-submit");
    private By laptopImage = By.className("itemImage_frftyoh");
    private By searchTextBoxBrand = By.className("filter-search__input");
    private By selectedProduct = By.xpath("(//a[contains(@class, 'new-product-name')])[2]");
    private ProductModel productModel = new ProductModel();

    public void searchProduct() {
        waitForElementAndSendKeys(searchTextBox, productModel.getCategoryName());
        getDriver().findElement(searchButton).click();
        waitForElementAndClick(laptopImage);
    }

    public void searchBrandAndTopStarsProduct() {
        waitForElementAndSendKeys(searchTextBoxBrand, productModel.getBrand());

        String brandXpath = String.format("//strong[contains(text(), '%s')]", productModel.getBrand());
        By searchButtonBrand = By.xpath(brandXpath);
        waitForElementAndClick(searchButtonBrand);

        String topXpath = String.format("//span[contains(text(), '%s')]", productModel.getMostStarsFilterName());
        By searchButtonTopProduct = By.xpath(topXpath);
        waitForElementAndClick(searchButtonTopProduct);

        waitAndScroll(selectedProduct);
        Actions builder = new Actions(getDriver());
        builder.moveToElement(getDriver().findElement(selectedProduct), 0, 50).click().build().perform();
    }

}
