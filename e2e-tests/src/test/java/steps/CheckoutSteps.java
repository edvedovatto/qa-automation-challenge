package steps;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import infra.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CartPage;
import pages.CheckoutCompletePage;
import pages.CheckoutInformationPage;
import pages.CheckoutOverviewPage;
import pages.InventoryPage;

public class CheckoutSteps {

    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutInformationPage infoPage;
    private CheckoutOverviewPage overviewPage;
    private CheckoutCompletePage completePage;

    @And("the user has a product in the cart")
    public void userHasProductInCart() {
        inventoryPage = new InventoryPage(DriverFactory.getDriver());
        inventoryPage.addProductToCart();
        cartPage = inventoryPage.goToCart();
    }

    @When("the user completes checkout with valid information")
    public void completesCheckoutSuccessfully() {

        cartPage.proceedToCheckout();

        infoPage = new CheckoutInformationPage(DriverFactory.getDriver());

        String firstName = "FN_" + UUID.randomUUID().toString().substring(0,5);
        String lastName = "LN_" + UUID.randomUUID().toString().substring(0,5);
        String zipCode = String.valueOf((int)(Math.random() * 90000) + 10000);

        infoPage.fillInformation(firstName, lastName, zipCode);

        overviewPage = new CheckoutOverviewPage(DriverFactory.getDriver());
        overviewPage.finishCheckout();

        completePage = new CheckoutCompletePage(DriverFactory.getDriver());
    }

    @When("the user tries to checkout without filling required information")
    public void checkoutWithoutRequiredInformation() {

        cartPage.proceedToCheckout();

        infoPage = new CheckoutInformationPage(DriverFactory.getDriver());
        infoPage.continueCheckout();
    }

    @Then("the checkout should be completed successfully")
    public void checkoutCompletedSuccessfully() {

        assertEquals(
                "Thank you for your order!",
                completePage.getSuccessMessageText()
        );
    }

    @Then("an error message should be displayed on checkout")
    public void checkoutErrorDisplayed() {

        assertTrue(infoPage.isErrorDisplayed());

        assertEquals(
                "Error: First Name is required",
                infoPage.getErrorMessageText()
        );
    }
}