package org.example.automation;

import net.datafaker.Faker;
import org.example.utilities.BaseTest;
import org.example.utilities.Logs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SeleniumDevTest extends BaseTest {

    @Test
    public void testScroll(){
        Logs.info("Navegando a la pagina");
        driver.get("https://www.selenium.dev/selenium/web/scroll.html");

        int fakeNumb = new Faker().number().numberBetween(5,9);
        String dynamicId = String.format("line%d",fakeNumb);
        WebElement line = driver.findElement(By.id(dynamicId));

        new Actions(driver)
                .scrollToElement(line)
                .pause(1000)
                .perform();

        line.click();

        Assert.assertEquals(driver.findElement(By.id("clicked")).getText(), dynamicId);

    }

    @Test
    public void testScrollWithFrame(){
        Logs.info("Navegando a la pagina");
        driver.get("https://www.selenium.dev/selenium/web/scrolling_tests/page_with_frame_out_of_view.html");

        WebElement iFrame = driver.findElement(By.name("frame"));

        new Actions(driver)
                .scrollToElement(iFrame)
                .pause(1500)
                .perform();

        driver.switchTo().frame(iFrame);

        WebElement checkBox = driver.findElement(By.name("checkbox"));
        checkBox.click();

        Assert.assertTrue(checkBox.isSelected());
    }
}
