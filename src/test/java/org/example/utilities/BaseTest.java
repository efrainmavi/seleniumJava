package org.example.utilities;

import org.example.listeners.SuiteListeners;
import org.example.listeners.TestListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

@Listeners({TestListeners.class, SuiteListeners.class})
public class BaseTest {
    protected SoftAssert softAssert;
    protected WebDriver driver;
    protected final String smoke = "smoke";
    protected final String regression = "regression";

    @BeforeMethod(alwaysRun = true)
    public void masterSetUp() {
        softAssert = new SoftAssert();

        Logs.debug("Inicializando Driver");
        driver = new ChromeDriver();

        Logs.debug("Maximizando Ventana");
        driver.manage().window().maximize();

        Logs.debug("Eliminando Cookies");
        driver.manage().deleteAllCookies();

        Logs.debug("Inicializando el driver con webdriver provider");
        new WebdriverProvider().set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void masterTearDown() {
        Logs.debug("Eliminando la session del Driver");

        driver.quit();
    }
}
