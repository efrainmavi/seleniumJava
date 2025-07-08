package org.example.automation;

import net.datafaker.Faker;
import org.example.utilities.BaseTest;
import org.example.utilities.Logs;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoQATests extends BaseTest {

    @Test
    public void testShiftKeyboard(){
        Logs.info("Navegando a la pagina");
        driver.get("https://demoqa.com/text-box");

        Faker faker = new Faker();
        String fullName = faker.name().fullName();

        Logs.info("Full Name: %s ", fullName);
        WebElement userNameInput = driver.findElement(By.id("userName"));
        userNameInput.click();

        Logs.info("Aplicando acciones al teclado SHIFT y Escribiendo en mayusculas");
        new Actions(driver).keyDown(Keys.SHIFT).sendKeys(fullName).keyUp(Keys.SHIFT).perform();

        Logs.info("Verificando que el input este en mayúsculas");
        Assert.assertEquals(userNameInput.getAttribute("value"), fullName.toUpperCase());
    }

    @Test
    public void testCopyPasteKeyboard(){
        Logs.info("Navegando a la pagina");
        driver.get("https://demoqa.com/text-box");

        Faker faker = new Faker();
        String currentAddress = faker.address().fullAddress();

        Logs.info("current address: %s ", currentAddress);
        WebElement currentAddressInput = driver.findElement(By.id("currentAddress"));
        WebElement permanentAddressInput = driver.findElement(By.id("permanentAddress"));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
        currentAddressInput.sendKeys(currentAddress);
        currentAddressInput.click();

        String osSystem = System.getProperty("os.name").toLowerCase();
        if (osSystem.contains("win")){
            Logs.info("Seleccionando y Copiando el contenido");
            new Actions(driver)
                    .keyDown(Keys.CONTROL)
                    .sendKeys("a")
                    .sendKeys("c")
                    .keyUp(Keys.CONTROL)
                    .perform();

            Logs.info("Seleccionando y pegando el contenido");
            permanentAddressInput.click();

            new Actions(driver)
                    .keyDown(Keys.CONTROL)
                    .sendKeys("v")
                    .keyUp(Keys.CONTROL)
                    .perform();

        }else {
            Logs.info("Seleccionando y Copiando el contenido");
            new Actions(driver)
                    .keyDown(Keys.COMMAND)
                    .sendKeys("a")
                    .sendKeys("c")
                    .keyUp(Keys.COMMAND)
                    .perform();

            Logs.info("Seleccionando y pegando el contenido");
            permanentAddressInput.isEnabled();

            permanentAddressInput.click();

            new Actions(driver)
                    .keyDown(Keys.COMMAND)
                    .sendKeys("v")
                    .keyUp(Keys.COMMAND)
                    .perform();
        }
        Logs.info("Verificando que el input este en mayúsculas");
        Assert.assertEquals(currentAddressInput.getAttribute("value"), permanentAddressInput.getAttribute("value"));
    }
}
