package org.example.utilities;

import org.example.listeners.SuiteListeners;
import org.example.listeners.TestListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

@Listeners({TestListeners.class, SuiteListeners.class})
public class BaseTest {
    protected SoftAssert softAssert;
    protected WebDriver driver;
    protected ChromeOptions options;
    protected final String smoke = "smoke";
    protected final String regression = "regression";

    @BeforeMethod(alwaysRun = true)
    public void masterSetUp() {
        softAssert = new SoftAssert();

        Logs.debug("Inicializando Driver");
        options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-save-password-bubble");
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false
        ));
        options.addArguments("/Users/efrain/Desktop/test-data=/selenium_clean_profile");

        driver = new ChromeDriver(options);


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

    protected void sleep(int time){
        try{
            Thread.sleep(time);
        }catch (InterruptedException ex){
            Logs.error("Timeout Exception, Wait time exceded: %s", ex.getLocalizedMessage());
        }
    }
}
