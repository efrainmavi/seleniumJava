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

import java.time.Duration;
import java.util.Map;

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
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");
        options.setExperimentalOption("prefs", Map.of("credentials_enable_service", false,
                "profile.password_manager_enabled", false));
        options.addArguments("--disable-sync");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");

        //TODO Uncomment this block if it's necessary for get a clean profile for chrome webdriver - Refers to README.md file
        /*String osPlatform = System.getProperty("os.name").toLowerCase();
        if (osPlatform.contains("win")){
            options.addArguments("--user-data-dir=C:/temp/chrome-profile-test");
        }else {
            options.addArguments("--user-data-dir=/tmp/chrome-profile-test");
        }*/

        driver = new ChromeDriver(options);

        Logs.debug("Maximizando Ventana");
        driver.manage().window().maximize();

        Logs.debug("Eliminando Cookies");
        driver.manage().deleteAllCookies();

        Logs.debug("Iniciando Implicit wait de 5 segundos");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

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
