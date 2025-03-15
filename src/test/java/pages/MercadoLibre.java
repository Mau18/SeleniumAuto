package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MercadoLibre {
    private WebDriver driver;

@BeforeMethod
public void setUp() {
    //Inicializa el web Driver para chrome
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
}

@Test
public void navegaML(){
    //navega a la página web
    driver.get("https://www.mercadolibre.com.mx/");
}

@AfterMethod
public void tearDown(){
    //cierra la página web
    if(driver != null);{
    driver.quit();    
    }
}

}
