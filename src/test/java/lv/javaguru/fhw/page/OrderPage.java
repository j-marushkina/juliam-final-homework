package lv.javaguru.fhw.page;

import lv.javaguru.fhw.model.CustomerModel;
import lv.javaguru.fhw.model.ProductModel;
import org.junit.Assert;
import org.openqa.selenium.By;

/**
 * @author Julia Marushkina
 */
public class OrderPage extends AbstractPage implements Page {

    private By userEmail = By.xpath("//form[@id=\"new_user_guest\"]//input[@id=\"user_email\"]");
    private By userEmailSubmit = By.xpath("//form[@id=\"new_user_guest\"]//input[@type=\"submit\"]");

    private By withoutDelivery = By.xpath("//input[@name=\"shipping_unused\" and @value=\"2\"]");
    private By pickupPoint = By.xpath("//div[@class=\"pickup-point-name\"]");

    private By firstName = By.id("address_first_name");
    private By lastName = By.id("address_last_name");
    private By phone = By.id("address_phone_number");

    private By buttonSave = By.xpath("//div[@class=\"form-controls\"]/button");
    private By buttonContinue = By.xpath("//span[@class=\"icon-arrow-right\"]");

    private By price = By.xpath("//span[@class=\"price\"]");

    private CustomerModel customerModel = new CustomerModel();

    private ProductModel productModel;

    public OrderPage(ProductModel productModel) {
        this.productModel = productModel;
    }

    @Override
    public void process() {
        submitUserEmail();
        submitOrderData();
        validateFinalPrice(productModel);
    }

    private void submitUserEmail() {
        waitForElementAndSendKeys(userEmail, customerModel.getEmail());
        waitForElementAndClick(userEmailSubmit);
    }

    private void submitOrderData() {
        waitForElementAndClick(withoutDelivery);
        waitForElementAndClick(pickupPoint);
        waitForElementAndSendKeys(firstName, customerModel.getFirstName());
        waitForElementAndSendKeys(lastName, customerModel.getLastName());
        waitForElementAndSendKeys(phone, customerModel.getPhone());
        waitForElementAndClick(buttonSave);
        waitForElementAndClick(buttonContinue);
    }

    private void validateFinalPrice(ProductModel productModel) {
        String finalPrice = waitForElementAndGetText(price);
        Assert.assertEquals("Order price is not equal to product price", finalPrice, productModel.getPrice());
    }
}
