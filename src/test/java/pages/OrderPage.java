package pages;

import common.Common;
import models.CustomerModel;
import models.ProductModel;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.net.EphemeralPortRangeDetector;

/**
 * @author Julia Marushkina
 */
public class OrderPage extends Common {

    private By userEmail = By.xpath("(//input[@type='email'])[2]");
    private By userEmailSubmit = By.xpath("(//input[@type='submit'])[2]");

    private By withoutDelivery = By.xpath("//input[@name='shipping_unused' and @value='2']");
    private By pickupPoint = By.xpath("//div[contains(@class, 'pickup-point-name')]");

    private By firstName = By.id("address_first_name");
    private By lastName = By.id("address_last_name");
    private By phone = By.id("address_phone_number");

    private By buttonSave = By.xpath("//div[contains(@class, 'form-controls')]/button");
    private By buttonContinue = By.xpath("//span[contains(@class, 'icon-arrow-right')]");

    private By price = By.xpath("//span[contains(@class, 'price')]");

    private CustomerModel customerModel = new CustomerModel();

    public void submitUserEmail() {
        waitForElementAndSendKeys(userEmail, customerModel.getEmail());
        waitForElementAndClick(userEmailSubmit);
    }

    public void submitOrderData(ProductModel productModel) {
        waitForElementAndClick(withoutDelivery);
        waitForElementAndClick(pickupPoint);
        waitForElementAndSendKeys(firstName, customerModel.getFirstName());
        waitForElementAndSendKeys(lastName, customerModel.getLastName());
        waitForElementAndSendKeys(phone, customerModel.getPhone());
        waitForElementAndClick(buttonSave);
        waitForElementAndClick(buttonContinue);

        validateFinalPrice(productModel);
    }

    private void validateFinalPrice(ProductModel productModel) {
        String finalPrice = waitForElementAndGetText(price);
        Assert.assertEquals("Order price is not equal to product price", finalPrice, productModel.getPrice());
    }
}
