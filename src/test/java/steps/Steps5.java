package steps;

import java.util.List;

import io.cucumber.java.en.*;
import pages.PaginaPrincipal;

public class Steps5 {

    PaginaPrincipal mainPage = new PaginaPrincipal();
    
    // @Given("The user navigate to www.mercadolibre.com.mx")
    // public void navegaML(){
    //     mainPage.navegaML();

    // }

    @When("The user write Playstation and search results")
    public void writePlay() throws InterruptedException{
        mainPage.searchPlay();
    }

    @Then("The user obtain a list of results")
    public void obtainList(){
        List<String> lista = mainPage.getAllSearchResults();
        String Texto = "playstation 2";
        boolean textIsThere = lista.stream().anyMatch(text -> text.toLowerCase().contains(Texto));

        if(textIsThere) {
            System.out.println("Se muestra " + Texto + " " + " en la lista:  " + lista);
        }else{
            throw new Error( Texto + " " + "No se muestra en la lista" + lista);
        }
    }
}
