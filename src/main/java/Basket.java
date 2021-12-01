import java.util.ArrayList;
import java.util.List;

public class Basket {

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