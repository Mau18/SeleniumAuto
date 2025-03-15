package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

     /*
     * Declaración de una variable de instancia 'wait' de tipo WebDriverWait.
     * Se inicializa inmediatamente con una instancia dew WebDriverWait utilizando el 'driver' estático
     * WebDriverWait se usa para poner esperas explícitas en los elementos web
     */

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    //Levanta una instancia de chrome cuando arranque la ejecución
    static{
        WebDriverManager.chromedriver().setup();
         //Inicializa la variable estática 'driver' con una instancia de ChromeDriver
        driver = new ChromeDriver();
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

     //funcion de tiempo de espera localización de path esperando a que lo encuentre
    private WebElement Find(String locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    private WebElement Find2(String locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
    }

    // función click de selenium para interactuar con los elementos web
    // cuando se use el click se mandará a llamar el clicElement que utiliza el Find con el tiempo de espera estipulado 
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

    //Método para calcular el numero de elementos en un arreglo.
    public int dropdownSize(String locator){
        Select dropdown = new Select(Find(locator));

        List<WebElement> dropdownOptions = dropdown.getOptions();

        return dropdownOptions.size();

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

    public void Scroll(String locator){
        WebElement elemento = driver.findElement(By.className(locator)); 
        JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", elemento);

    }

    //Método para calcular el numero de elementos en un arreglo.
    public int getListSize(String locator) {
        // Encuentra todos los elementos con el locator proporcionado
        List<WebElement> elements = driver.findElements(By.cssSelector(locator));
    
        // Devuelve el tamaño de la lista de elementos
        return elements.size();
    }

   
    


    

};
    