package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import junit.framework.Assert;
import pages.PaginaPrincipal;
import pages.Vehiculos;
import pages.ResultadosVehiculos;

public class MLSteps3 {

    PaginaPrincipal PaginaInicial = new PaginaPrincipal();
    Vehiculos PagVehiculos = new Vehiculos();
    ResultadosVehiculos Resultados = new ResultadosVehiculos();

   // @Given ("The user navigate to www.mercadolibre.com.mx")
   // public void NavegandoML(){
   //      PaginaInicial.navegaML();
   // }

   @Then ("El usuario selecciona la categoría Vehiculos")
   public void SelectCategoria(){
      PaginaInicial.clickCategorias();
   }

   @And ("El usuario selecciona {word} y busca resultados")
   public void BuscaResultados(String categoria){
      PagVehiculos.SeleccionaAuto(categoria);
      System.out.println("el valor selectFromdownByValue: " + categoria);
      PagVehiculos.BuscarAuto();
   }

   @SuppressWarnings("deprecation")
   @And ("el usuario valida la cantidad de resultados obtenidos es mayor a 0")
      public void ValidaResults(){
         Resultados.clicPostal();
         String Result = Resultados.SelectTextResultado();

         String numberText = Result.split(" ")[0].replace(",", "");

         // Convierte el texto a un número (por ejemplo, a un entero)
         int number = Integer.parseInt(numberText);
 
         // Verifica que el número sea mayor que cero
         Assert.assertTrue("El número de resultados debe ser mayor que cero", number > 0);

         System.out.println("El numero de resultados enconytrados es: "+ " " + numberText);
   }

    
}
