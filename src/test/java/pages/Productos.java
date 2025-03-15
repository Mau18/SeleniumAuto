package pages;


public class Productos extends BasePage {

    private String Pop ="/html/body/div[5]/div/div/div[2]/div/div/div[2]/button[2]/span";
    private String Cookies = "/html/body/div[4]/div[1]/div/div[2]/button[1]";
    private String Pagina= "/html[1]/body[1]/main[1]/div[1]/div[2]/section[1]/nav[1]/ul[1]/li[3]/a[1]";
   
    //private String mensaje = ".alert.alert-danger.alert-dismissible";

    public Productos() {
        super(driver);
       
    }

    public void ClickPop(){
        clickElement(Pop);
    }

    public void Scroll (){
        Scroll(Pagina);
    }

    public void clickCookie(){
       
        clickElement(Cookies);
      }

    public void SelectPag(){
       
      clickElement(Pagina);
    }

    // public String returnalerta(){
    //     return ObtenerTexto(alerta);
        
    // }

    
}
