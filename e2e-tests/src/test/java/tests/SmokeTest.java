package tests;

import org.junit.jupiter.api.Test;

import support.BaseTest;

public class SmokeTest extends BaseTest {

    @Test
    void shouldOpenBrowser() {
        driver.get("https://www.saucedemo.com/");
    }
}