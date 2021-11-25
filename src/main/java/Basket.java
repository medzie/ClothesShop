import java.util.ArrayList;
import java.util.List;

public class Basket {

    List<String> unavailableProductOrder = new ArrayList<>();
    
    public void productAvailable(boolean isProductAvailable){

        if (!isProductAvailable){
            // unavailableProductOrder.add(); dodać przedmioty do listy zamowien przez pracownika
        }

    }

    List<Clothes> productsList = new ArrayList<>();

    public void add(Clothes product){
        productsList.add(product);
    }

    public void delete(Clothes product){
        productsList.remove(product);
    }

    public void priceOfBasket(){

    }

    public void amountOfProducts(){

    }

}
