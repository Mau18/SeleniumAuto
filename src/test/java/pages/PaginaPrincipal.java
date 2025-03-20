package pages;

public class PaginaPrincipal extends BasePage {

    private String TextBox = "//input[@id='cb1-edit']";
    private String Search = "//div[@aria-label='Buscar']";
    private String category = ".nav-categs-departments li";

    //private String Cookies = "/html/body/div[4]/div[1]/div/div[2]/button[1]";
    private String Categorias = "//a[normalize-space()='Categorías']";
    private String Vehiculos = "//a[normalize-space()='Vehículos']"; 

    //"//ul[@class='nav-categs-departments']//a[contains(text(), 'Vehículos')]"; //a[normalize-space()='Compra Internacional']

    public PaginaPrincipal(){
        super(driver);    
    }
    
    //Metodo para navegar a ML
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


    //Método para hacer un clic en las categorias y seleccionar Vehiculos
    public void clickCategorias(){
        //clickElement(ubicacion);
        //clickElement(Cookies);
        ClicJavaScript(Categorias);
        ClicJavaScript(Vehiculos);
        
    }
    

}
