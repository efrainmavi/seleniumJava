package org.example.automation;

import net.datafaker.Faker;
import org.example.utilities.BaseTest;
import org.example.utilities.Logs;
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

        Logs.info("hacer click en el text box y luego presionar la tecla  SHIFT y Escribiendo en mayusculas");
        new Actions(driver)
                .click(userNameInput)
                .keyDown(Keys.SHIFT)
                .sendKeys(fullName)
                .keyUp(Keys.SHIFT)
                .perform();

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

        //hacer scroll en la pagina para evitar anuncios
        //((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
        new Actions(driver)
                .scrollByAmount(0,500)
                .perform();

        String osSystem = System.getProperty("os.name").toLowerCase();
        if (osSystem.contains("win")){

            Logs.info("hacer click sobre el input, escribir el address y  selecciono y copio el contenido");
            new Actions(driver)
                    .click(currentAddressInput)
                    .sendKeys(currentAddress)
                    .keyDown(Keys.CONTROL)
                    .sendKeys("a")
                    .sendKeys("c")
                    .keyUp(Keys.CONTROL)
                    .perform();

            //Pegando la direccion
            new Actions(driver)
                    .click(permanentAddressInput)
                    .keyDown(Keys.CONTROL)
                    .sendKeys("v")
                    .keyUp(Keys.CONTROL)
                    .perform();

        } else {
            Logs.info("Seleccionando y Copiando el contenido");
            new Actions(driver)
                    .click(currentAddressInput)
                    .sendKeys(currentAddress)
                    .keyDown(Keys.COMMAND)
                    .sendKeys("a")
                    .sendKeys("c")
                    .keyUp(Keys.COMMAND)
                    .perform();

            Logs.info("Seleccionando y pegando el contenido");
            permanentAddressInput.isEnabled();

            new Actions(driver)
                    .click(permanentAddressInput)
                    .keyDown(Keys.COMMAND)
                    .sendKeys("v")
                    .keyUp(Keys.COMMAND)
                    .perform();
        }
        Logs.info("Verificando que el input este en mayúsculas");
        Assert.assertEquals(currentAddressInput.getAttribute("value"), permanentAddressInput.getAttribute("value"));
    }

    @Test
    public void testMouseAction(){
        Logs.info("Navegando a la pagina");
        driver.get("https://demoqa.com/droppable");

        WebElement figuraOrigen = driver.findElement(By.id("draggable"));
        WebElement figuraDestino = driver.findElement(By.id("droppable"));

        new Actions(driver)
                .dragAndDrop(figuraOrigen, figuraDestino)
                .perform();

        new Actions(driver)
                .scrollByAmount(0,300)
                .perform();
        Logs.info("Verificando que el label sea visible");
        //((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300);");
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Dropped!']")).isDisplayed());
    }

    @Test
    public void testTooltip() {
        Logs.info("Navegando a la pagina");
        driver.get("https://demoqa.com/tool-tips");

        WebElement btnVerde = driver.findElement(By.id("toolTipButton"));

        Logs.info("Moviendo mouse al boton verde");
        new Actions(driver)
                .moveToElement(btnVerde)
                .pause(2000)
                .perform();

        Logs.info("Verificando el tooltip");
        Assert.assertEquals(btnVerde.getAttribute("aria-describedby"), "buttonToolTip");

    }
}
