package utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
    public static String takeScreenshot(WebDriver driver, String scenarioName) {
    
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = "target/screenshots/" + scenarioName.replaceAll(" ", "_") + ".png";
            FileUtils.copyFile(srcFile, new File(screenshotPath));
            return screenshotPath;
        } catch (IOException e) {
            System.err.println("⚠️ Error al tomar el screenshot: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}

