package pages;

public class Vehiculos extends BasePage {

    String ListaPath = "/html[1]/body[1]/main[1]/div[1]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]/span[1]";
    String SelectAuto = "/html/body/main/div/div[3]/div/section/div/div/div[1]/div/div/div/div/div/ul";
    String Buscar = "/html[1]/body[1]/main[1]/div[1]/div[3]/div[1]/section[1]/div[1]/div[1]/div[5]/button[1]"; 
    String Scroll = "/html[1]/body[1]/main[1]/div[1]/div[4]/article[1]/div[1]/section[1]/div[2]/div[1]/div[1]/div[3]/div[1]/a[1]/div[1]/div[1]/div[1]/img[1]";
    

    public Vehiculos(){
        super(driver);
    }

    public void Scroll(){
        Scroll(Scroll);
    }

    public void SeleccionaAuto(String categoria){ 
        Scroll(Scroll);
        //despliego la lista para ser visible
        clickElement(ListaPath);
        //uso la categoria del escenario outline y el xpath de la lista de elementos en donde se encuentra la categoria
        SelectElementoDropdowTypeLi(categoria, SelectAuto);

    }

    public void BuscarAuto(){
        //clic en el botón buscar
        clickElement(Buscar);
    }


    
}
