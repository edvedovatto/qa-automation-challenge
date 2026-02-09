package steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.InventoryPage;
import pages.LoginPage;
import support.BaseTest;

public class LoginSteps extends BaseTest {
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @Given("the user is on the login page")
    public void userIsOnLoginPage() {
        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
        userIsOnLoginPage();
        inventoryPage = loginPage.login("standard_user", "secret_sauce");
    }

    @When("the user logs in with valid credentials")
    public void loginWithValidCredentials() {
        inventoryPage = loginPage.login("standard_user", "secret_sauce");
    }

    @When("the user logs in with invalid credentials")
    public void loginWithInvalidCredentials() {
        loginPage.login("locked_out_user", "wrong_password");
    }

    @Then("the products page should be displayed")
    public void productsPageDisplayed() {
        assertTrue(inventoryPage.isLoaded());
    }

    @Then("an error message should be displayed")
    public void errorMessageDisplayed() {
        assertTrue(loginPage.isErrorDisplayed());
    }
}