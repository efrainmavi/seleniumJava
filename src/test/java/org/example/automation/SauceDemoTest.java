package org.example.automation;

import org.example.utilities.BaseTest;
import org.example.utilities.Logs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

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
    public void testDetallesProducto() {
        llenarFormulario("standard_user","secret_sauce");

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

    @Test
    public void testSortItems(){
        llenarFormulario("standard_user","secret_sauce");

        sleep(3000);

        Logs.info("Verificando la pagina principal");
        driver.findElement(By.cssSelector("[data-test='title']"));

        WebElement selectSort = driver.findElement(By.cssSelector("[data-test='product-sort-container']"));

        //Cast Select Element
        Select select = new Select(selectSort);

        Logs.info("Chose items to sort from Z -> A");
        select.selectByValue("za");

        List<WebElement> itemsName = driver.findElements(By.cssSelector("data-test='inventory-item'"));

        String firstElementName = itemsName.get(0).getText();
        String lastElementName = itemsName.get(itemsName.size() -1).getText();

        softAssert.assertEquals(firstElementName, "Test.allTheThings() T-Shirt (Red)");
        softAssert.assertEquals(lastElementName, "Sauce Labs Backpack");
        softAssert.assertAll();

        //Test.allTheThings() T-Shirt (Red)
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
