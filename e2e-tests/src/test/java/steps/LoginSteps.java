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

    @Given("que estou na página de login")
    public void openLoginPage() {
        loginPage = new LoginPage(driver, wait);
        inventoryPage = new InventoryPage(driver);
        loginPage.open();
    }

    @When("faço login com usuário válido")
    public void loginWithValidUser() {
        loginPage.login("standard_user", "secret_sauce");
    }

    @When("faço login com usuário inválido")
    public void loginWithInvalidUser() {
        loginPage.login("invalid_user", "invalid_pass");
    }

    @Then("devo acessar a página de inventário")
    public void shouldAccessInventoryPage() {
        assertTrue(inventoryPage.isLoaded());
    }

    @Then("devo ver uma mensagem de erro")
    public void shouldSeeErrorMessage() {
        assertTrue(loginPage.isErrorVisible());
    }
}