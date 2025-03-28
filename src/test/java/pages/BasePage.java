package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
     /*
     * Declaración de una variable estática 'driver' de tipo WebDriver
     * Esta variable va a ser compartida por todas las instancias de BasePage y sus subclases
     */

    protected static WebDriver driver;
    private static Actions action;

     /*
     * Declaración de una variable de instancia 'wait' de tipo WebDriverWait.
     * Se inicializa inmediatamente con una instancia dew WebDriverWait utilizando el 'driver' estático
     * WebDriverWait se usa para poner esperas explícitas en los elementos web
     */

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

//     public static void EvitadetectSelenium(){
//         ChromeOptions options = new ChromeOptions();

//             System.setProperty("webdriver.chrome.driver", "C:\\Users\\jorge\\.cache\\selenium\\chromedriver\\win64\\133.0.6943.141\\chromedriver");
//             options.addArguments("--disable-blink-features=AutomationControlled");
//             options.addArguments("--disable-extensions");
// }


    //Levanta una instancia de chrome cuando arranque la ejecución
    static{

        WebDriverManager.chromedriver().setup();
         //Inicializa la variable estática 'driver' con una instancia de ChromeDriver
        driver = new ChromeDriver();
        action = new Actions (driver);

        ChromeOptions options = new ChromeOptions();
        String userDataDir = System.getenv("USER_DATA_DIR"); // Obtener la ruta del entorno
            if (userDataDir != null) {
                    options.addArguments("--user-data-dir=" + userDataDir);
             } else {
             // Fallback a un valor predeterminado si no está disponible
             options.addArguments("--user-data-dir=/path/to/default/dir");
            }
            driver = new ChromeDriver(options);
        
    }

    // constructor de BasePage que acepta un objeto WebDriver como argumento.
    public BasePage (WebDriver driver){
        BasePage.driver = driver;
        
    }

     //Método estático para navegar a una URL.
    public static void navigateTo(String url){
        driver.get(url);
    }
     //Método para el cierre del navegador
    public static void closeBrowser(){
        driver.quit();
    }

    public static void Max(){
        driver.manage().window().maximize();;
    }

    public static void BorrarCookies(){
    driver.manage().deleteAllCookies();
    }

     //funcion espera a que el elemento se encuentre y sea visible para realizar una acción
    protected WebElement Find(String locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    // Solo confirma que existe el elemento en el html 
    //protected WebElement customFind(String locator){
    //     return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    // }

    // función click de selenium para interactuar con los elementos web
    public void clickElement(String locator){
        
            Find(locator).click();
        
    }



    //Método para envió de texto    
    public void write(String locator, String keysToSend){
        Find(locator).clear();
        Find(locator).sendKeys(keysToSend);
    }

    //Método para seleccionar un valor de Listbox utilizando su nombre
    public void selectFromDropdownByValue(String locator, String value){
        Select dropdown = new Select(Find(locator));

        dropdown.selectByValue(value);
    }

    //Método para seleccionar un valor de Listbox utilizando su posición
    public void selectFromDropdownByIndex (String locator, Integer index){
        Select dropdown = new Select(Find(locator));

        dropdown.selectByIndex(index);
    }

    //Método para calcular el numero de elementos en un arreglo tipo Slect.
    public int dropdownSize(String locator){
        Select dropdown = new Select(Find(locator));

        List<WebElement> dropdownOptions = dropdown.getOptions();

        return dropdownOptions.size();

    }

    //Método para calcular el numero de elementos en un arreglo tipo List <lu>
    public int getListSize(String locator) {

        List<WebElement> elements = driver.findElements(By.cssSelector(locator));
    
        return elements.size();
    }

    //Metodo que devuelve todos los valores de una lista
    public List<String> getDropdownValues(String locator){
        Select dropdown = new Select(Find(locator));

        List<WebElement>dropDownOptions = dropdown.getOptions();
        List<String> values = new ArrayList<>();
        for (WebElement option : dropDownOptions){
            values.add(option.getText());
        }

        return values;
    }

    //Método para extraer lo valores tipo texto de un dropdown select
    public void selectFromDropdownByText(String locator, String valueToSelect){
        Select dropdown = new Select (Find(locator));

        dropdown.selectByVisibleText(valueToSelect);
    }

    //Método para extraer el texto de un elemento
    public String ObtenerTexto(String locator){
        WebElement elemento = driver.findElement(By.xpath(locator));
        return elemento.getText();
        
    }

    //Método para hacer clic en un link
    public void goToLinkText(String linkText){
        driver.findElement(By.linkText(linkText)).click();
    }
    
    //Método para armar una lista de elementos y obtener uno especifico
    public void selectNelement(String locator, int index){
            List<WebElement> results = driver.findElements(By.xpath(locator));
            results.get(index).click();
    }

    //Método para realizar scroll para localizar un elemento que no se ve
    public void Scroll(String locator){
        WebElement elemento = driver.findElement(By.xpath(locator)); 
        JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", elemento);

    }
    

    public void ClicJavaScript(String locator){
        WebElement elemento = driver.findElement(By.xpath(locator));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", elemento);
        //((JavascriptExecutor) driver).executeScript("arguments[0].click();", elemento);

    }

    //Metodo para buscar elementos en un dropdown por su texto enviadp desde scenario outline
    public void SelectElementoDropdowTypeLi(String ExamplesOutline,String locator ){
        String ElementoXpath = String.format("//span[contains(text(), '%s')]/ancestor::li", ExamplesOutline);
        WebElement categoriaElement = driver.findElement(By.xpath(ElementoXpath));
        categoriaElement.click();
    }

    //Método para pasar el mouse arriba de un elemento
    
    public void hoverOverElement(String locator){
        action.moveToElement(Find(locator)).perform();;
    }

    //Método para realizar un doble clic
    public void doubleClick(String locator){
        action.doubleClick(Find(locator)).perform();;
    }

    //Método para realizar un clic derecho
    public void rightClick(String locator){
        action.contextClick(Find(locator)).perform();
    }

    //Método para armar una lista de elementos por xpath
    public List<WebElement> bringMeElements(String locator){
        return driver.findElements(By.xpath(locator));
    } 


};
    