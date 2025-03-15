package pages;

public class Pagina2 extends BasePage {

    private String Product = "/html[1]/body[1]/main[1]/div[1]/div[2]/section[1]/ol[1]/li[3]/div[1]/div[1]/div[2]/h3[1]/a[1]";
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
