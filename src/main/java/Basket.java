import java.util.ArrayList;
import java.util.List;

public class Basket {

    List<String> unavailableProductOrder = new ArrayList<>();

    /** jeśli false, dodaje przedmiot do listy zamówień magazynowych
      * @author Magdalena Woźniak & Yelizaveta Samartseva
      * @param isProductAvailable - sprawdzanie dostępności produktu*/
    public void productAvailable(boolean isProductAvailable){

        if (!isProductAvailable){
            // unavailableProductOrder.add(); dodać przedmioty do listy zamowien magazynowych
        }

    }

    List<Cloth> productsList = new ArrayList<>();

    /** dodanie produktu do koszyka
     * @author Magdalena Woźniak & Yelizaveta Samartseva
     * @param product - instancja produktu*/
    public void add(Cloth product){
        productsList.add(product);
    }

    /** usunięcie produktu z koszyka
     * @author Magdalena Woźniak & Yelizaveta Samartseva
     * @param product - instancja produktu*/
    public void delete(Cloth product){
        productsList.remove(product);
    }

    /** łączna cena produktów w koszyku
     * @author Magdalena Woźniak & Yelizaveta Samartseva*/
    public void priceOfBasket(){

    }

    /** łączna liczba produktów w koszyku
     * @author Magdalena Woźniak & Yelizaveta Samartseva*/
    public void amountOfProducts(){

    }

    @Override
    public String toString() {
        return "Products: " + productsList;
    }
}