package pages;

import java.util.NoSuchElementException;

public class Alexahover extends BasePage{

    String image1 = "/html/body/main/div[2]/div[3]/div[3]/div[1]/div/div/a";
    String image2 = "/html/body/main/div[2]/div[3]/div[3]/div[1]/div/div/nav/ol/li[1]/a";
    String image3 = "/html/body/main/div[2]/div[3]/div[3]/div[1]/div/div/nav/ol/li[2]/a";
    String image4 = "/html/body/main/div[2]/div[5]/div[2]/div[1]/div/div[2]/div[2]/div[3]/div/div[2]/a[1]/div/img";
    String image5 = "/html[1]/body[1]/main[1]/div[2]/div[5]/div[2]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/a[1]/div[1]/img[1]";
    String cookies = "/html/body/div[4]/div[1]/div/div[2]/button[1]";
    // String image7 = "/html[1]/body[1]/main[1]/div[2]/div[5]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/span[7]/label[1]/div[1]/div[1]/img[1]";
    // String image8 = "/html[1]/body[1]/main[1]/div[2]/div[5]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/span[8]/label[1]/div[1]/div[1]/img[1]";
    String Postalcode = "//span[normalize-space()='Más tarde']";

    public Alexahover(){
        super(driver);
    }

    public void clickPostal(){
        try{
            clickElement(Postalcode);
        }catch (NoSuchElementException e){
            System.out.println("no se encuntra el botón 'más tarde'");
        }catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
      
        
    }

    public void hoverOverAlexa(){
        hoverOverElement(image1);
        hoverOverElement(image2);
        hoverOverElement(image3);
        hoverOverElement(image4);
        //clickElement(cookies);
        doubleClick(image5);
    }



    
}
