package org.example.automation;

import org.example.utilities.BaseTest;
import org.example.utilities.Logs;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

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

        //TODO: arreglar la falla de la alerta
        Logs.info("Manejando La alerta");
       // Alert alert = driver.switchTo().alert();

        //alert.accept();

        Logs.info("Verificando la pagina principal");
        driver.findElement(By.cssSelector("[data-test='title']"));
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
