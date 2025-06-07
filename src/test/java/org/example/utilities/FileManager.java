package org.example.utilities;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    private static final String screenshotPath = "src/test/resources/screenshots";
    private static final String pageSourcePath = "src/test/resources/pageStructure";

    public static void getScreenshot(String screenshotName){
        Logs.debug("Tomando Screenshot");
        File file = ((TakesScreenshot) new WebdriverProvider().get()).getScreenshotAs(OutputType.FILE);

        String path = String.format("%s/%s.png", screenshotPath,screenshotName);

        try{
            FileUtils.copyFile(file,new File(path));
        }catch (IOException ex){
            Logs.error("Error al tomar screenshot, %s: ", ex.getLocalizedMessage());
        }
    }

    public static void cleanPreviousEvidence(){
        try{
            Logs.info("Limpiando folder de evidencias");
            FileUtils.deleteDirectory(new File(screenshotPath));
            FileUtils.deleteDirectory(new File(pageSourcePath));

        }catch (IOException ex){
            Logs.error("Error al Intentar limpiar la evidencia, %s: ", ex.getLocalizedMessage());
        }
    }

    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] getScreenshot(){
        return ((TakesScreenshot) new WebdriverProvider().get()).getScreenshotAs(OutputType.BYTES);
    }

    public static void getPageSource(String fileName){
        Logs.info("Taking page source");

        String path = String.format("%s/%s.html", pageSourcePath, fileName);

        try{
            File file = new File(path);

            if (file.getParentFile().mkdir()){
                FileWriter fileWriter = new FileWriter(file);
                String pageSource = new WebdriverProvider().get().getPageSource();
                if (pageSource != null) {
                    fileWriter.write(Jsoup.parse(pageSource).toString());
                }
                fileWriter.close();
            }

        }catch (IOException ex){
            Logs.error("Error taking page source: %s", ex.getLocalizedMessage());
        }
    }
}
