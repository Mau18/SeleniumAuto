package pages;

public class PaginaPrincipal extends BasePage {

    private String TextBox = "//input[@id='cb1-edit']";
    private String Search = "//div[@aria-label='Buscar']";
    private String category = ".nav-categs-departments li";

    

    public PaginaPrincipal(){
        super(driver);    
    }
    
    //Metodo para navegar a amazon
    public void navegaML (){
        navigateTo("https://www.mercadolibre.com.mx/");

    }

   
    //Método para dar clic en el buscador y escribir alexa
    public void clickBuscador(){
        clickElement(TextBox);
        write(TextBox, "alexa");
        clickElement(Search);
    }

    //Metodo para obtener el numero de categorias
    public int  obtieneCategorias(){
        return getListSize(category);
    }
    

}
