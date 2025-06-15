package org.example.automation;

import org.example.utilities.BaseTest;
import org.example.utilities.Logs;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class SauceDemoTest extends BaseTest {

    @Test
    public void testUsuarioInvalido() {

        llenarFormulario("locked_out_user", "secret_sauce");

        Logs.info("Get an Error Message");
        WebElement errorMsg = driver.findElement(By.cssSelector("[data-test='error']"));

        softAssert.assertTrue(errorMsg.isDisplayed());
        softAssert.assertEquals(errorMsg.getText(), "Epic sadface: Sorry, this user has been locked out.");
        softAssert.assertAll();
    }

    @Test
    public void testUsuarioValido(){
        llenarFormulario("standard_user","secret_sauce");

        sleep(3000);

        Logs.info("Verificando la pagina principal");
        driver.findElement(By.cssSelector("[data-test='title']"));
    }

    @Test
    public void testDetallesProducto() throws AWTException {
        llenarFormulario("standard_user","secret_sauce");

        //TODO: Handle password alert
        Logs.info("Manejando La alerta");
        //Alert alert = driver.switchTo().alert();

        /*
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        */

        //alert.accept();
        Logs.info("Verificando la pagina principal");
        driver.findElement(By.cssSelector("[data-test='title']"));

        List<WebElement> product = driver.findElements(By.cssSelector("img[class='inventory_item_img']"));
        product.get(0).click();

        sleep(1000);

        softAssert.assertTrue(driver.findElement(By.cssSelector("[data-test='inventory-item-name']")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.cssSelector("[data-test='inventory-item-desc']")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.cssSelector("[data-test='inventory-item-price']")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.cssSelector("[data-test='item-sauce-labs-backpack-img']")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.cssSelector("[data-test='add-to-cart']")).isDisplayed());
        softAssert.assertAll();
    }

    private void llenarFormulario(String userName, String password){
        Logs.info("Navegate to Sauce Labs page");
        driver.get("https://www.saucedemo.com/");

        sleep(3000);

        Logs.info("Filling Login Form");
        driver.findElement(By.cssSelector("[data-test='username']")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(password);

        Logs.info("Click on Login Button");

        driver.findElement(By.id("login-button")).click();

        sleep(2000);
    }
}
