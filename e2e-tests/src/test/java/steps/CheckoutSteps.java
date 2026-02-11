package steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import config.DriverFactory;
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
        infoPage.fillInformation("John", "Doe", "12345");

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
        assertTrue(completePage.isOrderCompleted());
    }

    @Then("an error message should be displayed on checkout")
    public void checkoutErrorDisplayed() {
        assertTrue(infoPage.isErrorDisplayed());
    }
}