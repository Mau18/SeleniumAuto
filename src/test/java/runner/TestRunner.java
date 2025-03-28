package runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import pages.BasePage;

@RunWith(Cucumber.class)
@CucumberOptions(
    features="src/test/resources/features", //Directorio de archivos .feature
    glue = "steps", //paquete donde tenemos nuestras clases definiendo los steps escritos en el feature file
     //plugin = {"pretty", "html:target/cucumber-reports"},//crear un reporte en la ruta
     plugin = {"pretty", "steps.Hooks"},
     tags = "@ML"

)

public class TestRunner {
    
    @BeforeClass
    public static void MaximizaBrowser(){
        BasePage.Max();
        BasePage.BorrarCookies();
    }

    @AfterClass
    public static void cleanDriver(){
      BasePage.closeBrowser();
  }
  
}