package pages;

public class Pagina2 extends BasePage {

    private String Product = "/html/body/main/div/div[2]/section/ol/li[3]/div";
    
    private String AddToCart = "//span[normalize-space()='Agregar al carrito']";
    private String mensaje = "//*[@id=\":R19:\"]/div[1]/h1";

    public Pagina2 () {
        super(driver);
    }

    public void SelectProducto(){
        clickElement(Product);
    }  

    public void AgregarAlCarrito(){
        clickElement(AddToCart);
    }

    public String returnmensaje(){
        return ObtenerTexto(mensaje);  
        }
    
}
