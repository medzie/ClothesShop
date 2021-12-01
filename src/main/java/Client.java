import java.util.ArrayList;
import java.util.List;

public class Client extends Person {

    private Basket basket;
    List<Order> orders = new ArrayList<>();

    public Client(String name, String surname, String email, String password, boolean admin) {
        super(name, surname, email, password, admin);
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    /**
     * przypisanie zamówienia do konta klienta
     *
     * @param order - złożone zamówienie
     * @author Magdalena Woźniak & Yelizaveta Samartseva
     */
    public void placeOrder(Order order) {
        orders.add(order);
    }

    /**
     * wyświetlanie wszystkich zamówień danego użytkownika
     *
     * @author Magdalena Woźniak & Yelizaveta Samartseva
     */
    public void orders() {
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}