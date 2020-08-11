package pages;

import common.Common;
import models.ProductModel;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * @author Julia Marushkina
 */
public class ProductPage extends Common {

    private By searchTextBox = By.id("q");

    private By searchButton = By.className("main-search-submit");

    private By searchTextBoxBrand = By.className("filter-search__input");

    private By searchButtonBrand = By.xpath("//span[contains(text(), 'Lenovo')]");
            //" and @class='filter-link__label']");

    private By searchButtonTopProduct = By.xpath("//span[contains(text(), 'TOP prece') and @class='filter-link__label']");

    private By firstProduct = By.xpath("//a[[contains(@class, 'new-product-image')]][0]");

    private By customerName = By.id("address_first_name");

    private By customerSurname = By.id("address_last_name");

    private By customerPhoneNumber = By.id("address_phone_number");

    private ProductModel productModel = new ProductModel();

    public void searchProduct() {
        WebElement searchTextBoxElement = getDriver().findElement(searchTextBox);
        searchTextBoxElement.click();
        searchTextBoxElement.sendKeys(productModel.getCategoryName());
        getDriver().findElement(searchButton).click();

        By laptopImage = By.className("itemImage_frftyoh");
        waitUntilClickable(laptopImage);
        getDriver().findElement(laptopImage).click();
    }

    public void searchBrandAndTopStarsProduct() {
        By side = By.className("site-content-side");
        waitUntilClickable(side);
        WebElement sideElement = getDriver().findElement(side);

        waitUntilClickable(searchTextBoxBrand);
        WebElement searchTextBoxElementBrand = sideElement.findElement(searchTextBoxBrand);
        searchTextBoxElementBrand.sendKeys(productModel.getBrand());

        waitUntilClickable(searchButtonTopProduct);
        sideElement.findElement(searchButtonTopProduct).click();
        sideElement.findElement(searchButtonBrand).click();


        waitUntilClickable(firstProduct);
    }

    public void closeBanners() {
        By sliderBy = By.xpath("//div[contains(@class, 'close-button-slider')]");
        waitUntilDisplayed(sliderBy);
        WebElement slider = getDriver().findElement(sliderBy);
        moveAndClick(slider);
        WebElement policy = getDriver().findElement(By.id("CybotCookiebotDialogBodyButtonAccept"));
        policy.click();
    }

    public void fillInCustomerData(String customerName, String customerSurname, String customerPhoneNumber) {
        DRIVER.findElement(customerName).sendKeys();

    }
}
