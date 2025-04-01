package pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.StaleElementReferenceException;

public class Productos extends BasePage {

    private String Pop ="/html/body/div[5]/div/div/div[2]/div/div/div[2]/button[2]/span";
    private String Cookies = "/html/body/div[4]/div[1]/div/div[2]/button[1]";
    private String Pagina= "/html[1]/body[1]/main[1]/div[1]/div[2]/section[1]/div[8]/nav[1]/ul[1]/li[3]/a[1]";

    private String alexa= "/html/body/main/div/div[2]/section/div[7]/ol/li[4]/div/div"; 
   
    //private String mensaje = ".alert.alert-danger.alert-dismissible";

    public Productos() {
        super(driver);
       
    }

    public void ClickPop(){
        clickElement(Pop);
    }

    public void Scroll (){
        try{
            Scroll(Pagina);
         }catch (NoSuchElementException e){
            System.out.println("no se encuntra el elemento");
         }catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
         }
    }

    public void clickCookie(){
       
        clickElement(Cookies);
      }

    public void SelectPag(){
       
        try{
            clickElement(Pagina);
         }catch (StaleElementReferenceException e) {
            clickElement(Pagina);
         }
    }

    public void clickAlexa(){
        Scroll(alexa);
        clickElement(alexa);
    }

    // public String returnalerta(){
    //     return ObtenerTexto(alerta);
        
    // }

    
}
