package org.example.automation;

import org.example.utilities.BaseTest;
import org.example.utilities.Logs;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavegacionTests extends BaseTest {
    static final String url = "https://www.saucedemo.com/";

    @Test
    public void ejercicioUnoTest(){
        Logs.info("Navegando a: %s", url);
        driver.get(url);

        Logs.info("Obteniendo la URL actual");
        String currentUrl = driver.getCurrentUrl();

        Logs.info("Verificando la URL");
        Assert.assertEquals(currentUrl, url);
    }
}
