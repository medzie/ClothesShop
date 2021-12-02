import java.util.ArrayList;
import java.util.List;

public class Client extends Person {

    public Basket basket = new Basket();
    List<Order> orders = new ArrayList<>();

    public Client(String name, String surname, String email, String password, boolean admin) {
        super(name, surname, email, password, admin);
    }

    /**
     * przypisanie zamówienia do konta klienta
     * @param order - złożone zamówienie
     * @author Magdalena Woźniak & Yelizaveta Samartsava
     */
    public void placeOrder(Order order) {
        orders.add(order);
    }

    /**
     * wyświetlanie wszystkich zamówień danego użytkownika
     * @author Magdalena Woźniak & Yelizaveta Samartsava
     */
    public void orders() {
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}