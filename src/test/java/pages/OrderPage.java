package pages;

import common.Common;
import models.CustomerModel;
import org.openqa.selenium.By;

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

    private CustomerModel customerModel = new CustomerModel();

    public void submitUserEmail() {
        waitForElementAndSendKeys(userEmail, customerModel.getEmail());
        waitForElementAndClick(userEmailSubmit);
    }

    public void submitOrderData() {
        waitForElementAndClick(withoutDelivery);
        waitForElementAndClick(pickupPoint);
        waitForElementAndSendKeys(firstName, customerModel.getFirstName());
        waitForElementAndSendKeys(lastName, customerModel.getLastName());
        waitForElementAndSendKeys(phone, customerModel.getPhone());
    }
}
