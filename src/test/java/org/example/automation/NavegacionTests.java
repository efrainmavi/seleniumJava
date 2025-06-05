package org.example.automation;

import org.example.utilities.BaseTest;
import org.example.utilities.Logs;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavegacionTests extends BaseTest {
    static final String url = "https://www.saucedemo.com/";
    String urlHeroku = "https://the-internet.herokuapp.com/";
    String urlGithub = "https://github.com/";

    @Test(groups = {regression, smoke})
    public void ejercicioUnoTest(){
        Logs.info("Navegando a: %s", url);
        driver.get(url);

        Logs.info("Obteniendo la URL actual");
        String currentUrl = driver.getCurrentUrl();

        Logs.info("Verificando la URL");
        Assert.assertEquals(currentUrl, url);
    }

    @Test(groups = {regression})
    public void ejercicioDosTest(){

        Logs.info("Navegando a heroku app ");
        driver.get(urlHeroku);

        Logs.info("Navegando a Github");
        driver.get(urlGithub);

        Logs.info("Regresando a la pagina anterior");
        driver.navigate().back();

        Logs.info("Obteniendo la URL actual");
        String currentUrl = driver.getCurrentUrl();

        Logs.info("Comparando URL's");
        Assert.assertEquals(currentUrl, urlHeroku);

    }

    @Test(groups = {regression})
    public void alwaysFailTest(){
        Logs.info("Navegando a heroku app ");
        driver.get(urlHeroku);

        Logs.info("Obteniendo la URL actual");
        String currentUrl = driver.getCurrentUrl();

        Logs.info("Comparando URL's");
        Assert.assertEquals(currentUrl, "Hola Mundo");
    }
}
