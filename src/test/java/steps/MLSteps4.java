package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.Alexahover;
import pages.PaginaPrincipal;
import pages.Productos;

public class MLSteps4 {

    PaginaPrincipal PaginaInicial = new PaginaPrincipal();
    Productos Prod = new Productos();
    Alexahover hover = new Alexahover();

    // @Given ("The user navigate to www.mercadolibre.com.mx")
    // public void navegaML(){
    //     PaginaInicial.navegaML();
    // }

    @Then ("The user write Alexa and search results")
    public void writeAlexa(){
        PaginaInicial.clickBuscador();
    }

    @And ("The user select the first result")
    public void clickAlexa(){
        Prod.clickAlexa();
    }

    @And ("The user made hoverOver in the images")
    public void hoverImages(){
        hover.clickPostal();
        hover.hoverOverAlexa();
    }

}
