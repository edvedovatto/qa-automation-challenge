package steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import infra.DriverFactory;
import infra.EnvironmentConfig;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginSteps {

    private LoginPage loginPage;
    private String actualMessage;

    @Given("the user is on the login page")
    public void userIsOnLoginPage() {
        loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.open(EnvironmentConfig.getBaseUrl());
    }

    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
        loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.open(EnvironmentConfig.getBaseUrl());
        loginPage.login("standard_user", "secret_sauce");
    }

    @When("the user logs in with {string} and {string}")
    public void loginWithCredentials(String username, String password) {
        loginPage.login(username, password);

        if (loginPage.isOnInventoryPage()) {
            actualMessage = loginPage.getSuccessMessage();
        } else {
            actualMessage = loginPage.getErrorMessage();
        }
    }

    @Then("the login result message should be {string}")
    public void loginResultShouldBe(String expectedMessage) {
        assertEquals(expectedMessage, actualMessage);
    }
}