package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import pages.InventoryPage;
import pages.LoginPage;
import support.BaseTest;

public class LoginE2ETest extends BaseTest {

    @Test
    void shouldLoginSuccessfully() {
        LoginPage loginPage = new LoginPage(driver, wait);
        InventoryPage inventoryPage = new InventoryPage(driver);

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(inventoryPage.isLoaded());
    }

    @Test
    void shouldShowErrorForInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.open();
        loginPage.login("invalid_user", "invalid_pass");

        assertTrue(loginPage.isErrorVisible());
    }
}