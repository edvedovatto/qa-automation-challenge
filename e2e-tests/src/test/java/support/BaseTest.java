package support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseTest {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    protected static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    protected static void setWait(WebDriverWait webDriverWait) {
        wait = webDriverWait;
    }
}