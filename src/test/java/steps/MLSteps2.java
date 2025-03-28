package steps;

import org.junit.Assert;

import io.cucumber.java.en.Then;
import pages.PaginaPrincipal;

public class MLSteps2 {

    PaginaPrincipal landigPage = new PaginaPrincipal();

    // @Given ("The user navigate to www.mercadolibre.com.mx")
    // public void navegaML(){
    //         landigPage.navegaML();
    // }

    @Then ("^El usuario verifica que el número de categorías es (\\d+)$")
    public void verificaListado(int Cantidad){
        System.out.println("Número de categorías recibido: " + Cantidad);
        int CantidadEsperada = Cantidad;
        
        int cantidadActual = landigPage.obtieneCategorias();
        System.out.println("Número de cantidad actual: " + cantidadActual);

        Assert.assertEquals(CantidadEsperada, cantidadActual);


    }
    
}
