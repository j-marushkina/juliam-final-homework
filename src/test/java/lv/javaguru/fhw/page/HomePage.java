package lv.javaguru.fhw.page;

import lv.javaguru.fhw.common.Common;
import lv.javaguru.fhw.model.ProductModel;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

/**
 * @author Julia Marushkina
 */
public class HomePage extends AbstractPage implements Page {

    private By searchTextBox = By.id("q");
    private By searchButton = By.className("main-search-submit");
    private By laptopImage = By.className("itemImage_frftyoh");
    private By searchTextBoxBrand = By.className("filter-search__input");
    private By secondSelectedProduct = By.xpath("(//a[@class=\"new-product-name\"])[2]");

    private ProductModel productModel = new ProductModel();

    @Override
    public void process() {
        searchProduct();
        searchBrandAndTopStarsProduct();
    }

    private void searchProduct() {
        waitForElementAndSendKeys(searchTextBox, productModel.getCategoryName());
        getDriver().findElement(searchButton).click();
        waitForElementAndClick(laptopImage);
    }

    private void searchBrandAndTopStarsProduct() {
        waitForElementAndSendKeys(searchTextBoxBrand, productModel.getBrand());

        String brandXpath = String.format("//strong[text()='%s']", productModel.getBrand());
        By searchButtonBrand = By.xpath(brandXpath);
        waitForElementAndClick(searchButtonBrand);

        String topXpath = String.format("//span[text()='%s']", productModel.getMostStarsFilterName());
        By searchButtonTopProduct = By.xpath(topXpath);
        waitForElementAndClick(searchButtonTopProduct);

        waitAndScroll(secondSelectedProduct);
        Actions builder = new Actions(getDriver());
        builder.moveToElement(getDriver().findElement(secondSelectedProduct), 0, 50).click().build().perform();
    }

}
