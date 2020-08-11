package pages;

import common.Common;
import models.ProductModel;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * @author Julia Marushkina
 */
public class ProductPage extends Common {

    private By searchTextBox = By.id("q");
    private By searchButton = By.className("main-search-submit");

    private By laptopImage = By.className("itemImage_frftyoh");

    private By searchTextBoxBrand = By.className("filter-search__input");

    private By searchButtonBrand = By.xpath("//span[contains(text(), 'Lenovo')]");

    private By searchButtonTopProduct = By.xpath("//span[contains(text(), 'TOP prece')]");

    private By firstProduct = By.xpath("(//a[contains(@class, 'new-product-image')])[0]");

    private ProductModel productModel = new ProductModel();

    public void searchProduct() {
        waitForElementAndSendKeys(searchTextBox, productModel.getCategoryName());
        getDriver().findElement(searchButton).click();
        waitForElementAndClick(laptopImage);
    }

    public void searchBrandAndTopStarsProduct() {
        waitForElementAndSendKeys(searchTextBoxBrand, productModel.getBrand());
        scrollBy1000();
        waitForElementAndClick(searchButtonBrand);
        waitForElementAndClick(searchButtonTopProduct);
        waitForElementAndClick(firstProduct);
    }

}
