public class Client extends Person{

    // przeglądanie sklepu
    // logowanie/rejestracja/koszyk

    String adress;

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

    public void placeOrder(){
        // odwołać się do orders
    }

}
