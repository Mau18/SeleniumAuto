package steps;


import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.PaginaPrincipal;
import pages.Productos;
import pages.Pagina2;

public class MLSteps1{

    //Declaro a que pagina navego
    PaginaPrincipal landigPage = new PaginaPrincipal();
    Productos Prod = new Productos();
    Pagina2 Pag2 = new Pagina2();

    @Given ("The user navigate to www.mercadolibre.com.mx")
    public void navegaAmazon(){
            landigPage.navegaML();
    }
   
    @And ("busca por Producto")
    public void buscaProducto(){
            landigPage.clickBuscador();    
    }

    @And ("Seleccionar pagina 2")
    public void Paginar(){
            Prod.ClickPop();
            Prod.clickCookie();
            Prod.SelectPag();
    }

    @And ("selecciona el tercer item")
         public void SeleccionarProducto(){
            Pag2.SelectProducto();
           
    }
    
    @Then ("el usuario es capaz de agregarlo al carrito")
        public void AgregaCarrito(){
            Pag2.AgregarAlCarrito();

            String  mensaje = Pag2.returnmensaje();
            String mensajeEsperado = ("¡Hola! Para agregar al carrito, ingresa a tu cuenta");
             //String mensajeEsperado = ("Products marked with *** are not available in the desired quantity or not in stock!") + '\n' + ("×");

            Assert.assertEquals(mensaje, mensajeEsperado);

           //Assert.assertEquals(alerta, alertaesperada);

     }


    
}
