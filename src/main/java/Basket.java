import java.util.ArrayList;
import java.util.List;

public class Basket {

    List<Cloth> productsList = new ArrayList<>();

    /** dodanie produktu do koszyka
     * @author Magdalena Woźniak & Yelizaveta Samartsаva
     * @param product - wybrany produkt z ubrań*/
    public void add(Cloth product){
        productsList.add(product);
    }

    /** usunięcie produktu z koszyka
     * @author Magdalena Woźniak & Yelizaveta Samartseva
     * @param product - wybrany produkt z ubrań*/
    public void delete(Cloth product){
        productsList.remove(product);
    }

    /** zwracanie łącznej ceny produktów w koszyku
     * @author Magdalena Woźniak & Yelizaveta Samartseva
     * @return całkowita cena w koszyku*/
    public int priceOfBasket(){
        int fullPrice = 0;
        for (Cloth cloth : productsList) {
            fullPrice += cloth.getPrice();
        }
        return fullPrice;
    }

    @Override
    public String toString() {
        StringBuilder productWithDescription = new StringBuilder(); // podobno mądre to zastosowałam

        for (int i = 0; i < productsList.size(); i++) {
            productWithDescription.append(i + 1); // + 1 bo nie ma produktu z numerem 0
            productWithDescription.append(". "); // append - połącz
            productWithDescription.append(productsList.get(i)); // wyświetlenie całego produktu (sama nazwa .getName)
            productWithDescription.append("\n");
        }

        productWithDescription.append("Price of basket: ");
        productWithDescription.append(priceOfBasket());
        productWithDescription.append("$");
        return String.valueOf(productWithDescription); // konwertuje StringBuilder na string
    }
}