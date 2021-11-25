import java.util.ArrayList;
import java.util.List;

public class Client extends Person{
    // koszyk

    String adress;
    List<Order> orders = new ArrayList<>();

    public Client(String name, String surname, String email, String password, int id, boolean admin) {
        super(name, surname, email, password, id, admin);
        this.adress = adress;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    /** przypisanie zamówienia do konta klienta
      * @author Magdalena Woźniak & Yelizaveta Samartseva
      * @param order - złożone zamówienie*/
    public void placeOrder(Order order){
        orders.add(order);
    }

    /** logowanie do konta użytkownika
     * @author Magdalena Woźniak & Yelizaveta Samartseva
     * @param email - email użytkownika
     * @param password - hasło konta użytkownika*/
    public void login(String email,String password){
        // logowanie
    }

    /** rejestracja użytkownika
     * @author Magdalena Woźniak & Yelizaveta Samartseva
     * @param email - email użytkownika
     * @param password - hasło konta użytkownika*/
    public void register(String email,String password){
        // rejestracja
    }

    /** wyświetlanie wszystkich zamówień danego użytkownika
     * @author Magdalena Woźniak & Yelizaveta Samartseva*/
    public void orders(){

        for (Order order : orders) {
            System.out.println(order);
        }

    }

}