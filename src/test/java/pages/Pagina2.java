package pages;

import java.util.NoSuchElementException;

public class Pagina2 extends BasePage {

    private String Product = "/html/body/main/div/div[2]/section/div[7]/ol/li[4]/div/div";
    
    private String AddToCart = "//span[normalize-space()='Agregar al carrito']";
    private String mensaje = "//*[@id=\":R19:\"]/div[1]/h1";

    public Pagina2 () {
        super(driver);
    }

    public void SelectProducto(){
        try{
        clickElement(Product);
         }catch (NoSuchElementException e){
            System.out.println("no se encuntra el producto a seleccionar");
         }catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
         }
    }  

    public void AgregarAlCarrito(){
        clickElement(AddToCart);
    }

    public String returnmensaje(){
        return ObtenerTexto(mensaje);  
        }
    
}
