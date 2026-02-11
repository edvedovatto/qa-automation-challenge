package steps;

import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import support.BaseTest;
import support.Config;

public class Hooks extends BaseTest {

    @Before
    public void setUp() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        if (Config.isHeadless()) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--incognito");
        options.addArguments("--disable-blink-features=AutomationControlled");

        options.setExperimentalOption("excludeSwitches",
                Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        ChromeDriver chromeDriver = new ChromeDriver(options);
        chromeDriver.manage().window().maximize();

        setDriver(chromeDriver);
        setWait(new WebDriverWait(chromeDriver, Duration.ofSeconds(10)));
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed() && driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "failure-screenshot");
        }

        if (driver != null) {
            driver.quit();
        }
    }
}