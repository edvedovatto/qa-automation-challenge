package steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CartPage;
import pages.CheckoutCompletePage;
import pages.CheckoutInformationPage;
import pages.CheckoutOverviewPage;
import pages.InventoryPage;
import support.BaseTest;

public class CheckoutSteps extends BaseTest {
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutInformationPage infoPage;
    private CheckoutOverviewPage overviewPage;
    private CheckoutCompletePage completePage;

    @And("the user has a product in the cart")
    public void userHasProductInCart() {
        inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart();
        cartPage = inventoryPage.goToCart();
    }

    @When("the user completes checkout with valid information")
    public void completesCheckoutSuccessfully() {
        cartPage.proceedToCheckout();
        infoPage = new CheckoutInformationPage(driver);
        infoPage.fillInformation("John", "Doe", "12345");
        overviewPage = new CheckoutOverviewPage(driver);
        overviewPage.finishCheckout();
        completePage = new CheckoutCompletePage(driver);
    }

    @When("the user tries to checkout without filling required information")
    public void checkoutWithoutRequiredInformation() {
        cartPage.proceedToCheckout();
        infoPage = new CheckoutInformationPage(driver);
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