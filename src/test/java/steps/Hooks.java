package steps;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import pages.BasePage;

public class Hooks extends BasePage {
    
    public Hooks(){
        super(driver);
    }

    @AfterStep
    public void tearDown(Scenario scenario){
      if(scenario.equals(scenario)){
            scenario.log("Scenario succesfull, please refer to the image attached to this report");
            final byte[] screenshot = ((TakesScreenshot) driver)
            .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot of the step succes");
        }

    }

}
