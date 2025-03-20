package pages;

import java.util.NoSuchElementException;

public class ResultadosVehiculos extends BasePage{

    String Postalcode = "//span[normalize-space()='Más tarde']";
    String Resultado = "/html[1]/body[1]/main[1]/div[1]/div[2]/aside[1]/div[2]/span[1]";
    String cerrar ="//*[@id=\":r0:\"]/div/div/div/button";

    public ResultadosVehiculos(){
        super(driver);
    }


    public void clicPostal(){
        try {
            clickElement(Postalcode);
            }  catch (NoSuchElementException e) {
                System.out.println("El elemento no se encontró.");
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
          
    }


    public String SelectTextResultado(){
        
        return ObtenerTexto(Resultado);
        
    }


    
}
