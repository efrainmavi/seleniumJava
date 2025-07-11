package org.example.automation;

import org.example.utilities.BaseTest;
import org.example.utilities.Logs;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class SauceDemoTest extends BaseTest {

    @Test(groups = {regression, smoke})
    public void testUsuarioInvalido() {

        llenarFormulario("locked_out_user", "secret_sauce");

        Logs.info("Get an Error Message");
        WebElement errorMsg = driver.findElement(By.cssSelector("[data-test='error']"));

        softAssert.assertTrue(errorMsg.isDisplayed());
        softAssert.assertEquals(errorMsg.getText(), "Epic sadface: Sorry, this user has been locked out.");
        softAssert.assertAll();
    }

    @Test(groups = {regression, smoke})
    public void testUsuarioValido(){
        llenarFormulario("standard_user","secret_sauce");

        //sleep(3000);

        Logs.info("Verificando la pagina principal");
        driver.findElement(By.cssSelector("[data-test='title']"));
    }

    @Test(groups = {regression})
    public void testDetallesProducto() {
        llenarFormulario("standard_user","secret_sauce");

        Logs.info("Verificando la pagina principal");
        driver.findElement(By.cssSelector("[data-test='title']"));

        List<WebElement> product = driver.findElements(By.cssSelector("img[class='inventory_item_img']"));
        product.get(0).click();

        //sleep(1000);

        softAssert.assertTrue(driver.findElement(By.cssSelector("[data-test='inventory-item-name']")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.cssSelector("[data-test='inventory-item-desc']")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.cssSelector("[data-test='inventory-item-price']")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.cssSelector("[data-test='item-sauce-labs-backpack-img']")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.cssSelector("[data-test='add-to-cart']")).isDisplayed());
        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void testSortItemsByLetter(){
        llenarFormulario("standard_user","secret_sauce");

        //sleep(3000);

        Logs.info("Verificando la pagina principal");
        driver.findElement(By.cssSelector("[data-test='title']"));

        WebElement selectSort = driver.findElement(By.cssSelector("[data-test='product-sort-container']"));

        //Cast Select Element
        Select select = new Select(selectSort);

        Logs.info("Chose items to sort from Z -> A");
        select.selectByValue("za");

        List<WebElement> itemsName = driver.findElements(By.cssSelector("[data-test='inventory-item-name']"));

        String firstElementName = itemsName.get(0).getText();
        String lastElementName = itemsName.get(itemsName.size() -1).getText();

        softAssert.assertEquals(firstElementName, "Test.allTheThings() T-Shirt (Red)");
        softAssert.assertEquals(lastElementName, "Sauce Labs Backpack");
        softAssert.assertAll();

        //Test.allTheThings() T-Shirt (Red)
    }

    @Test(groups = {regression})
    public void testSortItemsByPrice(){
        llenarFormulario("standard_user","secret_sauce");

        //sleep(3000);

        Logs.info("Verificando la pagina principal");
        driver.findElement(By.cssSelector("[data-test='title']"));

        WebElement selectSort = driver.findElement(By.cssSelector("[data-test='product-sort-container']"));

        //Cast Select Element
        Select select = new Select(selectSort);

        Logs.info("Chose items to sort from low -> high");
        select.selectByValue("lohi");

        List<WebElement> itemsPrice = driver.findElements(By.cssSelector("[data-test='inventory-item-price']"));

        String firstElementPrice = itemsPrice.get(0).getText().replace("$","");
        String lastElementPrice = itemsPrice.get(itemsPrice.size() -1).getText().replace("$","");

        System.out.println("Item Price " + firstElementPrice);

        softAssert.assertEquals(firstElementPrice, "7.99");
        softAssert.assertEquals(lastElementPrice, "49.99");
        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void testFacebookLink(){
        llenarFormulario("standard_user","secret_sauce");

        //sleep(3000);

        Logs.info("Verificando la pagina principal");
        driver.findElement(By.cssSelector("[data-test='title']"));

        WebElement facebookLink = driver.findElement(By.cssSelector("[data-test='social-facebook']"));
        String facebookHrefUrl = facebookLink.getAttribute("href");

        softAssert.assertTrue(facebookLink.isDisplayed());
        softAssert.assertTrue(facebookLink.isEnabled());
        softAssert.assertEquals(facebookHrefUrl, "https://www.facebook.com/saucelabs");
        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void testTwitterLink(){
        llenarFormulario("standard_user","secret_sauce");

        //sleep(3000);

        Logs.info("Verificando la pagina principal");
        driver.findElement(By.cssSelector("[data-test='title']"));

        WebElement twitterLink = driver.findElement(By.cssSelector("[data-test='social-twitter']"));
        String twitterHrefUrl = twitterLink.getAttribute("href");

        softAssert.assertTrue(twitterLink.isDisplayed());
        softAssert.assertTrue(twitterLink.isEnabled());
        softAssert.assertEquals(twitterHrefUrl, "https://twitter.com/saucelabs");
        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void testLinkedinLink(){
        llenarFormulario("standard_user","secret_sauce");

        //sleep(3000);

        Logs.info("Verificando la pagina principal");
        driver.findElement(By.cssSelector("[data-test='title']"));

        WebElement linkedinLink = driver.findElement(By.cssSelector("[data-test='social-linkedin']"));
        String linkedinHrefUrl = linkedinLink.getAttribute("href");

        softAssert.assertTrue(linkedinLink.isDisplayed());
        softAssert.assertTrue(linkedinLink.isEnabled());
        softAssert.assertEquals(linkedinHrefUrl, "https://www.linkedin.com/company/sauce-labs/");
        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void testMenuBurgerAbout(){
        llenarFormulario("standard_user","secret_sauce");

        //sleep(3000);

        Logs.info("Verificando la pagina principal");
        driver.findElement(By.cssSelector("[data-test='title']"));

        driver.findElement(By.id("react-burger-menu-btn")).click();

        WebElement menu = driver.findElement(By.className("bm-item-list"));

        Logs.info("Creando explicit wait");
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(1500));
        wait.until(element -> menu.isDisplayed());

        WebElement aboutLink = driver.findElement(By.cssSelector("[data-test='about-sidebar-link']"));
        String hrefLink = aboutLink.getAttribute("href");

        softAssert.assertTrue(aboutLink.isDisplayed());
        softAssert.assertTrue(aboutLink.isEnabled());
        softAssert.assertEquals(hrefLink, "https://saucelabs.com/");
        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void testMenuBurgerLogOut(){
        llenarFormulario("standard_user","secret_sauce");

        //sleep(3000);

        Logs.info("Verificando la pagina principal");
        driver.findElement(By.cssSelector("[data-test='title']"));

        driver.findElement(By.id("react-burger-menu-btn")).click();

        //sleep(2000);

        //LogOut
        driver.findElement(By.cssSelector("[data-test='logout-sidebar-link']")).click();

        //sleep(2000);

        driver.findElement(By.cssSelector("[data-test='login-container']"));
    }

    @Test
    public void testHandleCookies(){
        llenarFormulario("standard_user","secret_sauce");

        Logs.info("Verificando la pagina principal");
        driver.findElement(By.cssSelector("[data-test='title']"));

        Logs.info("Obteniendo las cookies");
        Set<Cookie> cookies = driver.manage().getCookies();

        Logs.info("Verificar que solo existe una cookie");
        Assert.assertEquals(cookies.size(), 1);

        Logs.info("eliminando las cookies");
        driver.manage().deleteAllCookies();

        Logs.info("Obteniendo las cookies nuevamente");
        cookies = driver.manage().getCookies();

        Logs.info("Verificando que no hay cookies");
        Assert.assertTrue(cookies.isEmpty());
    }

    @Test
    public void testVerificandoCookies(){
        llenarFormulario("standard_user","secret_sauce");

        Logs.info("Verificando la pagina principal");
        driver.findElement(By.cssSelector("[data-test='title']"));

        Logs.info("Obteniendo las cookies");
        Cookie cookie = driver.manage().getCookieNamed("session-username");

        Logs.info("Verificar el valor de la cookie");
        Assert.assertNotNull(cookie);
        Assert.assertEquals(cookie.getValue(), "standard_user");
    }

    private void llenarFormulario(String userName, String password){
        Logs.info("Navegate to Sauce Labs page");
        driver.get("https://www.saucedemo.com/");

        //sleep(3000);

        Logs.info("Filling Login Form");
        driver.findElement(By.cssSelector("[data-test='username']")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(password);

        Logs.info("Click on Login Button");

        driver.findElement(By.id("login-button")).click();

        //sleep(2000);
    }
}
