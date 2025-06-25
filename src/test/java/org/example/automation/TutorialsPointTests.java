package org.example.automation;

import org.example.utilities.BaseTest;
import org.example.utilities.Logs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Objects;
import java.util.Set;

public class TutorialsPointTests extends BaseTest {

    @Test
    public void testNewTab(){
        Logs.info("Navegando a pagina");
        driver.get("https://www.tutorialspoint.com/selenium/practice/browser-windows.php");

        Logs.debug("obteniendo el id de la pestaña actual");
        String tabId = driver.getWindowHandle();
        Logs.debug("tabId: %s", tabId);

        Logs.info("Click en boton new tab");
        driver.findElement(By.cssSelector("button[title='New Tab']")).click();

        final Set<String> windowHandleSet = driver.getWindowHandles();
        Logs.debug("Window Handles Set: %s", windowHandleSet);

        Logs.debug("Cambiando a la nueva pestaña");
        for (String windowTab: windowHandleSet){
            /* Comprobando que la pestaña original no sea igual que la nueva pestaña */
            if (!windowTab.equals(tabId)){
                /* Cambiando a la nueva pestaña*/
                driver.switchTo().window(windowTab);
            }
        }
        Logs.info("Verificamos que el texto");
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='New Tab']")).isDisplayed());

        Logs.info("Cerrando la pestaña actual y regresando a la ventana original");
        driver.close();
        driver.switchTo().window(tabId);

        Logs.debug("Verificando la ventana original");
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Browser Windows']")).isDisplayed());
    }

    @Test
    public void testNewWindow(){
        Logs.info("Navegando a pagina");
        driver.get("https://www.tutorialspoint.com/selenium/practice/browser-windows.php");

        Logs.debug("obteniendo el id de la ventana actual");
        String windowId = driver.getWindowHandle();
        Logs.debug("windowId: %s", windowId);

        Logs.info("Click en boton new window");
        driver.findElement(By.xpath("//button[text()='New Window']")).click();

        final Set<String> windowHandleSet = driver.getWindowHandles();
        Logs.debug("Window Handles Set: %s", windowHandleSet);

        Logs.debug("Cambiando a la nueva ventana");
        for (String newWindow: windowHandleSet){
            /* Comprobando que la pestaña original no sea igual que la nueva pestaña */
            if (!newWindow.equals(windowId)){
                /* Cambiando a la nueva pestaña*/
                driver.switchTo().window(newWindow);
            }
        }
        Logs.info("Verificamos que el texto");
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='New Window']")).isDisplayed());

        Logs.info("Cerrando la ventana actual y regresando a la ventana original");
        driver.close();
        driver.switchTo().window(windowId);

        Logs.debug("Verificando la ventana original");
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Browser Windows']")).isDisplayed());
    }

    @Test
    public void testIframe(){
        Logs.info("Navegando a pagina");
        driver.get("https://www.tutorialspoint.com/selenium/practice/nestedframes.php");

        Logs.debug("Cambiamos al iFrame");
        driver.switchTo().frame("frame1");

        Logs.info("Verificando titulo del iframe");
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='New Tab']")).isDisplayed());

        Logs.debug("Regresando a la pagina original");
        driver.switchTo().defaultContent();

        Logs.info("Verificando Pagina principal");
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Nested Frames']")).isDisplayed());
    }
}
